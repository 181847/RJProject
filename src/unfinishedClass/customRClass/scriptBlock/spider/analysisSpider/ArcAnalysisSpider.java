package unfinishedClass.customRClass.scriptBlock.spider.analysisSpider;

import unfinishedClass.customRClass.RStringChecker;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.ScriptDeclaration;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;

public class ArcAnalysisSpider extends AbstractBCSpider {

	public ArcAnalysisSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	protected void dealWithTargetBlock() {
		Information information = targetBlock.getInformation();
		String informationString = information.getOriginalString();
		
		//标志检查的位置，
		//一个完整的弧线声明应该是
		//“EtoE: 1.funEnd -> 4.construct”
		//或者“RtoP: 2.result->3.by”
		//"EtoE: "和“RtoP: ”属于弧线类型声明，
		//当弧线的类型声明没有问题的时候，只需要检查后面的部分，
		//所以要对原始的声明信息进行一个分割，
		//提取出“1.funEnd -> 4.construct”或者“2.result->3.by”，
		//subLength就是用来存储这个分割位置的变量，
		//subLength>0 表示弧线类型声明合法，
		//subLength<0 表示弧线类型声明不合法，也就无需再进一步的检验了，
		//直接将信息标志为VOID。
		int subLength;
		if (informationString.startsWith(ScriptDeclaration.EArc)){
			//获取执行弧线声明的长度。
			subLength = ScriptDeclaration.EArc.length();
		} else if (informationString.startsWith(ScriptDeclaration.PArc)){
			//获取执行参数声明的长度。
			subLength = ScriptDeclaration.PArc.length();
		} else {
			//既不是执行弧线声明，
			//也不是参数弧线声明，
			//将subLength标志为-1，表示Information不是弧线信息。
			subLength = -1;
		}
		
		//
		if (subLength > 0 && 
				RStringChecker.checkArc(
						informationString.substring(subLength))){
			information.setType(InformationType.ARC);
		} else {
			//记录错误信息。
			information.setType(InformationType.VOID);
			information.appendDescription("弧线声明非法。");
		}
	}

}
