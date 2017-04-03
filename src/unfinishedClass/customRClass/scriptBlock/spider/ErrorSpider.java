package unfinishedClass.customRClass.scriptBlock.spider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

public abstract class ErrorSpider extends AbstractBCSpider {
	/**
	 * 一个用来起标志作用的变量。
	 */
	protected boolean error;
	
	/**
	 * 默认Spider没有发生错误。
	 * @param targetBlock
	 * 		目标Block节点。
	 */
	public ErrorSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		error = false;
	}
	
	/**
	 * @param targetBlock
	 * 		目标Block节点。
	 * @param initError
	 * 		手动初始化Spider是否发生错误。
	 */
	public ErrorSpider(ScriptBlock targetBlock, boolean initError){
		super(targetBlock);
		error = initError;
	}

	@Override
	protected abstract void dealWithTargetBlock();
	
	public boolean occurredError(){
		return error;
	}
}
