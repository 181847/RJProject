package unfinishedClass.Exception.RStructException;

public class ArcPointFormatException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ArcPointFormatException() {
		super("弧线端点字符串定义不符合规范。");
	}

}
