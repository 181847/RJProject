package unfinishedClass.customRClass.rStruct;

import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct;
import unfinishedClass.customRClass.rStruct.baseRStruct.RStruct_contain_name;
import unfinishedClass.customRClass.rStruct.detailInterface.IExcuteeStruct;

/**
 * 存储了执行入口信息（名字）的结构。
 */
public class ExcuteeStruct 
		extends RStruct_contain_name
		implements IRStruct, IExcuteeStruct{

	/**
	 * 设置Excutee的名字。
	 * @param name
	 * 		Excutee的名字。
	 * @throws ComponentNameException
	 * 		如果name不符合组件命名规范，就会抛出此异常。
	 */
	public void defineName(String name) {
		this.defineNameAsComponentName(name);
	}

}
