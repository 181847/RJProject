package unfinishedClass.customRClass.scriptBlock.spider.analysisSpider;

import unfinishedClass.customRClass.RStringChecker;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.ScriptDeclaration;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;
import unfinishedClass.customRClass.scriptBlock.spider.CountableSpider;

/**
 * 分析多个弧线声明，
 * 弧线分为两个种类：
 * 执行弧线和参数弧线。
 */
public class ArcFieldAnalysisSpider extends CountableSpider {
	/**
	 * 由于最多有两种类型的弧线，
	 * 两种弧线分别放在两个Block中，
	 * 所以最大的检查数量就是2，
	 * 超过此数量的Block一定是非法的，
	 * 不予检查，直接设为VOID。
	 */
	public static int categoryLimit = 2;

	public ArcFieldAnalysisSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}
	
	@Override
	public void countWork() {
		//注意count在countWork执行前进行加一操作，
		//count从0开始，
		//因而第一次读取的值为1.
		if (count <= categoryLimit) {
			//分两种类型进行检查。
			if (targetInfoString
					.equals(ScriptDeclaration.declar_arcs_e_to_e)) {
				//执行弧线声明。
				setInfo(InformationType.DECLAR_ARCS_E_TO_E);
				analysisSubBlock();
				
			} else if (targetInfoString
					.equalsIgnoreCase(ScriptDeclaration.declar_arcs_r_to_p)) {
				//参数弧线声明
				setInfo(InformationType.DECLAR_ARCS_R_TO_P);
				analysisSubBlock();
			}
			
		} else {
			//如果末尾强制将Block标志为空白信息。
			setInfo_VOID();
			descriptInfo("无用的弧线类型声明。");
		}
		
		
	}
	
	/**
	 * 此方法对子Block进行具体的弧线检查（不区分类型），
	 * 此方法只在ArcFieldAnalysisSpider.countWork()中调用。
	 */
	private void analysisSubBlock() {
		if (hasSubBlock) {
			new ArcAnalysisSpider(subBlock)
				.workUntilEnd();
		}
	}

}
