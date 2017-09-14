package basicInterface;

/**
 * 所有可命名对象的接口，
 * 例如RClass的名字，Function的名字，
 * Excutee的名字，Parameter的名字，
 * Excuter的名字，Returnval的名字，
 * Reference的名字。
 */
public interface INameable {
	/**
	 * 重载Object的String toString()函数，
	 * 这个重载运用在RReference当中的，
	 * IRReference Member(String)、int locateRReferenceOf(String)，
	 * 目的是减少为了得到名字而需要进行的强制类型转换。
	 * @return 当前可命名对象内部存储的名字。
	 */
	public String toString();

	/**
	 * 获取名字专用的方法。
	 * @return 当前可命名对象内部存储的名字。
	 */
	public String getName();
	
	/**
	 * 设置名字。
	 * @param 新的名字。
	 */
	public void setName(String newName);
}
