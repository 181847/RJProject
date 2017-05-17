package unfinishedClass.customRClass.set;

import unfinishedClass.customRClass.struct.SubFunStruct;

/**
 * 收集Function的子Funtion信息。
 */
public class SubFunSet extends Set{

	/**
	 * 添加子Function信息，
	 * 例如“(-4,-7)basic.Integer.appendInteger{3}”。
	 * @param originalString
	 * 		子Function信息。
	 */
	public void appendSubFun(SubFunStruct subFunStruct) {
		append(subFunStruct);
	}
}
