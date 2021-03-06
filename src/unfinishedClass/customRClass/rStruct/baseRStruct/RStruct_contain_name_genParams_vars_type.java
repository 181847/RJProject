package unfinishedClass.customRClass.rStruct.baseRStruct;

import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_type;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * 包含泛参定义、名称、变量定义、类型的RStruct父类。
 */
public abstract class RStruct_contain_name_genParams_vars_type
extends RStruct_contain_name_genParams_vars
implements IRStruct_contain_type {
	
	/**
	 * RStruct的类型标签。
	 */
	protected InformationType type;

	@Override
	public void defineType(InformationType type) {
		if (type == null || type == InformationType.VOID) {
			throw new IllegalArgumentException("InformationType为null或者VOID。");
		}
		
		this.type = type;
	}

	public InformationType getType() {
		return type;
	}
}
