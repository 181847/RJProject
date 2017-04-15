package unfinishedClass.customRClass.scriptBlock;

import unfinishedClass.customRClass.scriptBlock.information.LineScriptInformation;
import unfinishedClass.customRClass.scriptBlock.spider.ReasonedErrorSpider;

/**
 * 增加一个添加错误信息的方法，
 * 添加信息之前加上targetBlock所包含的Information存储的行数信息，
 * 并且添加一个方法允许在结尾加上Information的Description。
 */
public abstract class GrammarSpider extends ReasonedErrorSpider {
	protected boolean foundOne;

	/**
	 * 默认Spider没有发生错误。
	 */
	public GrammarSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}
	
	/**
	 * @param targetBlock
	 * 		目标Block节点。
	 * @param initError
	 * 		手动初始化Spider是否发生错误。
	 */
	public GrammarSpider(ScriptBlock targetBlock, boolean initError){
		super(targetBlock, initError);
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
		appendReason("RClass的脚本中发现VOID信息。", true);
		error = true;
	}
	
	/**
	 * 统一定义一个针对意外信息的处理方法，
	 * 这个方法由子类调用。
	 */
	protected void dealWith_Unexpected(){
		appendReason("脚本中发现无用信息。", true);
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
			anotherReason += ("\n\t信息描述：" + information.getDescription());
		}
		
		super.appendReason("错误行数：" + information.getLine() 
				+ "\n\t" + anotherReason);
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

}
