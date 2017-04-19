package unfinishedClass.customRClass.scriptBlock.spider.analysisSpider;

import unfinishedClass.customRClass.RStringChecker;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.ScriptDeclaration;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;

/**
 * 为脚本Block中的顶层Information设置InformationType，
 * 方便后续的语法检查区分不同的声明字段。
 */
public class ScriptAnalysisSpider extends AbstractBCSpider {

	public ScriptAnalysisSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	protected void dealWithTargetBlock() {
		ScriptBlock subBlock = targetBlock.getSub();
		Information information = targetBlock.getInformation();
		String informationString = information.getOriginalString();
		
		if (informationString.equals(ScriptDeclaration.type)){
			//类型声明
			information.setType(InformationType.TYPE);
			//为类型声明之下的信息赋予InformationType
			if (subBlock != null){
				new TypeAnalysisSpider(subBlock)
					.workUntilEnd();
			}
			
		} else if (informationString.equals(ScriptDeclaration.name)){
			//名字声明
			information.setType(InformationType.NAME);
			//为名字声明下面的信息赋予InformationType
			if (subBlock != null){
				new NameAnalysisSpider(subBlock)
					.workUntilEnd();
			}
			
		} else if (informationString.equals(ScriptDeclaration.extendsD)){
			//父类声明
			information.setType(InformationType.EXTENDS);
			//为父类声明下面的信息赋予InformationType
			if (subBlock != null){
				new NameAnalysisSpider(subBlock)
					.workUntilEnd();
			}
				
		} else if (informationString.equals(ScriptDeclaration.implementsD)){
			//父类接口声明
			information.setType(InformationType.IMPLEMENTS);
			//为父类接口声明下面的信息赋予InformationType
			if (subBlock != null){
				new NameAnalysisSpider(subBlock)
					.workUntilEnd();
			}
				
		} else if (informationString.equals(ScriptDeclaration.member)){
			//成员声明
			information.setType(InformationType.MEMBER);
			//为成员声明下面的信息赋予InformationType
			if (subBlock != null){
				new VarFieldAnalysisSpider(subBlock)
					.workUntilEnd();
			}
					
		} else if (informationString.equals(ScriptDeclaration.conFun)){
			//构造Function声明
			information.setType(InformationType.CONFUN);
			//为构造Function声明下面的信息赋予InformationType
			if (subBlock != null){
				new FunAnalysisSpider(subBlock)
					.workUntilEnd();
			}
					
		} else if (informationString.startsWith(ScriptDeclaration.staticFun)){
			//静态Function声明
			information.setType(InformationType.STATICFUN);
			
			//为静态Function声明下面的信息赋予InformationType
			if (subBlock != null){
				new FunAnalysisSpider(subBlock)
					.workUntilEnd();
			}
					
		} else if (informationString.startsWith(ScriptDeclaration.function)){
			//成员Function声明
			information.setType(InformationType.FUN);
			//为成员Function声明下面的信息赋予InformationType
			if (subBlock != null){
				new FunAnalysisSpider(subBlock)
					.workUntilEnd();
			}
					
		} else if (informationString.startsWith(ScriptDeclaration.abstractFun)){
			//抽象Function声明
			information.setType(InformationType.ABSTRACTFUN);
			//为抽象Function声明下面的信息赋予InformationType
			if (subBlock != null){
				new FunAnalysisSpider(subBlock)
					.workUntilEnd();
			}
				
		} else {
			//非法信息声明
			information.setType(InformationType.VOID);
		}

	}

}
