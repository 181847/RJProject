package unfinishedClass;

import functionInterface.IFunction;
import functionInterface.IFunctionFactory;
import functionInterface.IFunctionMaker;
import rClassInterface.IRClass;
import rClassInterface.IRReference;

public abstract class AbstractRClassWithFunctionFactory extends AbstractRClass implements IRClass {
	public IFunctionFactory functionFactory;

	/**
	 * 
	 * @param rClassName RClass的名字。
	 * @param factorySpace functionFactory初始的空间数量，
	 * 虽说空间会在不够的时候自动扩容，
	 * 但是先定义好空间数量更节省性能。
	 */
	public AbstractRClassWithFunctionFactory(String rClassName, int factorySpace) {
		super(rClassName);
		functionFactory = new FunctionFactory(factorySpace);
	}
	
	/**
	 * 向FunctionFactory插入一个FunctionMaker。
	 * @param functionMaker functionMaker实例。
	 * @return 如果已存在相同名字的元素就返回2，如果插入成功就返回1。
	 */
	public int insertFunctionMaker(IFunctionMaker functionMaker){
		return functionFactory.insertFunctionMaker(functionMaker);
	}

	@Override
	public int getFunctionIndexOf(String functionName) {
		return functionFactory.getFunctionIndexOf(functionName);
	}

	@Override
	public IFunction Function(String functionName){
		IFunction newFunctionInstance = functionFactory.getFunction(functionName);
		if (newFunctionInstance != null){
			newFunctionInstance.setRClassID(getRClassID());
		}
		return newFunctionInstance;
	}

	@Override
	public IFunction getFunction(int functionIndex){
		IFunction newFunctionInstance = functionFactory.getFunction(functionIndex);
		if (newFunctionInstance != null){
			newFunctionInstance.setRClassID(getRClassID());
		}
		return newFunctionInstance;
	}
}
