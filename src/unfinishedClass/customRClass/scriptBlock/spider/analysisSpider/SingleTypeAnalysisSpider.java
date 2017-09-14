package unfinishedClass.customRClass.scriptBlock.spider.analysisSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.CountableSpider;

/**
 * 本类不对Block进行任何检查，
 * 将Block的Information、Type设置为单一类型。
 */
public class SingleTypeAnalysisSpider extends CountableSpider {
	/**
	 * 所有被检查的Information都会被设置成这个类型。
	 */
	protected InformationType forceType;

	public SingleTypeAnalysisSpider(ScriptBlock targetBlock, InformationType forceType) {
		super(targetBlock);
		this.forceType = forceType;
	}

	@Override
	public void countWork() {
		setInfo(forceType);
	}

}
