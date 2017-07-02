package unfinishedClass.customRClass.scriptBlock.spider.grammarSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.basicToolSpider.ReasonedErrorSpider;

/**
 * 检查必要的信息必须包含：
 * 顶层必须包含TYPE：
 * 以及不能包含标记为VOID额Block，
 * 潍坊
 */
public class ScriptGrammarSpider extends GrammarSpider {
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
	 * 默认Spider发生错误。
	 * @param targetBlock
	 * 		目标Block节点。
	 */
	public ScriptGrammarSpider(ScriptBlock targetBlock) {
		super(targetBlock, "RClass脚本检查");
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
			dealWith_Unexpected();
			break;
		}//switch
	}//dealWithTargetBlock

	/**
	 * 已知targetBlock的InformationType是ABSTRACTFUN类型，
	 * 进行相应的语法检查，
	 * ABSTRACTFUN的出现次数不受限制可以为0~无穷，
	 * 每次只需要保证ABSTRACTFUN内部的Function定义无语法错误。
	 */
	private void dealWith_ABSTRACTFUN() {
		foundOneTaggle();
		ScriptBlock subBlock = targetBlock.getSub();
		if (subBlock == null){
			//IMPLEMENTS声明的下面没有具体的Function定义，语法错误
			appendReason("ABSTRACTFUN型的信息下面没有具体的抽象Function定义。", false);
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
		foundOneTaggle();
		ScriptBlock subBlock = targetBlock.getSub();
		if (subBlock == null){
			//IMPLEMENTS声明的下面没有具体的Function定义，语法错误
			appendReason("FUN型的信息下面没有具体的Function定义。", false);
			error = true;
			return;
		}
		
		funGS = new FunGrammarSpider(subBlock, "普通Function检查");
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
		foundOneTaggle();
		ScriptBlock subBlock = targetBlock.getSub();
		if (subBlock == null){
			//IMPLEMENTS声明的下面没有具体的Function定义，语法错误
			appendReason("STATICFUN型的信息下面没有具体的静态Function定义。", false);
			error = true;
			return;
		}
		
		staticFunGS = new FunGrammarSpider(subBlock, "静态Function检查");
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
		foundOneTaggle();
		if (conFunGS != null){
			//conFunGS不为null，
			//说明先前检查过一次CONFUN，
			//这个脚本中有两个CONFUN声明，语法错误
			appendReason("定义中多次发现CONFUN型的信息。", false);
			error = true;
			return;
		}
		
		ScriptBlock subBlock = targetBlock.getSub();
		if (subBlock == null){
			//CONFUN声明的下面没有具体的Function定义，语法错误
			appendReason("CONFUN型的信息下面没有具体的Function定义。", false);
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
		foundOneTaggle();
		if (memberGS != null){
			//memberGS不为null，
			//说明先前检查过一次MEMBER，
			//这个脚本中有两个MEMBER声明，语法错误
			appendReason("定义中多次发现MEMBER型的信息。", false);
			error = true;
			return;
		}
		
		ScriptBlock subBlock = targetBlock.getSub();
		if (subBlock == null){
			//IMPLEMENTS声明的下面没有具体的父类接口定义，语法错误
			appendReason("MEMBER型的信息下面没有具体的成员定义。", false);
			error = true;
			return;
		}
		
		memberGS = new VarFieldGrammarSpider(subBlock, "RClass的成员变量检查");
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
		foundOneTaggle();
		if (implementsGS != null){
			//implementsGS不为null，
			//说明先前检查过一次IMPLEMENTS，
			//这个脚本中有两个IMPLEMENTS声明，语法错误
			appendReason("定义中多次发现IMPLEMENTS型的信息。", false);
			error = true;
			return;
		}
		
		ScriptBlock subBlock = targetBlock.getSub();
		if (subBlock == null){
			//IMPLEMENTS声明的下面没有具体的父类接口定义，语法错误
			appendReason("IMPLEMENTS型的信息下面没有具体的父类接口定义。", false);
			error = true;
			return;
		}
		
		//没有数量限制的类名检查
		implementsGS = new ClassNameGrammarSpider(subBlock, false, "RClass实现的接口名检查");
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
		foundOneTaggle();
		if (extendsGS != null){
			//extendsGS不为null，
			//说明先前检查过一次EXTENDS，
			//这个脚本中有两个EXTENDS声明，语法错误
			appendReason("定义中多次发现EXTENDS型的信息。", false);
			error = true;
			return;
		}
		
		ScriptBlock subBlock = targetBlock.getSub();
		if (subBlock == null){
			//Extends声明的下面没有具体的父类定义，语法错误
			appendReason("EXTENDS型的信息下面没有具体的父类定义。", false);
			error = true;
			return;
		}
		
		extendsGS = new ClassNameGrammarSpider(subBlock, true, "RClass继承的父类名检查");
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
		foundOneTaggle();
		if (nameGS != null){
			//nameGS不为null，
			//说明先前检查过一次NAME，
			//这个脚本中有两个NAME声明，语法错误
			appendReason("定义中多次发现NAME型的信息。", false);
			error = true;
			return;
		}
		
		ScriptBlock subBlock = targetBlock.getSub();
		if (subBlock == null){
			//TYPE声明的下面没有具体的类型定义，语法错误
			appendReason("NAME型的信息下面没有具体的名称定义。", false);
			error = true;
			return;
		}
		
		nameGS = new ClassNameGrammarSpider(subBlock, true, "RClass的类名检查");
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
		foundOneTaggle();
		if (typeGS != null){
			//typeGS不为null，
			//说明先前检查过一次TYPE，
			//这个脚本中有两个TYPE声明，语法错误
			appendReason("定义中多次发现TYPE型的信息。", false);
			error = true;
			return;
		}
		
		ScriptBlock subBlock = targetBlock.getSub();
		if (subBlock == null){
			//TYPE声明的下面没有具体的类型定义，语法错误
			appendReason("TYPE型的信息下面没有具体的类型定义。", false);
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
	
	/**
	 * 通过调用这个方法来统一的整合整个脚本中的检查信息，
	 * 如果typeGS、nameGS为null的话，
	 * 表示脚本中根本没有合法的类型和名字声明，
	 * 设置Spider发生的错误，
	 * 并且返回false；
	 * 如果typeGS、nameGS不是null的话，直接返回成员error。
	 */
	@Override
	public boolean occurredError(){
		if (typeGS == null){
			error = true;
			appendReason("脚本中没有发现类型声明");
		}
		if (nameGS == null){
			error = true;
			appendReason("脚本中没有发现类的名称声明");
		}
		return error;
	}
}
