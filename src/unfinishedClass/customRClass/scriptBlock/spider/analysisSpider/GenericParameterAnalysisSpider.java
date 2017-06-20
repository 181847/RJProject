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
		if (RStringChecker
				.checkGenParamDeclar(
						information.getOriginalString())){
			subBlock = targetBlock.getSub();
			
			//泛参声明必须包含泛型约束，
			//即subBlock 不能为null。
			if (subBlock != null){
				//设置InformattionType
				information.setType(InformationType.GEN_PARAM);
				
				//分析作为泛型约束的泛型定义
				new RClassRefAnalysisSpider(subBlock)
						.workUntilEnd();
			} else {
				//泛参声明缺乏泛型约束。
				information.setType(InformationType.VOID);
				information.appendDescription("泛参声明非法，缺少泛型约束。" );
			}
		} else {
			information.setType(InformationType.VOID);
			information.appendDescription("泛参名声明非法。" );
		}
	}

}
