package function;

import functionInterface.IExcuter;
import functionInterface.IFunction;

public abstract class AbstractFunctionNeedParameterForJava extends AbstractFunctionForJava implements IFunction {

	/**
	 * @param name function的名字
	 * @param excuteeList excutee列表的初始化空间数量。
	 * @param parameterList parameter列表的初始化空间数量。
	 * @param excuterList excuter列表的初始化空间数量。
	 * @param returnvalList returnval列表的初始化空间数量。
	 */
	public AbstractFunctionNeedParameterForJava(String name,
			int excuteeList, int parameterList,
			int excuterList,int returnvalList) {
		super(name, excuteeList, parameterList, excuterList, returnvalList);
	}

	@Override
	public abstract IExcuter run(int paragraph);

	/**
	 * @return 继承了AbstractFunctionNeedParameterForJava的Function类，
	 * 如果不重载，这个返回值永远为true。
	 */
	@Override
	public boolean needParameters() {
		return true;
	}

}
