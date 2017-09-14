package unfinishedClass.customRClass.scriptBlock.spider.analysisSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.ScriptDeclaration;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * 检查成员声明的Spider。
 */
public class VarFieldAnalysisSpider extends VarsAnalysisSpider {

	public VarFieldAnalysisSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}
	
	@Override
	public void countWork() {
		if (targetInfoString.equals(ScriptDeclaration.declar_static)){
			setInfo(InformationType.DECLAR_STATIC);
			
			//对静态变量声明部分进行变量分析。
			if (hasSubBlock){
				new VarsAnalysisSpider(subBlock)
					.workUntilEnd();
			}
		} else {
			super.countWork();
		}
	}

}
