package unfinishedClass.customRClass.scriptBlock;

import unfinishedClass.customRClass.RStringChecker;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;

public class VarAnalysisSpider extends AbstractBCSpider {

	public VarAnalysisSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	protected void dealWithTargetBlock() {
		Information information = targetBlock.getInformation();
		String informationString = information.getOriginalString();
		
		if (RStringChecker.checkVar(informationString)){
			information.setType(InformationType.VAR);
		} else {
			 //包含非法字符的信息，或者不符合变量声明格式
			 information.setType(InformationType.VOID);
			 information.appendDescription("变量声明包含非法字符"
			 + RStringChecker.getIllegalStrings()
			 + "或者不符合变量定义的格式。");
		}
	}

}
