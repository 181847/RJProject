package unfinishedClass.customRClass.scriptBlock;

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
		
		if (informationString.equals(ScriptBlockHelper.interfaceType)){
			information.setType(InformationType.INTERFACE);
		} else if (informationString.equals(ScriptBlockHelper.abstractType)){
			information.setType(InformationType.ABSTRACT);
		} else if (informationString.equals(ScriptBlockHelper.classType)){
			information.setType(InformationType.CLASS);
		} else {
			information.setType(InformationType.VOID);
			information.appendDescription("类型声明内容不是以下三种中的字符串："
					+ ScriptBlockHelper.interfaceType
					+ "/" + ScriptBlockHelper.abstractType
					+ "/" + ScriptBlockHelper.classType);
		}
	}

}
