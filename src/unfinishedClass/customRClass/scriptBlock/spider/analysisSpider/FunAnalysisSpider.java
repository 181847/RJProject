package unfinishedClass.customRClass.scriptBlock.spider.analysisSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.ScriptDeclaration;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;
import unfinishedClass.customRClass.scriptBlock.spider.CountableSpider;

/**
 * 分析Function内部声明字段的信息，
 * 不区分ConFun、StaticFun、Fun、AbstractFun。
 */
public class FunAnalysisSpider extends CountableSpider {

	public FunAnalysisSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}
	
	@Override
	public void countWork() {
		if (targetInfoString.equals(ScriptDeclaration.declar_excutees)){
			//执行入口组件声明。
			setInfo(InformationType.DECLAR_EXCUTEES);

			if (hasSubBlock) {
				//由于执行入口的声明只有单独的一个名字，
				//也没有什么分类，
				//所以只用一个组件分析去检查所有的字符串是否符合组件命名规范。
				new ComponentAnalysisSpider(subBlock)
					.workUntilEnd();
			}

		} else if (targetInfoString.equals(ScriptDeclaration.declar_parameters)) {
			//参数组件声明。
			setInfo(InformationType.DECLAR_PARAMETERS);

			if (hasSubBlock) {
				new VarsAnalysisSpider(subBlock)
					.workUntilEnd();
			}

		} else if (targetInfoString.equals(ScriptDeclaration.declar_excuters)) {
			//执行出口组件声明。
            setInfo(InformationType.DECLAR_PARAMETERS);

			if (hasSubBlock) {
				//执行出口分为正常和异常执行chukou ,
				//需要进行进一步的分类检查。
				new ExcuterAnalysisSpier(subBlock)
					.workUntilEnd();
			}

		} else if (targetInfoString.equals(ScriptDeclaration.declar_returnvals)) {
			//返回值组件声明。
            setInfo(InformationType.DECLAR_RETURNVALS);

			if (hasSubBlock) {
				new VarsAnalysisSpider(subBlock)
					.workUntilEnd();
			}

		} else if (targetInfoString.equals(ScriptDeclaration.declar_local_vars)) {
			//本地变量组件声明。
            setInfo(InformationType.DECLAR_LOCALVARS);
			
			if (hasSubBlock) {
				new VarFieldAnalysisSpider(subBlock)
					.workUntilEnd();
			}

		} else if (targetInfoString.equals(ScriptDeclaration.declar_subFuns)) {
			//子Function声明
            setInfo(InformationType.DECLAR_SUBFUNS);

			if (hasSubBlock) {
				new SubFunAnalysisSpider(subBlock)
					.workUntilEnd();
			}

		} else if (targetInfoString.equals(ScriptDeclaration.declar_arcs)) {
			//连接弧线声明
            setInfo(InformationType.DECLAR_ARCS);

			if (hasSubBlock) {
				new ArcsAnalysisSpider(subBlock)
					.workUntilEnd();
			}

		} else if (targetInfoString.equals(ScriptDeclaration.declar_comments)) {
			//注释声明
            setInfo(InformationType.DECLAR_COMMENTS);

			if (hasSubBlock) {
				new CommentsAnalysisSpider(subBlock)
					.workUntilEnd();
			}

		} else {
			//非法信息
			setInfo_VOID();
			descriptInfo("Function中的无用信息。");
		}
	}

}
