package unfinishedClass.customRClass.rStruct.baseRStruct;

import unfinishedClass.customRClass.rStruct.RClassRefStruct;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_classRef;

/**
 * 包含泛参指配、名字、类型引用的RStruct父类。
 */
public abstract class RStruct_contain_name_GPAssigns_classRef 
		extends RStruct_contain_name_GPAssigns
		implements IRStruct_contain_classRef{
	
	/**
	 * 类型引用。
	 */
	protected RClassRefStruct classRefStruct;

	@Override
	public void defineClassRef(RClassRefStruct rClassRefStruct) {
		if (rClassRefStruct == null) {
			throw new IllegalArgumentException("不能使用null来定义类型引用");
		}
		
		classRefStruct = rClassRefStruct;
	}

}
