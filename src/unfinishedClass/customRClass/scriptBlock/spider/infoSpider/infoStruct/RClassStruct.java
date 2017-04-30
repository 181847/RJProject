package unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct;

import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * 用于直接存储RClass的结构信息的类型，
 * 这个类型作为脚本文件和直接的RClass对象的过渡对象。
 */
public class RClassStruct {
	/**
	 * RClass来源的工程文件和内部的脚本文件路径。
	 */
	protected String loadPath;
	
	/**
	 * RClass类型。
	 */
	protected InformationType rClassType;
	
	/**
	 * RClass名字，
	 */
	protected String rClassName;
	
	/**
	 * 父类信息。
	 */
	protected RClassRefStruct extend;
	
	/**
	 * 接口信息集合。
	 */
	protected ImplementSet implementSet;
	
	/**
	 * 成员变量集合。
	 */
	protected VarFieldStruct memberVarSet;
	
	/**
	 * 构造Function信息。
	 */
	protected FunctionStruct conFun;
	
	/**
	 * 静态Function集合
	 */
	protected FunSet staticFunSet;
	
	/**
	 * 成员Function以及抽象Function集合。
	 */
	protected FunSet funSet;
	
	public RClassStruct(){
		rClassType = InformationType.VOID;
	}

	/**
	 * 添加RClass的类型。
	 * @param type
	 * 		RClass的类型，要求只能是一下三种之一：
	 * 		InformationType.INTERFACE/ABSTRACT/CLASS。
	 */
	public void setType(InformationType type) {
		rClassType = type;
	}
	
	/**
	 * 添加RClass的名字。
	 * @param name
	 * 		RClass的名字。
	 */
	public void setName(String name) {
		rClassName = name;
	}

	/**
	 * 添加RClass继承的父类的名字，
	 * 一个RClass只能继承一个父类。
	 * @param superName
	 * 		父类的名字。
	 */
	public void setExtends(String superName) {
		extend = new RClassRefStruct(superName);
	}

	/**
	 * 添加接口继承的父类名称集合。
	 * @param iSet
	 * 		这个对象当中包含多个接口RClass的名字。
	 */
	public void setImplementSet(ImplementSet iSet){
		implementSet = iSet;
	}

	/**
	 * 添加成员变量集合，
	 * 这个集合中包含静态成员变量。
	 * @param varFieldStruct
	 * 		成员变量集合。
	 */
	public void setMemberSet(VarFieldStruct varFieldStruct) {
		memberVarSet = varFieldStruct;
	}

	/**
	 * 添加构造Function，
	 * 一个RClass只允许有一个构造Function。
	 * @param funStruct
	 * 		构造Function的结构信息。
	 */
	public void setConFunStruct(FunctionStruct conFunStruct) {
		conFun = conFunStruct;
	}

	/**
	 * 添加静态Function，
	 * 一个RClass可以有多个静态Function。
	 * @param funStruct
	 * 		静态Function结构信息。
	 */
	public void addStaticFunStruct(FunctionStruct staticFunStruct) {
		staticFunSet.addFun(staticFunStruct);
	}

	/**
	 * 添加普通成员Function，
	 * 可以有多个普通成员Function。
	 * @param funStruct
	 * 		普通成员Function结构信息。
	 */
	public void addFunStruct(FunctionStruct funStruct) {
		funSet.addFun(funStruct);
	}

	/**
	 * 添加抽象Function，
	 * 可以有多个抽象Function。
	 * @param funStruct
	 * 		抽象Function结构信息。
	 */
	public void addAbstractFunStruct(FunctionStruct funStruct) {
		funSet.addFun(funStruct);
	}

	/**
	 * 设置当前RClass是从哪个工程文件中的脚本文件加载进来的。
	 * @param path
	 * 		工程文件路径和脚本文件路径。
	 */
	public void setLoadPath(String path) {
		loadPath = path;
	}

	/**
	 * @return
	 * 		RClassStruct的类型。
	 */
	public InformationType getType() {
		return rClassType;
	}
}
