package function.component;

import basicInterface.IExceptionHolder;
import functionInterface.IExcutee;

public class ExceptionExcutee extends LinerExcutee implements IExcutee, IExceptionHolder {
	
	Exception exception;

	public ExceptionExcutee(String excuteeName, int paragraphToFire) {
		super(excuteeName, paragraphToFire);
	}
	
	/**
	 * 获取Exception。
	 * @return exception对象。
	 */
	public Exception getException(){
		return exception;
	}
	
	/**
	 * 设置Exception对象。
	 * @param e exception对象。
	 */
	public void setException(Exception e){
		exception = e;
	}
}
