package function;

import function.component.ExceptionExcutee;
import functionInterface.IExcuter;
import functionInterface.IFunction;

/**
 * 这个Function专门用来捕获异常线路下的Runner。
 * 插口详情
 * Excutee：
 * 【
 * {Exceptionexcutee，“CATCH”，捕获异常的执行入口}
 * 】
 * Parameter：无；
 * Excuter：
 * 【
 * {ExceptionExcuter，“EXCEPTION”，异常执行出口 }
 * 】
 * Returnval：无。
 */
public abstract class AbstractFunctionCatchException extends AbstractFunctionForJava implements IFunction {

	/**
	 * Excutee: "CATCH"，
	 * Excuter: “EXCEPTION”。
	 * 在本抽象类中自动为excuteeList加一，
	 * 多出来的一个空间用来放置一个名为“CATCH”的ExceptionExcutee，
	 * 且它对应的代码段的数字为0。
	 * @param name 
	 * 		function的名字
	 * @param excuteeList 
	 * 		excutee列表的初始化空间数量。
	 * @param parameterList 
	 * 		parameter列表的初始化空间数量。
	 * @param excuterList 
	 * 		parameter列表的初始化空间数量。
	 * @param returnvalList 
	 * 		returnval列表的初始化空间数量。
	 */
	public AbstractFunctionCatchException(String name, int excuteeList, int parameterList, int excuterList,
			int returnvalList) {
		super(name, excuteeList + 1, parameterList, excuterList, returnvalList);
		this.insertExcutee(new ExceptionExcutee("CATCH", 0));
	}
	
	/**
	 * 获取名为“CATCH”的Excutee内部的Exception对象。
	 * @return
	 */
	public Exception catchException(){
		return ((ExceptionExcutee) Excutee("CATCH")).getException();
	}

	@Override
	public abstract IExcuter run(int paragraph);
	
}
