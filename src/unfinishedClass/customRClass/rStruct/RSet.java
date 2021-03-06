package unfinishedClass.customRClass.rStruct;

import java.util.ArrayList;

import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct;

/**
 * 相同类型的RStruc的集合，
 * 内部可以存储多个同类型的RStruct对象。
 */
public class RSet<T extends IRStruct> extends ArrayList<T> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 获取指定序号的RStruct对象。
	 * @param index
	 * 		对应的序号。
	 * @return
	 * 		如果序号超出范围就返回null。
	 */
	public T getRStruct(int index) {
		return get(index);
	}

}
