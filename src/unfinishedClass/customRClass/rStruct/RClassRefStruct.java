package unfinishedClass.customRClass.rStruct;

import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct;
import unfinishedClass.customRClass.rStruct.baseRStruct.RStruct_contain_name_GPAssigns_type;
import unfinishedClass.customRClass.rStruct.detailInterface.IRClassRefStruct;

/**
 * 存储类型引用的结构，
 * 可以引用实类型、泛型或者泛参。
 */
public class RClassRefStruct
extends RStruct_contain_name_GPAssigns_type
implements IRClassRefStruct, IRStruct {
	/**
	 * 设置被引用类型的名字，
	 * 如果满足类名命名规范就会设置引用类型为CLASS_REF_CL。
	 * 如果满足组件命名规范就会设置为CLASS_REF_GP，
	 * 否则不设置类型。
	 * @param name
	 * 		要设置的类型引用的名称。
	 */
	@Override
	public void defineName(String name) {
		defineType(
				//如果name符合组件命名规范，返回CLASS_REF_CL；
				//如果name符合类名命名规范，返回CLASS_REF_GP；
				defineNameAsAuto(name));
	}
}
