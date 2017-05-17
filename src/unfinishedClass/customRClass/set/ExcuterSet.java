package unfinishedClass.customRClass.set;

import unfinishedClass.customRClass.struct.ExcuterStruct;

/**
 * 收集Function的Excuter组件信息。
 */
public class ExcuterSet extends Set{
	/**
	 * 添加一个Excuter组件信息，
	 * 可以只是一个简单的字符串，
	 * 例如“finished”。
	 * @param excuterInfo
	 * 		一个Excuter组件的信息，
	 * 		目前而言只需要这个的名字。
	 */
	public void addExcuter(String excuterInfo) {
		append(new ExcuterStruct(excuterInfo));
	}
}
