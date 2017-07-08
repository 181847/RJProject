package unfinishedClass.customRClass.scriptBlock.spider.analysisSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.ScriptDeclaration;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.CountableSpider;

/**
 * 对一连串的弧线声明进行分析，
 * ArcAnalysisSpider（本类）不同于ArcFieldAnalysisSpider，
 * 前者直接对弧线进行分析，
 * 后者会对弧线进行分类，然后调用前者进行分析。
 */
public class ArcAnalysisSpider extends CountableSpider {

	public ArcAnalysisSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	public void countWork() {
		//分析单个Block的信息是否是一个用来表示弧线的特殊字符串，
		//要求必须含有子信息块。
		if (targetInfoString.equals(ScriptDeclaration.arrow)
				&& hasSubBlock) {
			//设置弧线标签。
			setInfo(InformationType.ARC);
			new SingleArcAnalysisSpider(subBlock)
				.workUntilEnd();
		} else {
			setInfo_VOID();
			descriptInfo("非法的弧线信息。");
		}
	}

}
