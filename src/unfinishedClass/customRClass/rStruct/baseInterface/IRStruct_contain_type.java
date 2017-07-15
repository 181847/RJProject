package unfinishedClass.customRClass.rStruct.baseInterface;

import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * 拥有类型区别的RStruct结构。
 */
public interface IRStruct_contain_type {

	/**
	 * 定义RStruct类型，
	 * 一般用于RClassStruct、RClassRefStruct、GPAssignStruct。
	 * @param type
	 * 		被指定的类型标签。
	 * @throws IllegalArgumentException
	 * 		传入参数为null或者InformationType.VOID。
	 */
	public void defineType(InformationType type);
}
