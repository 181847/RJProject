package unfinishedClass.customRClass.scriptBlock.spider.analysisSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.ScriptDeclaration;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.CountableSpider;

/**
 * 对执行出口进行分析，
 * 分为异常和普通执行出口。
 */
public class ExcuterAnalysisSpider extends CountableSpider {

	public ExcuterAnalysisSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	public void countWork() {
		//检查异常执行出口。
		if (targetInfoString
				.equals(ScriptDeclaration.declar_exception_excuter)) {
			setInfo(InformationType.DECLAR_EXCUTERS_EXCEPTION);
			
			if (hasSubBlock) {
				new ComponentAnalysisSpider(subBlock)
					.workUntilEnd();
			}
			
		//检查普通执行出口。
		} else if (targetInfoString.equals(ScriptDeclaration.declar_normal_excuter)) {
			
			setInfo(InformationType.DECLAR_EXCUTERS_NORMAL);
			
			if (hasSubBlock) {
				new ComponentAnalysisSpider(subBlock)
					.workUntilEnd();
			}
		} else {
			setInfo_VOID();
			descriptInfo("非法的执行出口声明。");
		}
	}

}
