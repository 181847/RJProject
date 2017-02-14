package basicInterface;

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
