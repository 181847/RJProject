package unfinishedClass.customRClass.scriptBlock.spider.analysisSpider;

import unfinishedClass.customRClass.RStringChecker;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;
import unfinishedClass.customRClass.scriptBlock.spider.CountableSpider;

/**
 * 分析执行入口的命名规范。
 */
public class ComponentAnalysisSpider extends CountableSpider {

	public ComponentAnalysisSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}
	
	@Override
	public void countWork() {
		if (RStringChecker.checkComponentName(targetInfoString)) {
			setInfo(InformationType.EXCUTEE);
		} else {
			setInfo_VOID();
			descriptInfo("不满足组件命名规范。");
		}
	}

}
