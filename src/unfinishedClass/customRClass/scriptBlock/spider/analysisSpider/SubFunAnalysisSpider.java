package unfinishedClass.customRClass.scriptBlock.spider.analysisSpider;

import unfinishedClass.customRClass.RStringChecker;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;

public class SubFunAnalysisSpider extends AbstractBCSpider {

	public SubFunAnalysisSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	protected void dealWithTargetBlock() {
		Information information = targetBlock.getInformation();
		String informationString = information.getOriginalString();
		
		
		if (RStringChecker.checkSubFun(informationString)){
			information.setType(InformationType.SUBFUN);
		} else {
			information.setType(InformationType.VOID);
			information.appendDescription("子Fun的名称声明不符合规范，"
					+ "请检查格式是否正确（例如：(-4,-7)basic.Integer.addInteger{3}），或者是否包含非法字符。");
		}
	}

}
