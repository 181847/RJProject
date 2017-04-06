package unfinishedClass.customRClass.scriptBlock;

import unfinishedClass.customRClass.RStringChecker;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;

public class ArcAnalysisSpider extends AbstractBCSpider {

	public ArcAnalysisSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	protected void dealWithTargetBlock() {
		// TODO Auto-generated method stub
		Information information = targetBlock.getInformation();
		String informationString = information.getOriginalString();
		if (informationString.startsWith(ScriptDeclaration.EArc)){
			//检查执行路线的弧线声明
			informationString = 
					informationString.substring(ScriptDeclaration.EArc.length());
			if (RStringChecker.checkArc(informationString)){
				information.setType(InformationType.ARC);
			} else {
				information.setType(InformationType.VOID);
				information.appendDescription("EArc弧线声明非法。");
			}
			
		} else if (informationString.startsWith(ScriptDeclaration.PArc)){
			//检查参数连接路线的弧线声明
			informationString = 
					informationString.substring(ScriptDeclaration.PArc.length());
			if (RStringChecker.checkArc(informationString)){
				information.setType(InformationType.ARC);
			} else {
				information.setType(InformationType.VOID);
				information.appendDescription("PArc弧线声明非法。");
			}
		} else {
			information.setType(InformationType.VOID);
			information.appendDescription("弧线声明解析过程中发现的非弧线声明字段。");
		}

	}

}
