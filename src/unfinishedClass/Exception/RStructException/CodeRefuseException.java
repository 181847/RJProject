package unfinishedClass.Exception.RStructException;

/**
 * 通用的代码拒绝执行异常，
 * 当某一部分代码不准执行时，抛出此异常。
 * @author 75309
 *
 */
public class CodeRefuseException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @param refuseReason
	 * 		拒绝执行代码的原因。
	 */
	public CodeRefuseException(String refuseReason) {
		super(refuseReason);
	}

}
