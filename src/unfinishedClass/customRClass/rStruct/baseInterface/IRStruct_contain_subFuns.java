package unfinishedClass.customRClass.rStruct.baseInterface;

import unfinishedClass.customRClass.rStruct.RSet;
import unfinishedClass.customRClass.rStruct.SubFunStruct;

/**
 * 包含子Function定义的RStruct接口。
 */
public interface IRStruct_contain_subFuns {
	
	/**
	 * 定义subFun。
	 * @param subFunSet
	 * 		subFun结构集合。
	 */
	public void defineSubFuns_by_RSet(RSet<SubFunStruct> subFunSet);
	
	/**
	 * 增加定义子Function。
	 * @param sfStruct
	 * 		子Function定义结构。
	 */
	public void defineSubFun(SubFunStruct sfStruct);
	
	/**
	 * 获取子Function定义集合。
	 * @return
	 * 		所有子Function定义的集合，如果没有子Function定义，
	 * 		返回对象长度为0（RSet以ArrayList实现）。
	 */
	public RSet<SubFunStruct> getSubFunSet();
}
