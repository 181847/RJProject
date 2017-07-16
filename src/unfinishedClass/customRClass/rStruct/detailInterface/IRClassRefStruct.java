package unfinishedClass.customRClass.rStruct.detailInterface;

import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_GPAssigns;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_name;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_type;

/**
 * 类引用的统一接口，
 * 可以包含名字、类型、泛参指定。
 */
public interface IRClassRefStruct 
extends 
IRStruct_contain_name, 
IRStruct_contain_type, 
IRStruct_contain_GPAssigns {

}
