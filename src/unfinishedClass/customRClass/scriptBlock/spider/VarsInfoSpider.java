package unfinishedClass.customRClass.scriptBlock.spider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

public class VarSetInfoSpider extends AbstractBCSpider {
	protected VarSetStruct varSetStruct;
	
	public VarSetInfoSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		varSetStruct = new VarSetStruct();
	}

	@Override
	protected void dealWithTargetBlock() {
		varSetStruct.addVar(
				targetBlock
				.getInformation()
				.getOriginalString());
	}
	
	public VarSetStruct getVarSetStruct(){
		return varSetStruct;
	}

}
