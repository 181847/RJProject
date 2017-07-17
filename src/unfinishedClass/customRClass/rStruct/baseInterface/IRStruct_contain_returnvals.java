package unfinishedClass.customRClass.rStruct.baseInterface;

import unfinishedClass.customRClass.rStruct.RSet;
import unfinishedClass.customRClass.rStruct.VarStruct;

/**
 * 包含返回值组件定义的RStruct接口。
 */
public interface IRStruct_contain_returnvals {
	/**
	 * 定义返回值组件。
	 * @param returnvalSet
	 * 		返回值结构集合。
	 */
	public void defineReturnvals_by_RSet(RSet<VarStruct> returnvalSet);
	
	/**
	 * 增加定义返回值组件。
	 * @param vStruct
	 * 		返回值组件定义结构。
	 */
	public void defineReturnval(VarStruct vStruct);
}
