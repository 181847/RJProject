package unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct;

import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * 用于直接存储RClass的结构信息的类型，
 * 这个类型作为脚本文件和直接的RClass对象的过渡对象。
 */
public class RClassStruct {

	/**
	 * 添加RClass的类型。
	 * @param type
	 * 		RClass的类型，要求只能是一下三种之一：
	 * 		InformationType.INTERFACE/ABSTRACT/CLASS。
	 */
	public void addType(InformationType type) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 添加RClass的名字。
	 * @param name
	 * 		RClass的名字。
	 */
	public void addName(String name) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 添加RClass继承的父类的名字，
	 * 一个RClass只能继承一个父类。
	 * @param superName
	 * 		父类的名字。
	 */
	public void addExtends(String superName) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 添加接口继承的父类名称集合。
	 * @param iSetStruct
	 * 		这个对象当中包含多个接口RClass的名字。
	 */
	public void addImplementSetStruct(ImplementSetStruct iSetStruct){
		//TODO with implementSetStruct
		
	}

	/**
	 * 添加成员变量集合，
	 * 这个集合中包含静态成员变量。
	 * @param varFieldStruct
	 * 		成员变量集合。
	 */
	public void addMemberStruct(VarFieldStruct varFieldStruct) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 添加构造Function，
	 * 一个RClass只允许有一个构造Function。
	 * @param funStruct
	 * 		构造Function的结构信息。
	 */
	public void addConFunStruct(FunctionStruct conFunStruct) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 添加静态Function，
	 * 一个RClass可以有多个静态Function。
	 * @param funStruct
	 * 		静态Function结构信息。
	 */
	public void addStaticFunStruct(FunctionStruct staticFunStruct) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 添加普通成员Function，
	 * 可以有多个普通成员Function。
	 * @param funStruct
	 * 		普通成员Function结构信息。
	 */
	public void addFunStruct(FunctionStruct funStruct) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 添加抽象Function，
	 * 可以有多个抽象Function。
	 * @param funStruct
	 * 		抽象Function结构信息。
	 */
	public void addAbstractFunStruct(FunctionStruct funStruct) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
