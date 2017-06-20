package unfinishedClass.customRClass.scriptBlock.spider.analysisSpider;

import unfinishedClass.customRClass.RStringChecker;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;

/**
 * 分析一个信息块中关于RClass类型的引用的定义，
 * 可以是如下几种类型的RClass类型引用：<br>
 * T	未知的泛参，定义在RClass和Function中的泛参；<br>
 * String	具体的单一类型引用，直接引用一个午餐的RClass类型；<br>
 * Generics<String> 一个泛型类型，泛参被具体设置；<br>
 * Generics<T>		一个泛型类型，
 * 泛型所需的泛参没有被具体指定，
 * 而是指向RClass或Function中指定的泛参。<br>
 */
public class RClassRefAnalysisSpider extends AbstractBCSpider {

	public RClassRefAnalysisSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	protected void dealWithTargetBlock() {
		Information information = 
				targetBlock.getInformation();
		ScriptBlock subBlock = 
				targetBlock.getSub();
		
		//检查是否符合RClass命名规范。
		if (RStringChecker
				.checkRClassName(		
						information.getOriginalString())){
			//设置信息类型
			information.setType(InformationType.CLASSREF);
			
			if (subBlock != null){
				//检查泛参指配
				new GenericsAssignAnalysisSpider(subBlock)
					.workUntilEnd();
			}
			
		//检查是否符合泛参命名规范
		} else if (RStringChecker
				.checkComponentName(	
						information.getOriginalString())){
			//如果是引用一个泛参，
			//就不能包含子信息块（子信息款用来配置类型之下的泛参）,
			//泛参是未知类型，它没有泛参可供指配。
			if (subBlock != null){
				//泛参之下不允许指配泛参。
				information.setType(InformationType.VOID);
				information.appendDescription("RClassRef指代的是一个泛参，"
						+ "泛参之下不允许指配泛参。");
			} else {
				//正确，设置信息类型.
				information.setType(InformationType.CLASSREF);
			}
		
		//两种命名规范都不符合。
		} else {
			information.setType(InformationType.VOID);
			information.appendDescription("RClassRef声明不符合RClass命名规范，"
					+ "也不符合泛参命名规范。");
		}
	}

}
