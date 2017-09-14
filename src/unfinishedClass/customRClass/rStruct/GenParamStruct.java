package unfinishedClass.customRClass.rStruct;

import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct;
import unfinishedClass.customRClass.rStruct.baseRStruct.RStruct_contain_name_classRef;
import unfinishedClass.customRClass.rStruct.detailInterface.IGenParamStruct;

/**
 * 存储了一个泛参定义的结构，
 * 包含泛参的名字，
 * 以及泛型约束。
 */
public class GenParamStruct 
		extends RStruct_contain_name_classRef
		implements IRStruct, IGenParamStruct {

	/**
	 * 设置泛参定义的名字。
	 * @param gpName
	 * 		一个符合组件命名规范的字符串。
	 */
	public void defineName(String gpName) {
		this.defineNameAsComponentName(gpName);
	}
	
	public void defineGPConstraint(RClassRefStruct constrainRClassRef) {
		this.defineClassRef(constrainRClassRef);
	}

}
