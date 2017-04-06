package unfinishedClass.customRClass.scriptBlock;

import unfinishedClass.customRClass.RStringChecker;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;

public class SubFunAnalysisSpider extends AbstractBCSpider {

	public SubFunAnalysisSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	protected void dealWithTargetBlock() {
		Information information = targetBlock.getInformation();
		String informationString = information.getOriginalString();
		
		//检查子Function的位置信息是否正确声明
		int locationStart = informationString.indexOf(ScriptDeclaration.locationStart);
		int locationEnd = informationString.indexOf(ScriptDeclaration.locationEnd);
		if (locationStart != 0 ||
				locationStart > locationEnd){
			information.setType(InformationType.VOID);
			information.appendDescription("子Funtion的位置信息声明出错，位置标志符的顺序不正确，"
					+ "位置开始标志符的位置：" + locationStart + "、位置结束标志符的位置：" + locationEnd);
			return;
			
		}
		
		//检查子Function的Modify信息是否正确声明
		informationString.substring(locationEnd - 1);
		
		locationStart = informationString.lastIndexOf(ScriptDeclaration.modifyStart);
		locationEnd = informationString.lastIndexOf(ScriptDeclaration.modifyEnd);
		if (locationStart > locationEnd){
			information.setType(InformationType.VOID);
			information.appendDescription("子Funtion的Modify信息声明出错，声明标志符的顺序不正确，"
					+ "Modify开始标志符的位置：" + locationStart 
					+ "、Modify结束标志符的位置：" + locationEnd);
			return;
		}
		
		informationString.substring(locationEnd - 1);
		//检查Function的名字是否正确
		if (RStringChecker.checkFunctionName(informationString)){
			information.setType(InformationType.SUBFUN);
		} else {
			information.setType(InformationType.VOID);
			information.appendDescription("子Fun的名称声明中包含非法字符：" 
					+ RStringChecker.getIllegalStrings()
					+ "或者子Fun的名字不符合名字的命名规则，连同类所在的包名在内"
					+ "，子Fun的名字中至少要包含三个名字层次符，例如："
					+ "包名" + ScriptDeclaration.nameHierarchy
					+ "类名" + ScriptDeclaration.nameHierarchy
					+ "Function的名字");
		}
		
	}

}
