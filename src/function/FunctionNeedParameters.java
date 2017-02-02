package function;

import functionInterface.IExcuter;

/**
 * 本抽象类继承于AbstractFunction
 * 只实现了其中的boolean needParameters()方法，只返回true
 * 所有继承这个类的Function都是需要需要参数的
 */
public abstract class FunctionNeedParameters extends AbstractFunction {
	
	public FunctionNeedParameters(){
		super();
	}
	
	public FunctionNeedParameters(int excuteeList, int parameterList, int excuterList, int returnvalList)
	{
		super(excuteeList, parameterList, excuterList, returnvalList);
	}
	
	public boolean needParameters() {
		return true;
	}

	public abstract IExcuter run(int paragraph);

	public abstract void fillContentGraph();

	public abstract void clearGraph();
}
