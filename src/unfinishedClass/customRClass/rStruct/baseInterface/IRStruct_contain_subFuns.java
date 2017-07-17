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
}
