package unfinishedClass.customRClass.rStruct.baseRStruct;

import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_name;

/**
 * 包含名称和泛参定义的RStruct父类。
 */
public class RStruct_contain_genParams_name
extends RStruct_contain_genParams
implements IRStruct_contain_name {
	/**
	 * 结构定义的名字。
	 */
	protected String name;

	@Override
	public void defineName(String name) {
		if (name == null || name.equals("")) {
			//防止名字是null或者空串。
			throw new IllegalArgumentException("定义RStrut的名字是null或者空串。");
		}
		
		this.name = name;
	}
	
}
