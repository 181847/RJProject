package unfinishedClass.customRClass.rStruct.baseRStruct;

import unfinishedClass.customRClass.rStruct.GenParamStruct;
import unfinishedClass.customRClass.rStruct.RSet;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_genParams;

/**
 * 包含泛参定义的RStruct。
 * @author 75309
 *
 */
public class RStruct_contain_genParams implements IRStruct_contain_genParams {
	protected RSet<GenParamStruct> gpSet;
	
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
	 * 定义一个泛参，
	 * 防止定义同样名称的泛参。
	 * @param gpStruct
	 * 		指定的泛参结构。
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
}
