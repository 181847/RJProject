package unfinishedClass.Exception.RStructException;

public class NameConflictException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NameConflictException (String errorReason) {
		super(errorReason);
	}
}
