package unfinishedClass.customRClass.rStruct;

import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct;
import unfinishedClass.customRClass.rStruct.baseRStruct.RStruct_contain_name_classRef;
import unfinishedClass.customRClass.rStruct.detailInterface.IVarStruct;

/**
 * 变量结构，包括变量名、类型引用、初始化信息。
 * 其中初始化信息用TextStruct（initInfo）进行存储，
 * 但是由于变量的定义很多，为了不浪费空间，
 * 只有通过外部来为内部的initInfo创建对象，
 * 所以如果一个变量没有定义初始化信息，
 * initInfo就为null。
 */
public class VarStruct
		extends RStruct_contain_name_classRef
		implements IRStruct, IVarStruct {
	
	/**
	 * 初始化信息文本。
	 */
	protected TextStruct initInfo;

	/**
	 * 设置变量的名字。
	 * @param name
	 * 		要符合组件命名规范。
	 * @throws IllegalArgumentException
	 * 		如果name不符合组件命名规范。
	 */
	public void defineName(String name) {
		defineNameAsComponentName(name);
		
	}

	/**
	 * 定义变量的初始化信息。
	 * @param initStruct
	 * 		初始化信息结构。
	 */
	@Override
	public void defineInitInfo(TextStruct initStruct) {
		if (initStruct == null) {
			throw new IllegalArgumentException("不能用null来定义VarStruct中的初始化信息。");
		}
		
		initInfo = initStruct;
	}

	@Override
	public TextStruct getInitInfo() {
		return initInfo;
	}

}
