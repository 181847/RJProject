package unfinishedClass.customRClass.scriptBlock.spider.analysisSpider;

import unfinishedClass.customRClass.RStringChecker;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.ScriptDeclaration;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;

public class ArcAnalysisSpider extends AbstractBCSpider {

	public ArcAnalysisSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	protected void dealWithTargetBlock() {
		Information information = targetBlock.getInformation();
		String informationString = information.getOriginalString();
		
		if (RStringChecker.checkArc(informationString)){
			information.setType(InformationType.ARC);
		} else {
			information.setType(InformationType.VOID);
			information.appendDescription("弧线声明格式非法。");
		}
	}

}
