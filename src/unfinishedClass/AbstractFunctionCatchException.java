package unfinishedClass;

import function.AbstractFunctionNeedParameterForJava;
import functionInterface.IExcuter;
import functionInterface.IFunction;

/**
 * Excutee: CATCH 特殊类型的excutee，类型为ExceptionExcutee，
 * 内部存储着一个Exception变量，用于Runner的异常线路传递。
 */
public abstract class AbstractFunctionCatchException extends AbstractFunctionNeedParameterForJava implements IFunction {

	/**
	 * 在本抽象类中自动为excuteeList加一，
	 * 多出来的一个空间用来放置一个名为“CATCH”的ExceptionExcutee，
	 * 且它对应的代码段的数字为0。
	 * @param name function的名字
	 * @param excuteeList excutee列表的初始化空间数量。
	 * @param parameterList parameter列表的初始化空间数量。
	 * @param excuterList parameter列表的初始化空间数量。
	 * @param returnvalList returnval列表的初始化空间数量。
	 */
	public AbstractFunctionCatchException(String name, int excuteeList, int parameterList, int excuterList,
			int returnvalList) {
		super(name, excuteeList, parameterList, excuterList, returnvalList);
		this.insertExcutee(new ExceptionExcutee("CATCH", 0));
	}

	@Override
	public abstract IExcuter run(int paragraph);
}
