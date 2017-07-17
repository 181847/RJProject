package unfinishedClass.customRClass.rStruct;

import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_excuters;

public class ExcuterFieldStruct 
		implements IRStruct, IRStruct_contain_excuters {
	
	/**
	 * 异常执行出口集合。
	 */
	protected RSet<ExcuterStruct> exceptionESet;

	/**
	 * 普通执行出口集合。
	 */
	protected RSet<ExcuterStruct> normalESet;
	
	/**
	 * @throws NameConflicException
	 * 		如果执行出口的名称发生了冲突，
	 * 		抛出此异常。
	 */
	@Override
	public void defineExcuters_by_RStruct(ExcuterFieldStruct excuterSet) {
		if (excuterSet == null) {
			throw new IllegalArgumentException("通过ExcuterFieldStruct定义执行出口时，"
					+ "传入的参数为null。");
		}
		
		//定义异常执行出口。
		defineEExcuters_by_RSet(excuterSet.getEExcuterSet());
		
		//定义普通执行出口。
		defineNExcuters_by_RSet(excuterSet.getNExcuterSet());
	}
	
	/**
	 * @throws NameConflicException
	 * 		如果执行出口的名称发生了冲突，
	 * 		抛出此异常。
	 */
	@Override
	public void defineEExcuters_by_RSet(RSet<ExcuterStruct> eSet) {
		if (eSet == null) {
			throw new IllegalArgumentException("通过执行出口集合定义异常执行出口时，"
					+ "传入的参数为null。");
		}
		
		for (ExcuterStruct eStruct : eSet) {
			defineEExcuter(eStruct);
		}
	}

	/**
	 * @throws NameConflicException
	 * 		如果执行出口的名称发生了冲突，
	 * 		抛出此异常。
	 */
	@Override
	public void defineNExcuters_by_RSet(RSet<ExcuterStruct> nSet) {
		if (nSet == null) {
			throw new IllegalArgumentException("通过执行出口集合定义普通执行出口时，"
					+ "传入的参数为null。");
		}
		
		for (ExcuterStruct eStruct : nSet) {
			defineNExcuter(eStruct);
		}
	}

	/**
	 * @throws NameConflicException
	 * 		如果执行出口的名称发生了冲突，
	 * 		抛出此异常。
	 */
	@Override
	public void defineEExcuter(ExcuterStruct exceptionEStruct) {
		if (exceptionEStruct == null) {
			throw new IllegalArgumentException("不能用null来定义异常执行出口");
		}
		
		//TODO 在这里添加对命名冲突的检测，如果出现命名冲突就抛出NameConflictException。
		
		exceptionESet.add(exceptionEStruct);
	}

	/**
	 * @throws NameConflicException
	 * 		如果执行出口的名称发生了冲突，
	 * 		抛出此异常。
	 */
	@Override
	public void defineNExcuter(ExcuterStruct normalEStruct) {
		if (normalEStruct == null) {
			throw new IllegalArgumentException("通过ExcuterFieldStruct定义执行出口时，"
					+ "传入的参数为null。");
		}
		
		//TODO 在这里添加对命名冲突的检测，如果出现命名冲突就抛出NameConflictException。
		
		normalESet.add(normalEStruct);
	}

	@Override
	public RSet<ExcuterStruct> getEExcuterSet() {
		return exceptionESet;
	}

	@Override
	public RSet<ExcuterStruct> getNExcuterSet() {
		return normalESet;
	}

}
