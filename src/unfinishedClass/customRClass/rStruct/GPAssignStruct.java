package unfinishedClass.customRClass.rStruct;

import unfinishedClass.Exception.RStructException.CodeRefuseException;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct;
import unfinishedClass.customRClass.rStruct.baseRStruct.RStruct_contain_name_classRef;
import unfinishedClass.customRClass.rStruct.detailInterface.IGPAssignStruct;
import unfinishedClass.customRClass.scriptBlock.ScriptDeclaration;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * 泛参指定结构，
 * 包括一个原泛参名，
 * 一个对应泛参名的类型引用，
 * 内部存储的type类型表示当前类型引用是另一个泛参，
 * 还是一个实类型（但是不提供对type进行修改的方法实现），
 * GPAssignStruct有关增加其他泛参指定的方法是将相关方法，
 * 映射到内部存储的RClassRef上。
 */
public class GPAssignStruct
extends RStruct_contain_name_classRef
implements IGPAssignStruct, IRStruct {

	/**
	 * @param name
	 * 		名字需要符合组件命名规范。
	 */
	@Override
	public void defineName(String name) {
		//要求名字满足组件命名规范。
		this.defineNameAsComponentName(name);
	}
	
	/**
	 * 同时指定被指配的泛参名和对应的类型引用的名字
	 * （泛参名或者实类型名）。
	 * @param assign
	 * 		形如“T: basic.Integer”，其中“T"就是被指配泛参名，
	 * 		“basic.Integer”就是用于指配的类型引用。
	 */
	public void defineAssign(String assign) {
		String[] gpAssigns = assign.split(
				ScriptDeclaration.colon + ScriptDeclaration.generalSplit);
		
		if (gpAssigns.length != 2) {
			throw new IllegalArgumentException("泛参指定一次性设定泛参名和引用类型时，"
					+ "传入参数不能分为泛参名和引用类型名。");
		}
		
		//定义被指定的泛参名。
		defineName(gpAssigns[0]);
		//定义引用类型的名字。
		classRef.defineName(gpAssigns[1]);
	}

	/**
	 * 对泛参指配中指配的类型进行泛参指配。
	 */
	@Override
	public void defineGPAssigns_by_RSet(RSet<GPAssignStruct> gpaSet) {
		if (classRef == null) {
			new NullPointerException("泛参指配结构中被指配类型引用为null，"
					+ "无法通过集合对类型引用添加子项泛参指配。");
		}
	}

	/**
	 * 对泛参指配中指配的类型进行泛参指配。
	 */
	@Override
	public void defineGPAssign(GPAssignStruct gpaStruct) {
		if (classRef == null) {
			new NullPointerException("泛参指配结构中被指配类型引用为null，"
					+ "无法通过集合对类型引用添加子项泛参指配。");
		}
		//增加泛参指配的方法映射到classRef中。
		classRef.defineGPAssign(gpaStruct);
	}

	/**
	 * 此方法没有作用，
	 * 请不要调用，
	 * 此方法一调用就会抛出CodeRefuseException。
	 * @throws	CodeRefuseException
	 * 		一定会抛出这个异常。
	 */
	@Override
	public void defineType(InformationType type) {
		//不对type进行设置，动态的从classRef中获取泛参指配类型。
		
		throw new CodeRefuseException(
				"不能对GPAssignStruct的类型进行手动设置，"
				+ "它的类型有内部的引用类型classRef决定。");
	}

	@Override
	public RSet<GPAssignStruct> getGPAssignSet() {
		return classRef.getGPAssignSet();
	}

	/**
	 * 获取RStruct类型，
	 * 实际上获取的就是内部引用的classRef的类型。
	 */
	@Override
	public InformationType getType() {
		return classRef.getType();
	}

}
