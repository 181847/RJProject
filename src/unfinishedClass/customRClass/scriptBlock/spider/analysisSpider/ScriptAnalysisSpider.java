package unfinishedClass.customRClass.scriptBlock.spider.analysisSpider;

import unfinishedClass.customRClass.RStringChecker;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.ScriptDeclaration;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;
import unfinishedClass.customRClass.scriptBlock.spider.CountableSpider;

/**
 * 为脚本Block中的顶层Information设置InformationType，
 * 方便后续的语法检查区分不同的声明字段。
 */
public class ScriptAnalysisSpider extends CountableSpider {

	public ScriptAnalysisSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	public void countWork() {
		if (targetInfoString.equals(ScriptDeclaration.declar_type)){
			//类型声明
			setInfo(InformationType.DECLAR_TYPE);
			//为类型声明之下的信息赋予InformationType
			if (hasSubBlock){
				new TypeAnalysisSpider(subBlock)
					.workUntilEnd();
			}
			
		} else if (targetInfoString.equals(ScriptDeclaration.declar_name)){
			//名字声明
			setInfo(InformationType.DECLAR_NAME);
			//为名字声明下面的信息赋予InformationType
			if (hasSubBlock){
				//注意这里只保证有一个名字
				new NameAnalysisSpider(subBlock)
					.workUntilEnd();
			}
			
		} else if (targetInfoString.equals(ScriptDeclaration.declar_GPs)){
			//泛参声明部分
			setInfo(InformationType.DECLAR_GEN_PARAMS);
			if (hasSubBlock){
				new GenericParameterAnalysisSpider(subBlock)
					.workUntilEnd();
			}
			
		} else if (targetInfoString.equals(ScriptDeclaration.declar_extends)){
			//父类声明
			setInfo(InformationType.DECLAR_EXTENDS);
			//为父类声明下面的信息赋予InformationType，
			//同时会检查泛型信息。
			if (hasSubBlock){
				new RClassRefAnalysisSpider(subBlock, 1)
					.workUntilEnd();
			}
				
		} else if (targetInfoString.equals(ScriptDeclaration.declar_implements)){
			//父类接口声明
			setInfo(InformationType.DECLAR_IMPLEMENTS);
			//为父类接口声明下面的信息赋予InformationType，
			//同时会检查泛型信息。
			if (hasSubBlock){
				new RClassRefAnalysisSpider(subBlock)
					.workUntilEnd();
			}
				
		} else if (targetInfoString.equals(ScriptDeclaration.declar_members)){
			//成员声明
			setInfo(InformationType.DECLAR_MEMBERS);
			//为成员声明下面的信息赋予InformationType
			if (hasSubBlock){
				new VarFieldAnalysisSpider(subBlock)
					.workUntilEnd();
			}
					
		} else if (targetInfoString.equals(ScriptDeclaration.declar_fun_confun)){
			//构造Function声明，注意构造Function没有Function的名字，
			//所以这里查看是否是构造Function信息块声明需要看这个声明字符串是不是
			//equal(ScriptDeclaration.conFun)，
			//而不是像下面的其他类型的Function检查声明字符串是不是以相应的声明开头
			//startsWith(……)。
			setInfo(InformationType.DECLAR_FUN_CONFUN);
			//为构造Function声明下面的信息赋予InformationType
			analysisFun();
					
		} else if (targetInfoString.startsWith(ScriptDeclaration.declar_fun_static)){
			//静态Function声明
			setInfo(InformationType.DECLAR_FUN_STATIC);
			
			//为静态Function声明下面的信息赋予InformationType
			analysisFun();
					
		} else if (targetInfoString.startsWith(ScriptDeclaration.declar_fun)){
			//成员Function声明
			setInfo(InformationType.DECLAR_FUN);
			//为成员Function声明下面的信息赋予InformationType
			analysisFun();
					
		} else if (targetInfoString.startsWith(ScriptDeclaration.declar_fun_abstract)){
			//抽象Function声明
			setInfo(InformationType.DECLAR_FUN_ABSTRACT);
			//为抽象Function声明下面的信息赋予InformationType
			analysisFun();
				
		} else {
			//非法信息声明
			setInfo(InformationType.VOID);
		}
	}
	
	/**
	 * 这个方法检查subBlock是存在，
	 * 如果存在，就进行Function分析。
	 */
	private void analysisFun(){
		if (hasSubBlock){
			new FunAnalysisSpider(subBlock)
				.workUntilEnd();
		}
	}

}
