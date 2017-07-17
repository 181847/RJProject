package unfinishedClass.customRClass.rStruct.detailInterface;

import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_name;

/**
 * 一个弧线定义端点。
 * @author 75309
 *
 */
public interface IArcPointStruct 
		extends IRStruct_contain_name{
	/**
	 * 用一个字符串来定义弧线端点，
	 * 包括一个序号和一个组件名称。
	 * @param arcPointInfo
	 * 		格式：数字 + "." + 组件名称，<br>
	 * 		形如“1.funStart”。
	 */
	public void defineArcPoint(String arcPointInfo);
	
	/**
	 * 设定弧线端点对应的子Function序号。
	 * @param subFunIndex
	 */
	public void defineIndex(int subFunIndex);

}
