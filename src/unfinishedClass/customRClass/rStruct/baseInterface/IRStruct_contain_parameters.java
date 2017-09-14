package unfinishedClass.customRClass.rStruct.baseInterface;

import unfinishedClass.customRClass.rStruct.RSet;
import unfinishedClass.customRClass.rStruct.VarStruct;

/**
 * 包含参数组件定义的RStruct接口。
 */
public interface IRStruct_contain_parameters {
	/**
	 * 定义参数组件。
	 * @param parameterSet
	 * 		参数结构结合。
	 */
	public void defineParameters_by_RSet(RSet<VarStruct> parameterSet);
	
	/**
	 * 增加定义参数组件。
	 * @param vStruct
	 * 		参数组件定义结构。
	 */
	public void defineParameter(VarStruct vStruct);
	
	/**
	 * 获取参数定义集合。
	 * @return
	 * 		参数定义集合，如果没有定义参数，
	 * 		返回对象长度就为0（RSet以ArrayList实现）。
	 */
	public RSet<VarStruct> getParameterSet();
}
