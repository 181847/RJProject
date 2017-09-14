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
	/**
	 * 当符合组件命名规范时，
	 * block应该被赋予何种类型的标签。
	 */
	protected InformationType assignType;

	public ComponentAnalysisSpider(ScriptBlock targetBlock, InformationType assignType) {
		super(targetBlock);
		this.assignType = assignType;
	}
	
	@Override
	public void countWork() {
		if (RStringChecker.checkComponentName(targetInfoString)) {
			setInfo(assignType);
		} else {
			setInfo_VOID();
			descriptInfo("不满足组件命名规范。");
		}
	}

}
