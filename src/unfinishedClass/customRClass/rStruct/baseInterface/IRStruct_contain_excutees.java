package unfinishedClass.customRClass.rStruct.baseInterface;

import unfinishedClass.customRClass.rStruct.ExcuteeStruct;
import unfinishedClass.customRClass.rStruct.RSet;

/**
 * 包含执行入口定义的RStruct接口。
 */
public interface IRStruct_contain_excutees {
	/**
	 * 定义执行入口。
	 * @param excuteeSet
	 * 		执行入口结构集合。
	 */
	public void defineExcutees_by_RSet(RSet<ExcuteeStruct> excuteeSet);
	
	/**
	 * 增加定义执行入口。
	 * @param eStruct
	 * 		执行入口结构。
	 */
	public void defineExcutee(ExcuteeStruct eStruct);
	
	/**
	 * 获取执行入口定义集合。
	 * @return
	 * 		执行入口定义集合。
	 */
	public RSet<ExcuteeStruct> getExcuteeSet();
}
