package unfinishedClass.customRClass.scriptBlock.spider.analysisSpider;

import unfinishedClass.customRClass.RStringChecker;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;
import unfinishedClass.customRClass.scriptBlock.spider.CountableSpider;

/**
 * 这个类用来分析注释的矩形区域是否正确声明。
 */
public class CommentsAnalysisSpider extends CountableSpider {

	public CommentsAnalysisSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	public void countWork() {
		//分析方形区域声明是否符合规范。
		if (RStringChecker.checkRect(targetInfoString)) {
			//设置标签。
			setInfo(InformationType.RECT);
			
			if (hasSubBlock) {
				//对方形区域下的具体注释信息进行分析，
				//由于对注释没有限制，
				//所以直接使用一种单独的AnalysisSpider进行简单分析。
				new SingleTypeAnalysisSpider(subBlock, InformationType.INFO_COMMENT)
					.workUntilEnd();
			}
		} else {
			setInfo_VOID();
			descriptInfo("非法的方形注释区域声明。");
		}
	}

}
