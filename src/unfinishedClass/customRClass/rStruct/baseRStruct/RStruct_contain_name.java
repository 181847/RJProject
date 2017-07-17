package unfinishedClass.customRClass.rStruct.baseRStruct;

import unfinishedClass.Exception.RStructException.CodeRefuseException;
import unfinishedClass.Exception.RStructException.ComponentNameException;
import unfinishedClass.Exception.RStructException.RClassNameException;
import unfinishedClass.customRClass.RStringChecker;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_name;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * 包含名字的RStruct的父类，
 * 本抽象类中定义了两个专门的名字设置方法，
 * 但是没有实现直接的defineName()方法，
 * 子类可以再defineName中自由调用两种特殊的名称设置方法，
 * 来对自己的名字进行约束（要符合组件命名规范还是类名命名规范）。
 */
public abstract class RStruct_contain_name implements IRStruct_contain_name {

	/**
	 * RStruct的名字。
	 */
	protected String name;
	
	/**
	 * 要求name符合组件命名规范的方法，
	 * 如果不符合将会抛出IllegalArgumentException。
	 * @param name
	 * 		期望符合组件命名规范的方法。
	 * @throws ComponentNameException
	 * 		如果name不符合组件命名规范，就会抛出此异常。
	 */
	public void defineNameAsComponentName(String name) {
		//事先检查名字，一旦有问题，抛出异常。
		preCheck(name);
		
		if (RStringChecker.checkComponentName(name)) {
			this.name = name;
		} else {
			//不符合组件命名规范。
			throw new ComponentNameException();
		}
	}
	
	/**
	 * 要求name符合类命名规范的方法，
	 * 如果不符合将会抛出IllegalArgumentException。
	 * @param name
	 * 		期望符合类命名规范的方法。
	 * @throws RClassNameException
	 * 		如果name不符合类命名规范，就会抛出此异常。
	 */
	public void defineNameAsRClassName(String name) {
		//事先检查名字，一旦有问题，抛出异常。
		preCheck(name);
		
		if (RStringChecker.checkRClassName(name)) {
			this.name = name;
		} else {
			//不符合组件命名规范。
			throw new RClassNameException();
		}
	}
	
	/**
	 * 只有name满足组件命名规范、或者类名命名规范时，
	 * 才设置名字。
	 * @param name
	 * 		满足组件命名规范、或者类名命名规范的名字。
	 * @return
	 * 		如果满足组件命名规范，返回CLASS_REF_CL；
	 * 		如果满足类名命名规范，返回CLASS_REF_GP；
	 * @throws	IllegalArgumentException
	 * 		如果name不符合类命名规范、或者类名命名规范
	 * 		，就会抛出此异常。
	 */
	public InformationType defineNameAsAuto(String name) {
		preCheck(name);
		
		if (RStringChecker.checkComponentName(name)) {
			this.name = name;
			return InformationType.CLASS_REF_CL;
		} else if (RStringChecker.checkRClassName(name)) {
			this.name = name;
			return InformationType.CLASS_REF_GP;
		} else {
			throw new IllegalArgumentException(
					"不符合组件命名规范、"
					+ "或者类名命名规范的名字。");
		}
	}
	
	/**
	 * 防止被设置的名字为null或者空串，
	 * 以及不能重复命名。
	 * @param name
	 * 		被定义到RStruct的名字。
	 * @throws IllegalArgumentException
	 * 		当name == null 或者 name 是空串。
	 * @throws CodeRefuseException
	 * 		当前RStruct已经被命名或者this.name != null。
	 */
	protected void preCheck(String name) {
		if (name == null || name.equals("")) {
			//防止名字是null或者空串。
			throw new IllegalArgumentException(
					"定义RStrut的名字是null或者空串。");
		}
		
		if (this.name != null) {
			throw new CodeRefuseException(
					"不能对已命名的对象重命名。");
		}
	}


	/**
	 * @throws CodeRefuseException
	 * 		如果name还是null，即还未设置名字之前，就会抛出这个异常，
	 * 		防止获取非法的名字。
	 */
	public String getName() {
		if (name == null) {
			throw new CodeRefuseException("名称还未设置，不能获取名字。");
		}
		
		return name;
	}
}
