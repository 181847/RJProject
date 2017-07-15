package unfinishedClass.customRClass.rStruct.baseInterface;

import unfinishedClass.customRClass.rStruct.GPAssignStruct;
import unfinishedClass.customRClass.rStruct.RSet;

/**
 * 拥有泛参指配的接口类。
 */
public interface IRStruct_contain_GPAssigns {
	/**
	 * 定义subFun的泛参指配。
	 * @param gpaSet
	 * 		泛参指配结构集合。
	 */
	public void defineGPAssigns_by_RSet(RSet<GPAssignStruct> gpaSet);
	
	/**
	 * 定义泛参指配，
	 * 防止对同名的泛参进行指配。
	 * @param gpaStruct
	 * 		包含泛参名和及被指配的RClassRefStruct。
	 * @exception NameConfliction_Exception
	 * 		定义冲突的泛参名泛参指配时，抛出此异常。
	 */
	public void defineGPAssign(GPAssignStruct gpaStruct);
}
