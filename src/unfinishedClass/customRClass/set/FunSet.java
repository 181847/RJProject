package unfinishedClass.customRClass.set;

import unfinishedClass.customRClass.struct.FunctionStruct;

/**
 * 本类用于存储FunctionStruct，
 * 并提供获取的方法。
 */
public class FunSet extends Set{

	/**
	 * 添加FunctionStruct，
	 * 要求不能与原有的元素的名字发生冲突，否则插入失败。
	 * @param funStruct
	 * 		被添加的Function元素。
	 */
	public void appendFun(FunctionStruct funStruct) {
		append(funStruct);
	}

	/**
	 * 检查内部是否包含非抽象Function。
	 * @return
	 * 		如果集合内部没有Function、
	 * 		或者只有抽象Function，就返回false。
	 * 		否则返回true。
	 */
	public boolean haveFun() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 检查集合内部是否包含抽象Function。
	 * @return
	 * 		如果包含抽象Function则返回true，
	 * 		否则返回false。
	 */
	public boolean haveAbstractFun() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 获取指定序号的FunctionStruct
	 * @param funNum
	 * @return
	 */
	public FunctionStruct getFun(int funNum) {
		// TODO Auto-generated method stub
		return null;
	}
}
