package unfinishedClass.customRClass.scriptBlock.spider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

public abstract class ReasonedErrorSpider extends ErrorSpider {
	protected String errorReason;

	public ReasonedErrorSpider(ScriptBlock targetBlock) {
		this(targetBlock, false);
	}
	
	public ReasonedErrorSpider(ScriptBlock targetBlock, boolean initError) {
		super(targetBlock, initError);
		errorReason = "";
	}

	@Override
	protected abstract void dealWithTargetBlock();
	
	public String getErrorReason(){
		return errorReason;
	}
	
	public void appendErrorReason(String anotherReason){
		if (errorReason.isEmpty()){
			errorReason = anotherReason;
		} else {
			errorReason += "；" + anotherReason;
		}
	}
}
