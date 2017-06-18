package unfinishedClass.customRClass.scriptBlock.spider.analysisSpider;

import unfinishedClass.customRClass.RStringChecker;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;

/**
 * 分析变量的Spider，
 * 本Spider由于可以分析两个字段的变量，
 * 即“类型 + 名字”这种形式的声明，
 * 所以这个Spider还会用来检查Function的Parameter和Returnval组件声明，
 * 但是由于Paramter和Returnval组件的InformationType不是VAR，
 * 所以这里用一个成员变量 assignedType 来
 * 记录对合法Information所赋予的类型枚举变量，
 * 默认为VAR，
 * 如果有特殊要去可以调用另外一个构造方法来修改赋予的类型。
 */
public class VarAnalysisSpider extends AbstractBCSpider {
	protected InformationType assignedType;

	public VarAnalysisSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		assignedType = InformationType.VAR;
	}
	
	public VarAnalysisSpider(ScriptBlock targetBlock,
			InformationType assignedType){
		super(targetBlock);
		this.assignedType = assignedType;
	}

	@Override
	protected void dealWithTargetBlock() {
		Information information = targetBlock.getInformation();
		String informationString = information.getOriginalString();
		
		if (RStringChecker.checkVar(informationString)){
			information.setType(assignedType);
		} else {
			 //包含非法字符的信息，或者不符合变量声明格式
			 information.setType(InformationType.VOID);
			 information.appendDescription("变量声明格式非法。");
		}
	}

}
