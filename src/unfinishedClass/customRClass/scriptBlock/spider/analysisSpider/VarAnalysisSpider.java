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
		
		//这里变量可以声明为具体的类型，
		//也可以声明为一个泛型，
		//RStringChecker中还需要在类型不满足RClass命名的时候去检查是否符合泛参的命名。
		if (RStringChecker.checkVar(informationString)){
			information.setType(assignedType);
			
			//检查变量是否使用了泛型
			ScriptBlock subBlock = targetBlock.getSub();
			if (subBlock != null){
				
				//如果被检查的变量声明是一个泛参，
				//即直接引用RClass或者Function中声明的泛参（比如T）作为变量的类型,
				//则这种变量下面不能有具体的泛参实现，（不能有T<int, String>这样的声明，因为T本来就是未知的）
				//将信息的类型重新设为VOID，
				//使得在GrammarSpider检查到这里的时候能够发现这个错误。
				if (RStringChecker.isVarType_a_GenericParam(informationString)){
					//记录错误信息
					information.setType(InformationType.VOID);
					information.appendDescription("变量声明格式非法，"
							+ "变量直接使用了RClass或者Function定义的泛参作为类型（比如T），"
							+ "它下面的子信息不能对这个未知泛参的泛参进行指定"
							+ "（不能有T<int, String>这样的声明，因为T本来就是未知的）。");
				} else {
					new GenericsAssignAnalysisSpider(subBlock)
						.workUntilEnd();
				}
			}
		} else {
			 //包含非法字符的信息，或者不符合变量声明格式
			 information.setType(InformationType.VOID);
			 information.appendDescription("变量声明格式非法。");
		}
	}

}
