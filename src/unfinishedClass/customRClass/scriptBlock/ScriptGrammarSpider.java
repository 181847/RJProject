package unfinishedClass.customRClass.scriptBlock;

import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.ReasonedErrorSpider;

/**
 * 假设脚本定义的是AbstractRClass，
 * 检查必要信息是否包含，
 * 例如一个脚本中必须有NAME声明，
 * NAME下面也必须有且只有一个类名的声明，
 * 如果没有它的话就算是语法错误。
 * 而这个类名由先前的AnalysisSpider进行分析后只会保证这个类名
 * 是一个正确的符合字符规范的类名，不会包含非法字符、
 * 有包的名字，但是这个类名是否和别的类名重复 不属于语法检查的范畴。
 * 语法检查还需要避免无用信息，
 * 即类型为VOID的Information，
 * 如果包含此类信息，
 * 无论VOID是在什么地方出现的，
 * 当前脚本都会被认为发生语法错误，
 * 不允许加载。
 */
public class ScriptGrammarSpider extends ReasonedErrorSpider {
	protected ReasonedErrorSpider typeGS;
	protected ReasonedErrorSpider nameGS;
	protected ReasonedErrorSpider extendsGS;
	protected ReasonedErrorSpider implementsGS;
	protected ReasonedErrorSpider memberGS;
	protected ReasonedErrorSpider conFunGS;
	
	
	protected ReasonedErrorSpider staticFunGS;
	protected ReasonedErrorSpider funGS;
	protected ReasonedErrorSpider abstractGS;


	/**
	 * 默认Spider没有发生错误。
	 * @param targetBlock
	 * 		目标Block节点。
	 */
	public ScriptGrammarSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}
	
	public ScriptGrammarSpider(ScriptBlock targetBlock, boolean initError){
		super(targetBlock, initError);
	}

	@Override
	protected void dealWithTargetBlock() {
		InformationType infoType = targetBlock.getInformation().getType();
		
		switch(infoType){
		case TYPE:
			dealWith_TYPE();
			break;
		case NAME:
			dealWith_NAME();
			break;
		case EXTENDS:
			dealWith_EXTENDS();
			break;
		case IMPLEMENTS:
			dealWith_IMPLEMENTS();
			break;
		case MEMBER:
			dealWith_MEMBER();
			break;
		case CONFUN:
			dealWith_CONFUN();
			break;
		case STATICFUN:
			dealWith_STATICFUN();
			break;
		case FUN:
			dealWith_FUN();
			break;
		case ABSTRACTFUN:
			dealWith_ABSTRACTFUN();
			break;
		case VOID:
			dealWith_VOID();
			break;
		default:
			break;
		}

	}

	/**
	 * 已知targetBlock的InformationType是VOID类型，
	 * 毫无疑问，脚本信息中不允许出现任何无用信息，
	 * 当前脚本发生语法错误，不允许加载。
	 */
	private void dealWith_VOID() {
		appendReason("RClass的脚本中发现VOID信息。");
		error = true;
	}

	/**
	 * 已知targetBlock的InformationType是ABSTRACTFUN类型，
	 * 进行相应的语法检查，
	 * ABSTRACTFUN的出现次数不受限制可以为0~无穷，
	 * 每次只需要保证ABSTRACTFUN内部的Function定义无语法错误。
	 */
	private void dealWith_ABSTRACTFUN() {
		ScriptBlock subBlock = targetBlock.getSub();
		if (subBlock == null){
			//IMPLEMENTS声明的下面没有具体的Function定义，语法错误
			appendReason("ABSTRACTFUN型的信息下面没有具体的抽象Function定义。");
			error = true;
			return;
		}
		
		abstractGS = new AbstractFunGrammarSpider(subBlock);
		abstractGS.workUntilEnd();
		
		if (abstractGS.occurredError()){
			appendReason(abstractGS.getErrorReason());
			error = true;
			return;
		}
	}

	/**
	 * 已知targetBlock的InformationType是FUN类型，
	 * 进行相应的语法检查，
	 * FUN的出现次数不受限制可以为0~无穷，
	 * 每次只需要保证FUN内部的Function定义无语法错误。
	 */
	private void dealWith_FUN() {
		ScriptBlock subBlock = targetBlock.getSub();
		if (subBlock == null){
			//IMPLEMENTS声明的下面没有具体的Function定义，语法错误
			appendReason("FUN型的信息下面没有具体的Function定义。");
			error = true;
			return;
		}
		
		funGS = new FunGrammarSpider(subBlock);
		funGS.workUntilEnd();
		
		if (funGS.occurredError()){
			appendReason(funGS.getErrorReason());
			error = true;
			return;
		}
	}

	/**
	 * 已知targetBlock的InformationType是STATICFUN类型，
	 * 进行相应的语法检查，
	 * STATICFUN的出现次数不受限制可以为0~无穷，
	 * 每次只需要保证STATICFUN内部的Function定义无语法错误。
	 */
	private void dealWith_STATICFUN() {
		ScriptBlock subBlock = targetBlock.getSub();
		if (subBlock == null){
			//IMPLEMENTS声明的下面没有具体的Function定义，语法错误
			appendReason("STATICFUN型的信息下面没有具体的静态Function定义。");
			error = true;
			return;
		}
		
		staticFunGS = new StaticFunGrammarSpider(subBlock);
		staticFunGS.workUntilEnd();
		
		if (staticFunGS.occurredError()){
			appendReason(staticFunGS.getErrorReason());
			error = true;
			return;
		}
	}

	/**
	 * 针对一个构造Function的定义脚本进行检查，
	 * 保证内部的组件构成符合构造Function的要求，
	 * 要求同一个脚本中不得出现两个CONFUN声明。
	 */
	private void dealWith_CONFUN() {
		if (conFunGS != null){
			//conFunGS不为null，
			//说明先前检查过一次CONFUN，
			//这个脚本中有两个CONFUN声明，语法错误
			appendReason("定义中多次发现CONFUN型的信息。");
			error = true;
			return;
		}
		
		ScriptBlock subBlock = targetBlock.getSub();
		if (subBlock == null){
			//IMPLEMENTS声明的下面没有具体的Function定义，语法错误
			appendReason("CONFUN型的信息下面没有具体的Function定义。");
			error = true;
			return;
		}
		
		conFunGS = new ConFunGrammarSpider(subBlock);
		conFunGS.workUntilEnd();
		
		if (conFunGS.occurredError()){
			//具体的类型定义检查中发现错误，语法错误
			appendReason(conFunGS.getErrorReason());
			error = true;
			return;
		}
	}

	/**
	 * 已知targetBlock的InformationType是MEMBER类型，
	 * 进行相应的语法检查，
	 * 要求同一个脚本中不得出现两个MEMBER声明，
	 * MEMBER下面至少有一个STATIC或者VAR的Information。
	 */
	private void dealWith_MEMBER() {
		if (memberGS != null){
			//implementsGS不为null，
			//说明先前检查过一次IMPLEMENTS，
			//这个脚本中有两个IMPLEMENTS声明，语法错误
			appendReason("定义中多次发现MEMBER型的信息。");
			error = true;
			return;
		}
		
		ScriptBlock subBlock = targetBlock.getSub();
		if (subBlock == null){
			//IMPLEMENTS声明的下面没有具体的父类接口定义，语法错误
			appendReason("MEMBER型的信息下面没有具体的成员定义。");
			error = true;
			return;
		}
		
		memberGS = new MemberGrammarSpider(subBlock);
		memberGS.workUntilEnd();
		
		if (memberGS.occurredError()){
			//具体的类型定义检查中发现错误，语法错误
			appendReason(memberGS.getErrorReason());
			error = true;
			return;
		}
	}

	/**
	 * 已知targetBlock的InformationType是IMPLEMENTS类型，
	 * 进行相应的语法检查，
	 * 要求同一个脚本中不得出现两个IMPLEMENTS声明，
	 * IMPLEMENTS下面至少有一个InformationType为CLASSNAME的Information。
	 */
	private void dealWith_IMPLEMENTS() {
		if (implementsGS != null){
			//implementsGS不为null，
			//说明先前检查过一次IMPLEMENTS，
			//这个脚本中有两个IMPLEMENTS声明，语法错误
			appendReason("定义中多次发现IMPLEMENTS型的信息。");
			error = true;
			return;
		}
		
		ScriptBlock subBlock = targetBlock.getSub();
		if (subBlock == null){
			//IMPLEMENTS声明的下面没有具体的父类接口定义，语法错误
			appendReason("IMPLEMENTS型的信息下面没有具体的父类接口定义。");
			error = true;
			return;
		}
		
		implementsGS = new ImplementsGrammarSpider(subBlock);
		implementsGS.workUntilEnd();
		
		if (implementsGS.occurredError()){
			//具体的类型定义检查中发现错误，语法错误
			appendReason(implementsGS.getErrorReason());
			error = true;
			return;
		}
	}

	/**
	 * 已知targetBlock的InformationType是Extends类型，
	 * 进行相应的语法检查，
	 * 要求同一个脚本中不得出现两个Extends声明，
	 * Extends下面有且只有一个InformationType为CLASSNAME的Information，
	 * 此检查过程类似Name的检查，
	 */
	private void dealWith_EXTENDS() {
		if (extendsGS != null){
			//extendsGS不为null，
			//说明先前检查过一次EXTENDS，
			//这个脚本中有两个EXTENDS声明，语法错误
			appendReason("定义中多次发现EXTENDS型的信息。");
			error = true;
			return;
		}
		
		ScriptBlock subBlock = targetBlock.getSub();
		if (subBlock == null){
			//Extends声明的下面没有具体的父类定义，语法错误
			appendReason("EXTENDS型的信息下面没有具体的父类定义。");
			error = true;
			return;
		}
		
		extendsGS = new ExtendsGrammarSpider(subBlock);
		extendsGS.workUntilEnd();
		
		if (extendsGS.occurredError()){
			//具体的类型定义检查中发现错误，语法错误
			appendReason(extendsGS.getErrorReason());
			error = true;
			return;
		}
	}

	/**
	 * 已知targetBlock的InformationType是NAME类型，
	 * 进行相应的语法检查，
	 * 要求同一个脚本中不得出现两个NAME声明，
	 * NAME下面有且只有一个InformationType为CLASSNAME的Information。
	 */
	private void dealWith_NAME() {
		if (nameGS != null){
			//nameGS不为null，
			//说明先前检查过一次NAME，
			//这个脚本中有两个NAME声明，语法错误
			appendReason("定义中多次发现NAME型的信息。");
			error = true;
			return;
		}
		
		ScriptBlock subBlock = targetBlock.getSub();
		if (subBlock == null){
			//TYPE声明的下面没有具体的类型定义，语法错误
			appendReason("NAME型的信息下面没有具体的名称定义。");
			error = true;
			return;
		}
		
		nameGS = new NameGrammarSpider(subBlock);
		nameGS.workUntilEnd();
		
		if (nameGS.occurredError()){
			//具体的类型定义检查中发现错误，语法错误
			appendReason(nameGS.getErrorReason());
			error = true;
			return;
		}
	}

	/**
	 * 已知targetBlock的InformationType是TYPE类型，
	 * 进行相应的语法检查，
	 * 要求同一个脚本中不得出现两个TYPE声明。
	 */
	private void dealWith_TYPE() {
		if (typeGS != null){
			//typeGS不为null，
			//说明先前检查过一次TYPE，
			//这个脚本中有两个TYPE声明，语法错误
			appendReason("定义中多次发现TYPE型的信息。");
			error = true;
			return;
		}
		
		ScriptBlock subBlock = targetBlock.getSub();
		if (subBlock == null){
			//TYPE声明的下面没有具体的类型定义，语法错误
			appendReason("TYPE型的信息下面没有具体的类型定义。");
			error = true;
			return;
		}
		
		typeGS = new TypeGrammarSpider(subBlock);
		typeGS.workUntilEnd();
		
		if (typeGS.occurredError()){
			//具体的类型定义检查中发现错误，语法错误
			appendReason(typeGS.getErrorReason());
			error = true;
			return;
		}
	}
}
