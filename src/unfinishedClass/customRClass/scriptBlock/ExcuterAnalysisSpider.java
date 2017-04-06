package unfinishedClass.customRClass.scriptBlock;

import unfinishedClass.customRClass.RStringChecker;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;

public class ExcuterAnalysisSpider extends AbstractBCSpider {

	public ExcuterAnalysisSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	protected void dealWithTargetBlock() {
		Information information = targetBlock.getInformation();
		String informationString = information.getOriginalString();
		
		if (RStringChecker.checkExcutee(informationString)){
			information.setType(InformationType.EXCUTER);
		} else {
			 //包含非法字符的信息
			 information.setType(InformationType.VOID);
			 information.appendDescription("执行入口名包含非法字符");
		}
	}

}
