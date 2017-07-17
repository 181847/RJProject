package unfinishedClass.customRClass.rStruct.detailInterface;

import unfinishedClass.customRClass.rStruct.RClassRefStruct;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_classRef;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_name;

/**
 * 存储泛参定义的结构接口，
 * 包括名称和类引用。
 */
public interface IGenParamStruct 
		extends 
		IRStruct_contain_name, 
		IRStruct_contain_classRef {
	
	/**
	 * 设置泛参的约束，
	 * 一个泛参用一个类型引用来进行约束，
	 * 和调用本类的defineClassRef()方法的效果一样。
	 * @param constrainRClassRef
	 * 		一个类型引用。
	 */
	public void defineGPConstraint(RClassRefStruct constrainRClassRef);
}
