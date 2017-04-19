package unfinishedClass.customRClass.scriptBlock.spider.grammarSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.LineScriptInformation;
import unfinishedClass.customRClass.scriptBlock.spider.basicToolSpider.ReasonedErrorSpider;

/**
 * 增加一个添加错误信息的方法，
 * 添加信息之前加上targetBlock所包含的Information存储的行数信息，
 * 并且添加一个方法允许在结尾加上Information的Description，
 * GrammarSpider默认发生错误，
 * 必须至少找到一个合法的Block才能从错误的状态转变为正常状态。
 */
public abstract class GrammarSpider extends ReasonedErrorSpider {
	protected boolean foundOne;
	protected String gsDescription;
	
	/**
	 * 
	 * @param targetBlock
	 * 		目标Block节点。
	 * @param initError
	 * 		手动初始化Spider是否发生错误。
	 * @param spiderDescription
	 * 		对本GrammarSpider的基本描述，
	 * 		这个描述将会被添加到错误信息的文本当中，
	 * 		方便用户在发生错误的时候从信息中得知错误发生在哪一个语法检查阶段。
	 */
	public GrammarSpider(ScriptBlock targetBlock, String spiderDescription){
		super(targetBlock, true);
		gsDescription = spiderDescription;
		appendReason("#" + spiderDescription + "过程中发现以下错误：\n");
	}

	@Override
	protected abstract void dealWithTargetBlock();
	
	/**
	 * 已知targetBlock的InformationType是VOID类型，
	 * 毫无疑问，脚本信息中不允许出现任何无用信息，
	 * 当前脚本发生语法错误，不允许加载，
	 * 设置error成员为true。
	 */
	protected void dealWith_VOID() {
		appendReason("发现VOID信息。", true);
		error = true;
	}
	
	/**
	 * 统一定义一个针对意外信息的处理方法，
	 * 这个方法由子类调用。
	 */
	protected void dealWith_Unexpected(){
		appendReason("发现不属于本定义阶段的信息。", false);
		error = true;
	}
	
	/**
	 * 添加错误信息的时候在错误信息的前面
	 * 加上targetBlock的information的行数信息，
	 * 并且通过一个布尔参数来判断是否添加Information的Description。
	 * @param anotherReason
	 * 		错误原因。
	 * @param appendDescription
	 * 		是否添加targetBlock的Information的Description。
	 */
	public void appendReason(String anotherReason, boolean appendDescription){
		LineScriptInformation information =
				(LineScriptInformation) targetBlock.getInformation();
		
		if (appendDescription){
			anotherReason += ("\n\t\t信息描述：" + information.getDescription());
		}
		
		appendReason("错误行数：" + information.getLine() 
				+ "\t" + anotherReason);
	}
	
	/**
	 * 本方法配合foundOne变量，
	 * 本方法在第一次查找到合法信息的Block之后将Spider的状态转为正常，
	 * 本方法被调用时，如果foundOne为false，
	 * 就将Spider.error变为false，foundOne变为true。
	 */
	public void foundOneTaggle(){
		if ( ! foundOne){
			foundOne = true;
			error = false;
		}
	}
	
	@Override
	public String getErrorReason(){
		if ( ! foundOne){
			return super.getErrorReason() 
					+ "没有发现至少一个合法的信息。";
		}
		
		return super.getErrorReason();
	}
}
