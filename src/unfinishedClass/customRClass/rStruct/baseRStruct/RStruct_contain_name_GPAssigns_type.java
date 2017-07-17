package unfinishedClass.customRClass.rStruct.baseRStruct;

import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_type;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * 包含泛参指定、名称、类型的RStruct的父类型。
 */
public abstract class RStruct_contain_name_GPAssigns_type 
		extends RStruct_contain_name_GPAssigns
		implements IRStruct_contain_type {
	
	/**
	 * 类型。
	 */
	protected InformationType type;

	@Override
	public void defineType(InformationType type) {
		if (type == null || type == InformationType.VOID) {
			throw new IllegalArgumentException("不能使用null或者VOID来设置RStruct的类型。");
		}
		
		this.type = type;
	}

	public InformationType getType() {
		return type;
	}
}
