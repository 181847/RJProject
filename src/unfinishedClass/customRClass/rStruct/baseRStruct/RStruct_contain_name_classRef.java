package unfinishedClass.customRClass.rStruct.baseRStruct;

import unfinishedClass.customRClass.rStruct.RClassRefStruct;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_classRef;

/**
 * 包含名字和类型引用的RStruct父类。
 */
public abstract class RStruct_contain_name_classRef 
extends RStruct_contain_name
implements IRStruct_contain_classRef{
	
	/**
	 * 类型引用。
	 */
	protected RClassRefStruct classRef;
	
	public RStruct_contain_name_classRef() {
		classRef = new RClassRefStruct();
	}

	/**
	 * 定义类型引用。
	 * @param
	 * 		被定义的类型引用结构。
	 */
	@Override
	public void defineClassRef(RClassRefStruct rClassRefStruct) {
		classRef = rClassRefStruct;
	}
	
	public RClassRefStruct getClassRef(){
		return classRef;
	}
}
