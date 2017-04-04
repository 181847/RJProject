package unfinishedClass.customRClass.scriptBlock;

import unfinishedClass.customRClass.RStringChecker;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;

/**
 * 分析Block中的Information是否包含非法字符。
 */
public class NameAnalysisSpider extends AbstractBCSpider {

	public NameAnalysisSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	protected void dealWithTargetBlock() {
		Information information = targetBlock.getInformation();
		String informationString = information.toString();
		
		//检查是否包含非法字符
		if (RStringChecker.check(informationString)){
			information.setType(InformationType.CONTENT);
		} else {
			information.setType(InformationType.VOID);
			information.appendDescription("信息中包含非法字符：" + RStringChecker.getIllegalStrings());
		}
	}

}