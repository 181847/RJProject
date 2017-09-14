package unfinishedClass.Exception.RStructException;

/**
 * 当字符串不符合组件命名规范时，抛出此异常。
 */
public class ComponentNameException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ComponentNameException() {
		super("字符串不符合组件命名规范。");
	}

}
