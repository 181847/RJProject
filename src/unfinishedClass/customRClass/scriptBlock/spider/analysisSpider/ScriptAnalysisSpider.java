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
			//构造Function声明，注意构造Function没有名字，
			//所以这里查看是否是构造Function信息块声明需要看这个声明字符串是不是
			//equal(ScriptDeclaration.conFun)，
			//而不是像下面的其他类型的Function检查声明字符串是不是以相应的声明开头
			//startsWith(……)。
			setInfo(InformationType.DECLAR_FUN_CONFUN);
			//分析构造Function
			analysisFun();
					
		} else if (targetInfoString.startsWith(ScriptDeclaration.declar_fun_static)){
			//进一步分析静态Function的名字是否符合命名规范。
			if (analysisFunDeclar(
					ScriptDeclaration.declar_fun_static,
					InformationType.DECLAR_FUN_STATIC)){
				//符合组件命名规范的情况下，
				//对子链进行Function分析。
				analysisFun();
			}
					
		} else if (targetInfoString.startsWith(ScriptDeclaration.declar_fun)){
			//进一步分析Function的名字是否符合命名规范。
			if (analysisFunDeclar(
					ScriptDeclaration.declar_fun,
					InformationType.DECLAR_FUN)){
				//符合组件命名规范的情况下，
				//对子链进行Function分析。
				analysisFun();
			}
					
		} else if (targetInfoString.startsWith(ScriptDeclaration.declar_fun_abstract)){
			//进一步分析抽象Function的名字是否符合命名规范。
			if (analysisFunDeclar(
					ScriptDeclaration.declar_fun_abstract,
					InformationType.DECLAR_FUN_ABSTRACT)){
				//符合组件命名规范的情况下，
				//对子链进行Function分析。
				analysisFun();
			}
				
		} else {
			//非法信息声明
			setInfo(InformationType.VOID);
			targetInformation.appendDescription("类定义阶段发现的非法信息。");
		}
	}
	
	/**
	 * 此方法单独对当前的targetInfoString进行分析，
	 * 要求其中 declar + 分割符 后面的字符串符合组件命名规范，
	 * 如果符合就会设置相应的infoType；
	 * 如果不符合就会设置空白标签，并追加错误信息。
	 * 分割符参考ScriptDeclaration.generalSplit。
	 * 注意，本方法的操作很蠢，
	 * 直接将targetInfoString剪掉前面对应(declar + 分割符)长度的字符串，
	 * 对剩余的部分检查是否符合组件命名规范。
	 * 不保证targetInfoString一定以declar开头。
	 * @param declar
	 * 		声明字段，
	 * 		形如“StaticFunction:”。
	 * @param infoType
	 * 		被设置的标签。
	 * @return
	 * 		返回当前对Function的命名是否符合组件命名规范。
	 */
	private boolean analysisFunDeclar(String declar, InformationType infoType) {
		if (RStringChecker.checkComponentName(	//检查组件命名规范。
				targetInfoString.substring(
						//剔除声明字段。
						declar.length()
						//剔除分割符。
						+ ScriptDeclaration.generalSplit.length()))) {
			//符合组件命名规范，
			//设置标签。
			setInfo(infoType);
			return true;
		} else {
			setInfo_VOID();
			descriptInfo(declar + "之后的部分不满足组件命名规范。");
			return false;
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
