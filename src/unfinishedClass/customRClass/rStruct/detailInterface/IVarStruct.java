package unfinishedClass.customRClass.rStruct.detailInterface;

import unfinishedClass.customRClass.rStruct.TextStruct;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_classRef;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_name;

/**
 * 变量定义结构接口。
 */
public interface IVarStruct 
		extends 
		IRStruct_contain_name, 
		IRStruct_contain_classRef {

	/**
	 * 定义变量的初始化信息。
	 * @param initStruct
	 * 		初始化信息。
	 */
	public void defineInitInfo(TextStruct initStruct);
	
	/**
	 * 获取初始化信息。
	 * @return
	 * 		包含初始化信息的TextStruct，
	 * 		如果本变量没有定义初始化信息的话就返回null。
	 */
	public TextStruct getInitInfo();
}
