package function.component;

import basicInterface.IExceptionHolder;
import functionInterface.IExcutee;

/**
 * 异常线路上的Excutee，拥有一个异常变量的成员，
 * 当Runner发生异常，Runner将会在ExceptionExcutee和ExceptionExcuter之间进行传递，
 * 内部的异常变量成员用来存储发生的异常。
 */
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
