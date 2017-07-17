package unfinishedClass.customRClass.rStruct.detailInterface;

import unfinishedClass.customRClass.rStruct.LocationStruct;
import unfinishedClass.customRClass.rStruct.TextStruct;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_GPAssigns;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_name;

public interface ISubFunStruct 
		extends
		IRStruct_contain_name,
		IRStruct_contain_GPAssigns{
	
	/**
	 * 设定子Function在工作空间中的位置。
	 * @param locationStruct
	 * 		包含一个二维坐标的结构。
	 */
	public void defineLocation(LocationStruct locationStruct);
	
	/**
	 * 定义调用SubFun时的修改信息。
	 * @param modifyStruct
	 * 		修改信息结构。
	 */
	public void defineModifyInfo(TextStruct modifyStruct);
}
