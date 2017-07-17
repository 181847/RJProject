package unfinishedClass.Exception.RStructException;

/**
 * 当字符串不符合类名命名规范时，抛出此异常。
 */
public class RClassNameException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RClassNameException() {
		super("字符串不符合类名命名规范。");
	}
}
