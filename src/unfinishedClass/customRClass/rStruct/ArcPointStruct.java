package unfinishedClass.customRClass.rStruct;

import unfinishedClass.Exception.RStructException.ArcPointFormatException;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct;
import unfinishedClass.customRClass.rStruct.baseRStruct.RStruct_contain_name;
import unfinishedClass.customRClass.rStruct.detailInterface.IArcPointStruct;
import unfinishedClass.customRClass.scriptBlock.ScriptDeclaration;

/**
 * 弧线端点定义，
 * 包含一个序号和一个组件符号。
 */
public class ArcPointStruct 
		extends RStruct_contain_name
		implements IRStruct, IArcPointStruct {
	/**
	 * 弧线连接的子Function的序号。
	 */
	protected int index;

	/**
	 * 用一个字符串来定义弧线端点，
	 * 包括一个序号和一个组件名称。
	 * @param arcPointInfo
	 * 		格式：数字 + "." + 组件名称，<br>
	 * 		形如“1.funStart”。
	 * @throws ArcPointFormatException
	 * 		弧线端点字符串不能正常解析，或者序号部分不能转换为数字。
	 * @throws ComponentNameException
	 * 		对应的组件名不符合组件命名规范。
	 */
	public ArcPointStruct(String arcPointInfo) {
		defineArcPoint(arcPointInfo);
	}

	/**
	 * 定义弧线对应组件的名称。
	 * @param name
	 * 		这个弧线的名称用于指定连接的组件名称。
	 * @throws ComponentNameException
	 * 		对应的组件名不符合组件命名规范。
	 */
	@Override
	public void defineName(String name) {
		this.defineNameAsComponentName(name);
	}
	
	/**
	 * 用一个字符串来定义弧线端点，
	 * 包括一个序号和一个组件名称。
	 * @param arcPointInfo
	 * 		格式：数字 + "." + 组件名称，<br>
	 * 		形如“1.funStart”。
	 * @throws ArcPointFormatException
	 * 		弧线端点字符串不能正常解析，或者序号部分不能转换为数字。
	 * @throws ComponentNameException
	 * 		对应的组件名不符合组件命名规范。
	 */
	@Override
	public void defineArcPoint(String arcPointInfo) {
		if (arcPointInfo == null || arcPointInfo.isEmpty()) {
			throw new IllegalArgumentException("不能用null或者空串来定义弧线端点。");
		}
		
		//用点号分割。
		String[] arcDefines = arcPointInfo.split(
				//分割的时候要注意，英文半角点号是正则表达式中的特殊字符，
				//要通过一个斜杠来转义，为了加上一个斜杠，
				//java中的字符串还要用一个斜杠来转义这个斜杠，
				//于是就是下面看到的两个斜杠。
				"\\" + ScriptDeclaration.indexSplit);
		
		if (arcDefines.length != 2) {
			throw new ArcPointFormatException();
		}
		
		int index;
		try {
			index = Integer.parseInt(arcDefines[0]);
		} catch (NumberFormatException e) {
			throw new ArcPointFormatException();
		}
		
		//定义序号。
		defineIndex(index);
		//定义连接的组将名字。
		defineName(arcDefines[1]);
		
	}
	
	@Override
	public void defineIndex(int subFunIndex) {
		index = subFunIndex;
	}

	/**
	 * @return
	 * 		如果没有事先定义就返回0。
	 */
	@Override
	public int getIndex() {
		return index;
	}

}
