package unfinishedClass.customRClass.set;

import unfinishedClass.customRClass.struct.ParameterStruct;

/**
 * 收集Function的Parameter组件信息。
 */
public class ParameterSet extends Set{

	/**
	 * 添加一个Parameter组件的信息，，
	 * 形如“basic.Integer parameter2”，<br>
	 * “basic.Integer” ---- 组件的类型，<br>
	 * “parameter2” ------- 组件的名称。
	 * @param parameterInfo
	 * 		一个Parameter组件的信息。
	 */
	public void appendParameter(ParameterStruct parameterStruct) {
		append(parameterStruct);
	}
}
