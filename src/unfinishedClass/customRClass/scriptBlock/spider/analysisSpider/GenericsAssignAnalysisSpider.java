package unfinishedClass.customRClass.scriptBlock.spider.analysisSpider;

import unfinishedClass.customRClass.RStringChecker;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;
import unfinishedClass.customRClass.scriptBlock.spider.CountableSpider;

/**
 * 专门分析一系列泛参指定的信息，
 * 例如一段泛型的结构如下：<br>
 * default.GenericsClassA member1 -12<br>
 *      M: basic.Integer<br>
 *      N: T<br>
 * 本Spider专门用于分析下面对于“M”和“N”这两行的的信息是否正确。
 */
public class GenericsAssignAnalysisSpider extends CountableSpider {

	public GenericsAssignAnalysisSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	public void countWork() {
		//检查泛参指配的前后是否符合命名要求，
		//前一个必须是一个泛参名，
		//后一个是一个泛参名或者一个类型名。
		if (RStringChecker.checkGenParamAssign(targetInfoString)){
			
			//分析当前Block中代表的泛参指定信息，
			//是用Class来指定，
			//还是用泛参来指定。。
			if (RStringChecker.isGenParam_assigned_to_GenParam(targetInfoString)) {
				if (hasSubBlock) {
					//当使用另一个泛参来对当前泛参进行指定时，
					//就不应该再包含任何子信息了，
					//设置空白标签。
					setInfo_VOID();
					descriptInfo("当前信息表明，使用一个泛参来对另一个泛参进行指定，"
							+ "而子信息块中包含无用信息。");
				} else {
					//设置泛参标签。
					setInfo(InformationType.GP_ASSIGN_GP);
				}//else
				
			} else {//if isGenParam_assigned_to_GenParam
				//否则就是用RClass来指定。
				//设置RClass标签。
				setInfo(InformationType.GP_ASSIGN_CL);
				
				if (hasSubBlock) {
					//对下层子信息进一步分析当前指定类型的泛参指配。
					new GenericsAssignAnalysisSpider(subBlock)
						.workUntilEnd();
				}//hasSubBlock
			}// else
			
		} else {
			//泛参指定中的非法信息。
			setInfo_VOID();
			descriptInfo("无用的泛参指定信息。");
		}
	}

}
