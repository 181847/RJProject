package function;

import functionInterface.IExcuter;

/**
 * 本抽象类继承于AbstractFunction
 * 只实现了其中的boolean needParameters()方法，只返回true
 * 所有继承这个类的Function都是需要需要参数的
 */
public abstract class FunctionNeedParameters extends AbstractFunction {
	
	/**
	 * @param name function的名字。
	 */
	public FunctionNeedParameters(String name){
		super(name);
	}
	
	/**
	 * @param name function的名字
	 * @param excuteeList excutee列表的空间数量
	 * @param parameterList parameter列表的空间数量
	 * @param excuterList excuter列表的空间数量
	 * @param returnvalList returnval列表的空间数量
	 * 列表空间无需担心，如果不够程序会自动扩张，
	 * 但是预先就设置好的话能节省性能。
	 */
	public FunctionNeedParameters(String name, 
			int excuteeList, int parameterList, int excuterList, int returnvalList)
	{
		super(name, excuteeList, parameterList, excuterList, returnvalList);
	}
	
	/**
	 * 所有继承这个抽象类的Function类都是需要参数的
	 */
	public boolean needParameters() {
		return true;
	}

	public abstract IExcuter run(int paragraph);
	public abstract void fillContentGraph();
	public abstract void clearGraph();
}
