package unfinishedClass.customRClass.scriptBlock.spider.analysisSpider;

import unfinishedClass.customRClass.RStringChecker;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.ScriptDeclaration;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;

/**
 * 检查成员声明的Spider。
 */
public class VarFieldAnalysisSpider extends VarAnalysisSpider {

	public VarFieldAnalysisSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	protected void dealWithTargetBlock() {
		ScriptBlock subBlock = targetBlock.getSub();
		Information information = targetBlock.getInformation();
		String informationString = information.getOriginalString();
		
		if (informationString.equals(
				ScriptDeclaration.staticD)){
			information.setType(InformationType.STATIC);
			
			if (subBlock != null){
				new VarAnalysisSpider(subBlock)
					.workUntilEnd();
			}
		} else {
			super.dealWithTargetBlock();
		}
	}

}
