package unfinishedClass.customRClass.scriptBlock.spider.analysisSpider;

import unfinishedClass.customRClass.RStringChecker;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;
import unfinishedClass.customRClass.scriptBlock.spider.CountableSpider;

/**
 * 对多个子Function进行分析。
 */
public class SubFunAnalysisSpider extends CountableSpider {

	public SubFunAnalysisSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}
	
	@Override
	public void countWork() {
		//分析SubFun是否符合组件命名规范。
		if (RStringChecker.checkComponentName(targetInfoString)) {
			
			//SubFun一般都需要声明一些子信息，
			//包括类型、坐标等信息都是必要的，
			//因而SubBlock是必须存在的。
			if (hasSubBlock) {
				//设置标签。
				setInfo(InformationType.SUBFUN);
				
				//检查单个SubFun详细信息。
				new Single_SF_AnalysisSpider(subBlock)
					.workUntilEnd();
			} else {
				//没有SubFun，
				//则这个SubFun的信息声明一定是不完全的。
				setInfo_VOID();
				descriptInfo("子Fun声明缺少详细信息。");
			}
		} else {
			//不符合组件命名规范。
			setInfo_VOID();
			descriptInfo("SubFun名称不符合组件命名规范。");
		}
	}

}
