package unfinishedClass.customRClass.rStruct.baseRStruct;

import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_name;

/**
 * 包含泛参指配和名字的RStruct父类。
 */
public class RStruct_contain_GPAssigns_name extends RStruct_contain_GPAssigns implements IRStruct_contain_name {
	/**
	 * 包含的名称。
	 */
	protected String name;

	@Override
	public void defineName(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException(
					"RStruct的定义名称不能为null或者空。");
		}
		
		this.name = name;
	}

}
