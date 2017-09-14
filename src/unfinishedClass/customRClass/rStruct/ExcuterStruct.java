package unfinishedClass.customRClass.rStruct;

import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct;
import unfinishedClass.customRClass.rStruct.baseRStruct.RStruct_contain_name;
import unfinishedClass.customRClass.rStruct.detailInterface.IExcuterStruct;

/**
 * 存储Excuter定义的结构。
 */
public class ExcuterStruct 
		extends RStruct_contain_name
		implements IRStruct, IExcuterStruct {

	/**
	 * 设置Excuter的名字。
	 * @param name
	 * 		Excuter的名字。
	 * @throws ComponentNameException
	 * 		如果name不符合组件命名规范，就会抛出此异常。
	 */
	public void defineName(String name) {
		this.defineNameAsComponentName(name);
	}

}
