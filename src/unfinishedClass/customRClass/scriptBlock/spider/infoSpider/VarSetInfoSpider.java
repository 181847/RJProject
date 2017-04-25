package unfinishedClass.customRClass.scriptBlock.spider.infoSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;
import unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct.VarSetStruct;

public class VarSetInfoSpider extends AbstractBCSpider {
	protected VarSetStruct varSetStruct;
	
	public VarSetInfoSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		varSetStruct = new VarSetStruct();
	}

	@Override
	protected void dealWithTargetBlock() {
		switch(targetBlock.getInformation().getType()){
		case VAR:
			varSetStruct.addVar(
					targetBlock
					.getInformation()
					.getOriginalString());
			break;
		default:
			break;
		}
	}
	
	public VarSetStruct getVarSetStruct(){
		return varSetStruct;
	}
}
