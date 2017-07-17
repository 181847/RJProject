package unfinishedClass.customRClass.rStruct;

import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct;
import unfinishedClass.customRClass.rStruct.baseRStruct.RStruct_contain_name_GPAssigns;

public class SubFunStruct 
extends RStruct_contain_name_GPAssigns
implements IRStruct {
	/**
	 * 在工作空间中的一个二维坐标。
	 */
	protected LocationStruct location;
	
	/**
	 * 修改信息，内部存储的就是简答的字符串。
	 */
	protected TextStruct modifyInfo;

	/**
	 * 要求名字符合组件命名规范。
	 * @param name
	 * 		符合组件命名规范的名字。
	 * @throws IllegalArgumentException
	 * 		如果name不符合组件命名规范，就会抛出此异常
	 */
	@Override
	public void defineName(String name) {
		//以组件规范定义名字。
		this.defineNameAsComponentName(name);
	}
	
	public void defineLocation(LocationStruct locationStruct) {
		if (locationStruct == null) {
			throw new IllegalArgumentException("不能用null来定义子Function的坐标。");
		}
		
		location = locationStruct;
	}

	public void defineModifyInfo(TextStruct modifyStruct) {
		if (modifyStruct == null) {
			throw new IllegalArgumentException("不能用null来定义子Function的修改信息。");
		}
		
		modifyInfo = modifyStruct;
	}

}
