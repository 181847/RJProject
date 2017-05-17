package unfinishedClass.customRClass.set;

import unfinishedClass.customRClass.struct.RClassRefStruct;

/**
 * 可收集多个接口RClass名字的集合，
 * 用来在RClassStructClass中作为接口的记录。
 */
public class ImplementSet extends Set{

	/**
	 * 添加接口信息。
	 * @param interfaceName
	 * 		接口RClass的名字。
	 */
	public void appendInterface(RClassRefStruct interfaceRef) {
		append(interfaceRef);
	}
}
