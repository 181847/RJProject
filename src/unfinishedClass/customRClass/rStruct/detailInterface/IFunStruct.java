package unfinishedClass.customRClass.rStruct.detailInterface;

import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_arcs;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_comments;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_excutees;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_excuters;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_genParams;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_name;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_parameters;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_returnvals;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_subFuns;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_vars;

/**
 * Function定义结构接口。
 */
public interface IFunStruct 
		extends 
		IRStruct_contain_name, 
		IRStruct_contain_genParams,
		IRStruct_contain_vars,
		IRStruct_contain_arcs,
		IRStruct_contain_excutees,
		IRStruct_contain_parameters,
		IRStruct_contain_excuters,
		IRStruct_contain_returnvals,
		IRStruct_contain_subFuns,
		IRStruct_contain_comments{
	
}
