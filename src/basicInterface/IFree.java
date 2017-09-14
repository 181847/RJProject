package basicInterface;

/**
 * 实现这个接口的类应该能够将内部的成员变量置空，
 * 帮助垃圾回收。
 */
public interface IFree {
	/**
	 * 将内部成员变量置空，
	 * 方便垃圾回收。
	 */
	public int free();
}
