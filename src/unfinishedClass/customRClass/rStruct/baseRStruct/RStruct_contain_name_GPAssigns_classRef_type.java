package unfinishedClass.customRClass.rStruct.baseRStruct;

import unfinishedClass.customRClass.rStruct.RClassRefStruct;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_classRef;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_type;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * 包含泛参指定、名字、类型、类型引用的RStruct父类，
 * 一般用于GPAssignStruct中，其中的类型不允许随便指配，
 * 由内部存储的classRef来指定。
 */
public abstract class RStruct_contain_name_GPAssigns_classRef_type 
		extends RStruct_contain_name_GPAssigns_classRef
		implements IRStruct_contain_type {

	@Override
	public void defineType(InformationType type) {
		//由于这个抽象基类一般用于GPAssignStruct中。
		//修改这个的type就是修改类型引用的type。
		
		classRefStruct.defineType(type);
	}
}
