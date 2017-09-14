package unfinishedClass.customRClass.rStruct.baseRStruct;

import unfinishedClass.customRClass.rStruct.GenParamStruct;
import unfinishedClass.customRClass.rStruct.RSet;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_genParams;

/**
 * 包含名称和泛参定义的RStruct父类。
 */
public abstract class RStruct_contain_name_genParams
		extends RStruct_contain_name
		implements IRStruct_contain_genParams {
	/**
	 * 包含所有泛参定义的集合。
	 */
	protected RSet<GenParamStruct> gpSet;
	
	public RStruct_contain_name_genParams() {
		gpSet = new RSet<GenParamStruct>();
	}
	
	/**
	 * 定义泛参定义集合，通过一个泛参结构数组。
	 * @param gpSet
	 * 		包含多个泛参结构定义的集合（以ArrayList实现）。
	 * @return
	 * 		成功返回1，失败返回0.
	 */
	@Override
	public void defineGenParams_by_RSet(RSet<GenParamStruct> gpSet) {
		if (gpSet == null) {
			throw new IllegalArgumentException("添加的泛参定义集合为null。");
		}
		
		for (GenParamStruct gpStruct : gpSet) {
			defineGenParam(gpStruct);
		}
	}
	
	/**
	 * @exception NameConflictionException
	 * 		泛参定义名冲突。
	 */
	@Override
	public void defineGenParam(GenParamStruct gpStruct) {
		if (gpStruct == null) {
			throw new IllegalArgumentException("添加的泛参定义结构为null。");
		}
		
		//TODO 添加对泛参定义冲突的检查。
		
		//添加泛参集合。
		gpSet.add(gpStruct);
	}
	
	public RSet<GenParamStruct> getGenParamSet() {
		return gpSet;
	}
}
