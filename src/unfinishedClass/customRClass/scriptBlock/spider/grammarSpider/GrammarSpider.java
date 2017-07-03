package unfinishedClass.customRClass.scriptBlock.spider.grammarSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.information.LineScriptInformation;
import unfinishedClass.customRClass.scriptBlock.spider.basicToolSpider.ErrorSpider;

/**
 * 拥有一个可以返回错误信息的方法，
 * 这个Spider将所有检查结果进行存储，
 * 通过相关的方法返回检查结果和检查信息。
 */
public abstract class GrammarSpider extends ErrorSpider {
	/**
	 * 记录无意义或者内部出现语法错误的bloc数量，
	 */
	protected int count_nonesense;
	
	/**
	 * 对当前GrammarSpider进行描述，
	 * 可用于输出错误信息。
	 */
	protected String spiderDesc;
	
	/**
	 * 当前Block对应的InformationType。
	 */
	protected InformationType infoType;
	
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
		describeError("发现未知信息。对此未知信息的描述是：" + targetInformation.getDescription());
		//无意义Block计数加一。
		countNonesense();
	}
	
	/**
	 * 统一定义一个针对意外信息的处理方法，
	 * 这个方法由子类调用。
	 */
	protected void dealWith_Unexpected(){
		describeError("发现不属于本定义阶段的信，对此信息的描述是：" + targetInformation.getDescription());
		//无意义Block计数加一。
		countNonesense();
	}
	
	protected void dealWith_Lack_SubBlock(){
		describeError("缺少详细的内容声明。");
		//无意义Block计数加一。
		countNonesense();
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
	public void describeError(String error){
		super.describeError("错误行数：" 
				+ ((LineScriptInformation) targetInformation)
					.getLine()
				+ "描述：" + error);
	}
	
	/*
	 * @see unfinishedClass.customRClass.scriptBlock.spider.CountableSpider#countWork()
	 * 在对单个Block进行检查之前，
	 * 如果Block是VOID的话，
	 * 就直接调用dealWith-VOID()这个方法记录空白信息，
	 * 然后跳过正常的检查过程。
	 */
	@Override
	public void countWork() {
		infoType = targetInformation.getType();
		if (infoType == InformationType.VOID) {
			//如果发现VOID，就直接记录路错误信息。
			dealWith_VOID();
			//然后返回。
			return;
		} else {
			grammarWork();
		}
	}
	
	/**
	 * 返回是否发生过错误，
	 * 简单的判断count_nonesense是否大于等于0就可以了。
	 */
	@Override
	public boolean occurredError(){
		return count_nonesense > 0;
	}
	
	/**
	 * 让错误Block数量加一。
	 */
	protected void countNonesense(){
		count_nonesense++;
	}
	
	/**
	 * 接受一个GrammarSpider，
	 * 执行其检查功能，
	 * 并且对相应的错误信息进行检查，
	 * 这个方法的主要目的是为了简化代码，
	 * 因为很多中情况都是要调用一个Spider去子链中检查，
	 * 然后记录信息，
	 * 这种工作的代码流程几乎一样，
	 * 所以这里将这种代码流程用一个方法来实现，
	 * 使用的时候只需要根据需要创建好GrammarSpider就可以由
	 * 这些代码来完成剩余的工作。
	 * @param gs
	 * 		已经构造好了的GrammarSpider。
	 */
	protected void sendSpider(GrammarSpider gs){
		gs.workUntilEnd();
		if (gs.occurredError()) {
			//类型声明中发生错误。
			//无意义Block计数加一。
			countNonesense();
			
			//用子block的检查结果来描述当前错误。
			describeError(gs.report());
		}
	}

	/**
	 * 由子类来实现这个方法，
	 * 对Block进行语法检查，
	 * 但是子类无需处理InformationType为VOID的情况，
	 * 这种VOID的情况实时不会发动此方法的，
	 * 子类在使用的时候可以直接通过infoType这个成员变量来获取
	 * 当前Block的InformationType。
	 */
	protected abstract void grammarWork();
}
