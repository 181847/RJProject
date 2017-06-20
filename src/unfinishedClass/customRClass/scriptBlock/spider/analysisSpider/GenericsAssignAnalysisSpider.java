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
		String inforamtionString = 
				information.getOriginalString();
		
		//检查泛参指配的前后是否符合命名要求，
		//前一个必须是一个泛参名，
		//后一个是一个泛参名或者一个类型名。
		if (RStringChecker.checkGenParamAssign(inforamtionString)){
			subBlock = targetBlock.getSub();
			
			if (subBlock != null){
				//如果当前为泛参指定的是另一个泛参，
				//则这个泛参下面不应该有其他的泛参指定，
				//即不存在T<int, String>这样子的情况，
				//其中T是一个泛参，具体类型位置。
				if (RStringChecker
						.isGenParam_assigned_to_GenParam(inforamtionString)){
					information.setType(InformationType.VOID);
					information.appendDescription("泛参指定非法，当前已经将一个泛参传递给某个泛型中，"
							+ "这个泛参本身不能被指定其他的泛参，即不存在T<int, String>这样子的情况。" );
				} else {
					//分析当前指定类型的泛参指配.
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
