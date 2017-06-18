package unfinishedClass.customRClass.scriptBlock.spider.analysisSpider;

import unfinishedClass.customRClass.RStringChecker;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;

public class ExcuteeAnalysisSpider extends AbstractBCSpider {

	public ExcuteeAnalysisSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	protected void dealWithTargetBlock() {
		Information information = targetBlock.getInformation();
		String informationString = information.getOriginalString();
		
		if (RStringChecker.checkExcutee(informationString)){
			information.setType(InformationType.EXCUTEE);
		} else {
			 //包含非法字符的信息
			 information.setType(InformationType.VOID);
			 information.appendDescription("执行入口（Excutee）声明格式非法。");
		}
	}

}
