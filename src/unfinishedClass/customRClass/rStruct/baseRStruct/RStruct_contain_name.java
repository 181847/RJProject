package unfinishedClass.customRClass.rStruct.baseRStruct;

import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_name;

/**
 * 包含名字的RStruct的父类。
 */
public class RStruct_contain_name implements IRStruct_contain_name {

	/**
	 * RStruct的名字。
	 */
	protected String name;
	
	/**
	 * 定义RStruct的名字。
	 * @param name 
	 * 		被定义的名字。
	 */
	@Override
	public void defineName(String name) {
		this.name = name;
	}

}
