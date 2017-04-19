package unfinishedClass.customRClass.scriptBlock.spider.basicToolSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

public abstract class ReasonedErrorSpider extends ErrorSpider {
	/**
	 * 记录错误信息的变量。
	 */
	protected String errorReason;

	/**
	 * 默认Spider没有发生错误。
	 * @param targetBlock
	 * 		目标Block节点。
	 */
	public ReasonedErrorSpider(ScriptBlock targetBlock) {
		this(targetBlock, false);
		errorReason = "";
	}
	
	/**
	 * @param targetBlock
	 * 		目标Block节点。
	 * @param initError
	 * 		手动初始化Spider是否发生错误。
	 */
	public ReasonedErrorSpider(ScriptBlock targetBlock, boolean initError) {
		super(targetBlock, initError);
		errorReason = "";
	}

	@Override
	protected abstract void dealWithTargetBlock();
	
	public String getErrorReason(){
		return errorReason;
	}
	
	public void appendReason(String anotherReason){
		if (errorReason.isEmpty()){
			errorReason = anotherReason;
		} else {
			errorReason += (anotherReason + "\n");
		}
	}
}
