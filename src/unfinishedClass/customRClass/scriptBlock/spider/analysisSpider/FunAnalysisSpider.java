package unfinishedClass.customRClass.scriptBlock.spider.analysisSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.ScriptDeclaration;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;

/**
 * 分析Function内部声明字段的信息，
 * 不区分ConFun、StaticFun、Fun、AbstractFun。
 */
public class FunAnalysisSpider extends AbstractBCSpider {

	public FunAnalysisSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	protected void dealWithTargetBlock() {
		ScriptBlock subBlock = targetBlock.getSub();
		Information information = targetBlock.getInformation();
		String informationString = information.getOriginalString();
		
		if (informationString.equals(ScriptDeclaration.excutees)){
			//Function的执行入口组件分析
			information.setType(InformationType.EXCUTEE);
			if (subBlock != null){
				new ExcuteeAnalysisSpider(subBlock)
					.workUntilEnd();
			}
			
		} else if (informationString.equals(ScriptDeclaration.parameters)){
			//Function的参数组件分析
			information.setType(InformationType.PARAMETER);
			if (subBlock != null){
				new VarAnalysisSpider(subBlock, InformationType.PARAMETER)
					.workUntilEnd();
			}
			
		} else if (informationString.equals(ScriptDeclaration.excuters)){
			//Function的执行出口组件分析
			information.setType(InformationType.EXCUTER);
			if (subBlock != null){
				new ExcuterAnalysisSpider(subBlock)
					.workUntilEnd();
			}
			
		} else if (informationString.equals(ScriptDeclaration.returnvals)){
			//Function的返回值组件分析
			information.setType(InformationType.RETURNVAL);
			if (subBlock != null){
				new VarAnalysisSpider(subBlock, InformationType.RETURNVAL)
					.workUntilEnd();
			}
			
		} else if (informationString.equals(ScriptDeclaration.localVars)){
			//Function的本地变量组件分析
			information.setType(InformationType.LOCALVAR);
			if (subBlock != null){
				new VarFieldAnalysisSpider(subBlock)
					.workUntilEnd();
			}
			
		} else if (informationString.equals(ScriptDeclaration.subFunctions)){
			//Function的子Fun分析
			information.setType(InformationType.SUBFUN);
			if (subBlock != null){
				new SubFunAnalysisSpider(subBlock)
					.workUntilEnd();
			}
			
		} else if (informationString.equals(ScriptDeclaration.arcs)){
			//Function的内部弧线分析
			information.setType(InformationType.ARC);
			if (subBlock != null){
				new ArcAnalysisSpider(subBlock)
					.workUntilEnd();
			}
			
		} else if (informationString.equals(ScriptDeclaration.comments)){
			//Function的内部注释分析
			information.setType(InformationType.COMMENT);
			if (subBlock != null){
				new CommentAnalysisSpider(subBlock)
					.workUntilEnd();
			}
		} else {
			information.setType(InformationType.VOID);
			information.appendDescription("FunAnalysis中发现的无用信息。");
		}
	}

}
