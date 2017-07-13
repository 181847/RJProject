package unfinishedClass.customRClass.scriptBlock.information;

import unfinishedClass.customRClass.rStruct.IRStruct;
import unfinishedClass.customRClass.rStruct.RClassStruct;

/**
 * 可以存储RStruct的Information，
 * @author 75309
 *
 * @param <T>
 * 		RStruct的具体类型。
 */
public class RStructInfo<T extends IRStruct> extends RawScriptInformation{
	protected T rStruct;

	/**
	 * @param rcs
	 * 		需要存储的RClassStruct结构。
	 */
	public RStructInfo(T rStruct) {
		super("RStruct Information.");
		this.rStruct = rStruct;
	}

	/**
	 * 获取存储的RStruct结构。
	 * @return
	 * 		存储的RStruct结构。
	 */
	public T getrStruct() {
		return rStruct;
	}

	/**
	 * 存放RStruct结构。
	 * @param rStruct
	 * 		被存储的RStruct结构。
	 */
	public void setrStruct(T rStruct) {
		this.rStruct = rStruct;
	}

}
