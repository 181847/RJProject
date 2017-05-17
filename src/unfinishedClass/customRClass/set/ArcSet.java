package unfinishedClass.customRClass.set;

import unfinishedClass.customRClass.struct.ArcStruct;

/**
 * 弧线连接集合。
 */
public class ArcSet extends Set {

	/**
	 * 添加弧线信息。
	 * @param arcInfo
	 * 		弧线信息。
	 */
	public void addArc(String arcInfo) {
		append(new ArcStruct(arcInfo));
	}
}
