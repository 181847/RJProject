package unfinishedClass.customRClass.rStruct.baseInterface;

import unfinishedClass.customRClass.rStruct.ExcuterFieldStruct;
import unfinishedClass.customRClass.rStruct.ExcuterStruct;
import unfinishedClass.customRClass.rStruct.RSet;

/**
 * 包含执行出口定义的RStruct接口。
 */
public interface IRStruct_contain_excuters {
	/**
	 * 定义执行出口。
	 * @param excuterSet
	 * 		执行出口结构集合。
	 */
	public void defineExcuters_by_RStruct(ExcuterFieldStruct excuterSet);
	
	/**
	 * 通过执行出口结合来定义异常执行出口。
	 * @param eSet
	 * 		异常执行出口集合。
	 */
	public void defineEExcuters_by_RSet(RSet<ExcuterStruct> eSet);
	
	/**
	 * 通过执行出口结合来定义普通执行出口。
	 * @param eSet
	 * 		普通执行出口集合。
	 */
	public void defineNExcuters_by_RSet(RSet<ExcuterStruct> nSet);
	
	/**
	 * 单独定义异常执行入口。
	 * @param eStruct
	 * 		异常执行入口结构。
	 * @throws NameConflictException
	 * 		如果发生异常执行入口命名冲突就要抛出这个异常。
	 */
	public void defineEExcuter(ExcuterStruct eStruct);
	
	/**
	 * 单独定义普通执行入口。
	 * @param eStruct
	 * 		普通执行入口结构。
	 * @throws NameConflictException
	 * 		如果发生普通执行入口命名冲突就要抛出这个异常。
	 */
	public void defineNExcuter(ExcuterStruct eStruct);
	
	/**
	 * 获取异常执行出口集合。
	 * @return
	 * 		异常执行出口集合。
	 */
	public RSet<ExcuterStruct> getEExcuterSet();

	/**
	 * 获取普通执行出口集合。
	 * @return
	 * 		普通执行出口集合。
	 */
	public RSet<ExcuterStruct> getNExcuterSet();
	

}
