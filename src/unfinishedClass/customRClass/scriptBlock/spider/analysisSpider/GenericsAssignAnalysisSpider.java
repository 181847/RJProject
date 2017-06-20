package unfinishedClass.customRClass.scriptBlock.spider.analysisSpider;

import unfinishedClass.customRClass.RStringChecker;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;

/**
 * 专门分析一系列泛参指定的信息，
 * 例如一段泛型的结构如下：<br>
 * default.GenericsClassA member1 -12<br>
 *      M: basic.Integer<br>
 *      N: T<br>
 * 本Spider专门用于分析下面对于“M”和“N”这两行的的信息是否正确。
 */
public class GenericsAssignAnalysisSpider extends AbstractBCSpider {

	public GenericsAssignAnalysisSpider(ScriptBlock targetBlock) {
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
		if (RStringChecker.checkGenParamAssign(information.getOriginalString())){
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
					new GenericsAssignAnalysisSpider(subBlock)
						.workUntilEnd();
				}
			}
		} else {
			information.setType(InformationType.VOID);
			information.appendDescription("泛参指定非法。" );
		}
	}

}
