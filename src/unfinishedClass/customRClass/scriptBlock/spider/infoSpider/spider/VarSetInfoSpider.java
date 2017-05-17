package unfinishedClass.customRClass.scriptBlock.spider.infoSpider.spider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;
import unfinishedClass.customRClass.set.VarSet;

public class VarSetInfoSpider extends AbstractBCSpider {
	protected VarSet varSet;
	
	public VarSetInfoSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		varSet = new VarSet();
	}

	@Override
	protected void dealWithTargetBlock() {
		switch(targetBlock.getInformation().getType()){
		case VAR:
			varSet.addVar(
					targetBlock
					.getInformation()
					.getOriginalString());
			break;
		default:
			break;
		}
	}
	
	public VarSet getVarSet(){
		return varSet;
	}
}
