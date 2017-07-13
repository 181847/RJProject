package unfinishedClass.customRClass.rStruct.baseRStruct;

import unfinishedClass.customRClass.rStruct.GenParamStruct;
import unfinishedClass.customRClass.rStruct.RSet;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_genParams;

/**
 * 包含名称、类型、泛参定义的RStruct父类。
 */
public class RStruct_contain_name_type_genParams extends RStruct_contain_name_type
		implements IRStruct_contain_genParams {
	/**
	 * 泛参定义集合。
	 */
	protected RSet<GenParamStruct> gpSet;
	
	/**
	 * 定义泛参定义集合，通过一个泛参结构数组。
	 * @param gpSet
	 * 		包含多个泛参结构定义的集合（以ArrayList实现）。
	 * @return
	 * 		成功返回1，失败返回0.
	 */
	@Override
	public int defineGenParams_by_RSet(RSet<GenParamStruct> gpSet) {
		this.gpSet = gpSet;
		return 1;
	}

}
