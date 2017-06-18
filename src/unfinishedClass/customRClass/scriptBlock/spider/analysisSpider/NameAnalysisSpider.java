package unfinishedClass.customRClass.scriptBlock.spider.analysisSpider;

import unfinishedClass.customRClass.RStringChecker;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;

/**
 * 分析Block中的Information是否是正确的类名声明。
 */
public class NameAnalysisSpider extends AbstractBCSpider {

	public NameAnalysisSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	protected void dealWithTargetBlock() {
		Information information = targetBlock.getInformation();
		String informationString = information.getOriginalString();
		
		//检查是否包含非法字符
		if (RStringChecker.checkRClassName(informationString)){
			information.setType(InformationType.CLASSNAME);
		} else {
			information.setType(InformationType.VOID);
			information.appendDescription("RClass名字格式非法。" );
		}
	}

}
