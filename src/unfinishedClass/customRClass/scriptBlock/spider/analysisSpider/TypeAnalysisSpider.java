package unfinishedClass.customRClass.scriptBlock.spider.analysisSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.ScriptDeclaration;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;

/**
 * 分析类型声明内容。
 */
public class TypeAnalysisSpider extends AbstractBCSpider {

	public TypeAnalysisSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	protected void dealWithTargetBlock() {
		Information information = targetBlock.getInformation();
		String informationString = information.toString();
		
		if (informationString.equals(ScriptDeclaration.interfaceType)){
			information.setType(InformationType.INTERFACE);
		} else if (informationString.equals(ScriptDeclaration.abstractType)){
			information.setType(InformationType.ABSTRACT);
		} else if (informationString.equals(ScriptDeclaration.classType)){
			information.setType(InformationType.CLASS);
		} else {
			information.setType(InformationType.VOID);
			information.appendDescription("类型声明内容不是以下三种中的字符串："
					+ ScriptDeclaration.interfaceType
					+ "/" + ScriptDeclaration.abstractType
					+ "/" + ScriptDeclaration.classType);
		}
	}

}
