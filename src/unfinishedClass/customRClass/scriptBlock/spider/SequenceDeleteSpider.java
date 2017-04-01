package unfinishedClass.customRClass.scriptBlock.spider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

public abstract class SequenceDeleteSpider extends ReasonedErrorSpider {
	protected boolean targetError;

	public SequenceDeleteSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		error = false;
	}

	@Override
	protected void dealWithTargetBlock() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void work(){
		if ( ! targetBlock.isHead()){
			dealWithTargetBlock();
			
			if (targetError){
				(targetBlock = targetBlock.getPrec())
					.getNext().detach();
			}
		}
		walk();
	}

}
