package unfinishedClass.customRClass.rStruct.baseRStruct;

import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_type;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * 包含名称和类型的RStruct父类。
 */
public abstract class RStruct_contain_name_type 
	extends RStruct_contain_name 
	implements IRStruct_contain_type {
	
	/**
	 * 类型标签，
	 * 一般用于RClassStruct、RClassRefStruct、GPAssignStruct。
	 */
	protected InformationType type;
	
	/**
	 * 设置类型标签，
	 * 一般用于RClassStruct、RClassRefStruct、GPAssignStruct。
	 * @param type
	 * 		被设置的类型标签。
	 */
	@Override
	public void defineType(InformationType type) {
		this.type = type;
	}
}
