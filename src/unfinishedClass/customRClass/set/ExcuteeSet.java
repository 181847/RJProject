package unfinishedClass.customRClass.set;

import unfinishedClass.customRClass.struct.ExcuteeStruct;

/**
 * 收集Excutee组件的名称集合。
 */
public class ExcuteeSet extends Set{

	/**
	 * 添加一个Exutee组件信息，
	 * 可以只是一个简单的字符串，
	 * 例如“fire”。
	 * @param excuteeInfo
	 * 		一个Excutee组件的信息，
	 * 		目前而言只需要这个的名字。
	 */
	public void addExcutee(String excuteeInfo) {
		append(new ExcuteeStruct(excuteeInfo));
	}
}
