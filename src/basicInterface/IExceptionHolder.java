package basicInterface;

/**
 * 拥有异常对象的类接口，
 * 这个接口一般用于ExceptionExcutee、
 * ExceptionExcuter中设置和获取异常对象。
 * @author 75309
 *
 */
public interface IExceptionHolder {
	/**
	 * 获取Exception。
	 * @return exception对象。
	 */
	public Exception getException();
	
	/**
	 * 设置Exception对象。
	 * @param e exception对象。
	 */
	public void setException(Exception e);
}
