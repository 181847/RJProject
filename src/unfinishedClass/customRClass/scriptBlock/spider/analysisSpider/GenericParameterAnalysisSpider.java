package unfinishedClass.customRClass.scriptBlock.spider.analysisSpider;

import unfinishedClass.customRClass.RStringChecker;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;
import unfinishedClass.customRClass.scriptBlock.spider.CountableSpider;

/**
 * 分析一连串的泛参声明，
 * 注意是泛参声明，
 * 指的是在RClass或者Function中声明泛参
 * 泛参声明的时候必然包含一个泛型约束。
 */
public class GenericParameterAnalysisSpider extends CountableSpider {

	public GenericParameterAnalysisSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	public void countWork() {
		//检查泛参名字是否符合组件命名规范。
		if (RStringChecker.checkComponentName(targetInfoString)){
			
			if (hasSubBlock){
				setInfo(InformationType.GEN_PARAM);
				//对子Block检查是否是一个类型或者泛参
				new RClassRefAnalysisSpider(subBlock, 1).workUntilEnd();
			} else {
				setInfo_VOID();
				descriptInfo("泛参没有泛参约束。");
			}
		} else {
			setInfo_VOID();
			descriptInfo("泛参名字不符合组件规范。");
		}
	}

}
