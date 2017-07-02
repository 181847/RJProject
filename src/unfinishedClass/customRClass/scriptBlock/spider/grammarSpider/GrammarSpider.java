package unfinishedClass.customRClass.scriptBlock.spider.grammarSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.LineScriptInformation;
import unfinishedClass.customRClass.scriptBlock.spider.basicToolSpider.ErrorSpider;

/**
 * 拥有一个可以返回错误信息的方法，
 * 这个Spider将所有检查结果进行存储，
 * 通过相关的方法返回检查结果和检查信息。
 */
public abstract class GrammarSpider extends ErrorSpider {
	/**
	 * 记录无意义的bloc数量，
	 */
	protected int count_nonesense;
	
	/**
	 * 对当前GrammarSpider进行描述，
	 * 可用于输出错误信息。
	 */
	protected String spiderDesc;
	
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
	public GrammarSpider(ScriptBlock targetBlock, String spiderDesc){
		super(targetBlock);
		this.spiderDesc = spiderDesc;
		count_nonesense = 0;
	}
	
	/**
	 * 已知targetBlock的InformationType是VOID类型，
	 * 毫无疑问，脚本信息中不允许出现任何无用信息，
	 * 当前脚本发生语法错误，不允许加载，
	 * 设置error成员为true。
	 */
	protected void dealWith_VOID() {
		descriptError("发现未知信息。");
		//无意义Block计数加一。
		count_nonesense ++;
	}
	
	/**
	 * 统一定义一个针对意外信息的处理方法，
	 * 这个方法由子类调用。
	 */
	protected void dealWith_Unexpected(){
		descriptError("发现不属于本定义阶段的信息。");
		//无意义Block计数加一。
		count_nonesense ++;
	}
	
	/**
	 * 添加错误信息的时候在错误信息的前面
	 * 加上targetBlock的information的行数信息，
	 * 本方法对Block中存储的Information对象的实际类型有要求，
	 * 必须是包含行数的LineScriptInformation。<br>
	 * 被添加的描述形式类似：<br>
	 * “<br>
	 * 错误行数：15<br>
	 * 描述：未知类型信息。<br>
	 * ”<br>
	 * @param anotherReason
	 * 		对错误的描述。
	 */
	public void descriptError(String error){
		super.descriptError("错误行数：" 
				+ ((LineScriptInformation) targetInformation)
					.getLine()
				+ "描述：" + error);
	}
}
