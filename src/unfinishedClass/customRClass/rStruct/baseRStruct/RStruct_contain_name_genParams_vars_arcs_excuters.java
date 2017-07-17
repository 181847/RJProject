package unfinishedClass.customRClass.rStruct.baseRStruct;

import unfinishedClass.customRClass.rStruct.ExcuterFieldStruct;
import unfinishedClass.customRClass.rStruct.ExcuterStruct;
import unfinishedClass.customRClass.rStruct.RSet;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_excuters;

/**
 * 包含名称、泛参定义、变量定义、弧线、执行出口的RStruct的父类。
 */
public abstract class RStruct_contain_name_genParams_vars_arcs_excuters 
		extends RStruct_contain_name_genParams_vars_arcs
		implements IRStruct_contain_excuters {
	
	/**
	 * 执行出口定义区域。
	 */
	public ExcuterFieldStruct efStruct;
	
	/**
	 * @throws NameConflicException
	 * 		如果执行出口的名称发生了冲突，
	 * 		抛出此异常。
	 */
	@Override
	public void defineExcuters_by_RStruct(ExcuterFieldStruct excuterStruct) {
		efStruct.defineExcuters_by_RStruct(excuterStruct);
	}

	/**
	 * @throws NameConflicException
	 * 		如果执行出口的名称发生了冲突，
	 * 		抛出此异常。
	 */
	@Override
	public void defineEExcuters_by_RSet(RSet<ExcuterStruct> eSet) {
		efStruct.defineEExcuters_by_RSet(eSet);
	}

	/**
	 * @throws NameConflicException
	 * 		如果执行出口的名称发生了冲突，
	 * 		抛出此异常。
	 */
	@Override
	public void defineNExcuters_by_RSet(RSet<ExcuterStruct> nSet) {
		efStruct.defineNExcuters_by_RSet(nSet);
	}

	/**
	 * @throws NameConflicException
	 * 		如果执行出口的名称发生了冲突，
	 * 		抛出此异常。
	 */
	@Override
	public void defineEExcuter(ExcuterStruct eStruct) {
		efStruct.defineEExcuter(eStruct);
	}

	/**
	 * @throws NameConflicException
	 * 		如果执行出口的名称发生了冲突，
	 * 		抛出此异常。
	 */
	@Override
	public void defineNExcuter(ExcuterStruct eStruct) {
		efStruct.defineNExcuter(eStruct);
	}

	@Override
	public RSet<ExcuterStruct> getEExcuterSet() {
		return efStruct.getEExcuterSet();
	}

	@Override
	public RSet<ExcuterStruct> getNExcuterSet() {
		return efStruct.getNExcuterSet();
	}

}
