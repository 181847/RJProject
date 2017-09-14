package unfinishedClass.customRClass.rStruct.baseInterface;

import unfinishedClass.customRClass.rStruct.GenParamStruct;
import unfinishedClass.customRClass.rStruct.RSet;

/**
 * 拥有泛参的RStruct。
 */
public interface IRStruct_contain_genParams {
	/**
	 * 通过一个泛参定义结构集合来定义泛参。
	 * @param gpSet
	 * 		包含多个泛参结构的集合。
	 * @return
	 * 		0 - 失败；
	 * 		1 - 成功。
	 */
	public void defineGenParams_by_RSet(RSet<GenParamStruct> gpSet);
	
	/**
	 * 定义一个泛参，
	 * 防止定义同样名称的泛参。
	 * @param gpStruct
	 * 		指定的泛参结构。
	 * @throws NameConfliction_Exception
	 * 		泛参定义名冲突。
	 */
	public void defineGenParam(GenParamStruct gpStruct);
	
	/**
	 * 获取泛参定义集合。
	 * @return
	 * 		泛参定义集合，即使没有定义泛参，返回的对象也不是null，
	 * 		只不过内部没有包含泛参定义。
	 */
	public RSet<GenParamStruct> getGenParamSet();
}
