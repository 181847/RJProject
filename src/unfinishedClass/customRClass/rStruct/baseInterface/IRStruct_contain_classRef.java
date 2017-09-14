package unfinishedClass.customRClass.rStruct.baseInterface;

import unfinishedClass.customRClass.rStruct.RClassRefStruct;

/**
 * 包含类型引用的RStruct接口。
 */
public interface IRStruct_contain_classRef {
	
	/**
	 * 设定类型引用。
	 * @param rClassRefStruct
	 * 		被设定的类型引用。
	 */
	public void defineClassRef(RClassRefStruct rClassRefStruct);
	
	/**
	 * 获取内部存储的类型引用结构。
	 * @return
	 * 		类型引用结构，比如变量定义结构中变量的类型，
	 * 		如果当前还没有定义类型引用的话就返回nulls。
	 */
	public RClassRefStruct getClassRef();
}
