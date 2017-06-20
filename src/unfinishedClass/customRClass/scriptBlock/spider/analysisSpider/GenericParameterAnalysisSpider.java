package unfinishedClass.customRClass.scriptBlock.spider.analysisSpider;

import unfinishedClass.customRClass.RStringChecker;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;

/**
 * 分析一连串的泛参声明，
 * 注意是泛参声明，
 * 指的是在RClass或者Function中声明泛参
 * 泛参声明的时候必然包含一个泛型约束。
 */
public class GenericParameterAnalysisSpider extends AbstractBCSpider {

	public GenericParameterAnalysisSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	protected void dealWithTargetBlock() {
		ScriptBlock subBlock;
		Information information = 
				targetBlock.getInformation();
		//检查泛参声明是否正确声明了一个泛参的名字，
		//泛参的命名规范和RClass的命名规范不同，
		//需要注意区分。
		if (RStringChecker.checkGenParamDeclar(information.getOriginalString())){
			subBlock = targetBlock.getSub();
			
			if (subBlock != null){
				//如果当前为泛参指定的是另一个泛参，
				//则这个泛参下面不应该有其他的泛参指定，
				//即不存在T<int, String>这样子的情况，
				//其中T是一个泛参，具体类型位置。
				if (RStringChecker
						.isGenParam_assigned_to_GenParam(
								information.getOriginalString())){
					information.setType(InformationType.VOID);
					information.appendDescription("泛参指定非法，当前已经将一个泛参传递给某个泛型中，"
							+ "这个泛参本身不能被指定其他的泛参，即不存在T<int, String>这样子的情况。" );
				} else {
					//分析作为泛型约束的泛型定义
					new RClassRefAnalysisSpider(subBlock)
						.workUntilEnd();
				}
			}
		} else {
			information.setType(InformationType.VOID);
			information.appendDescription("泛参名声明非法。" );
		}
	}

}
