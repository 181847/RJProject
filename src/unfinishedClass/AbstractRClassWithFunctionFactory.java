package unfinishedClass;

import functionInterface.IFunction;
import functionInterface.IFunctionFactory;
import functionInterface.IFunctionHeadSlot;
import functionInterface.IFunctionRearSlot;
import rClassInterface.IRClass;
import rClassInterface.IRReference;

public class AbstractRClassWithFunctionFactory extends AbstractRClass implements IRClass {
	public IFunctionFactory functionFactory;

	public AbstractRClassWithFunctionFactory(String rClassName) {
		super(rClassName);
		functionFactory = new FunctionFactory(5);
	}
	
	@Override
	public IRReference getNewInstance() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 为functionFactory，插入一个FunctionMaker。
	 * @param functionName FuntionMaker创建的Function的名字。
	 * @param functionClass 用于直接创建Function的FunctionClass。
	 * @return 如果已存在相同名字的元素就返回2； 如果插入成功就返回1。
	 */
	public int insertFunctionClass(String functionName, Class<? extends IFunction> functionClass){
		return functionFactory.insertFunctionMaker(new FunctionMakerWithClass(functionName, functionClass));	
	}
	
	public int insertFunctionClass(Class<? extends IFunction> functionClass){
		return functionFactory.insertFunctionMaker(new FunctionMakerWithClass(functionClass));
		
	}
	
	@Override
	public IFunction ConstructFunction() {
		IFunction newFunctionInstance = functionFactory.getConstructFunction();
		if (newFunctionInstance != null){
			newFunctionInstance.setRClassID(getRClassID());
		}
		return newFunctionInstance;
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

	@Override
	public int fillFunctionGraphOf(String functionName, IFunctionRearSlot rearSlot, IFunctionHeadSlot headSlot) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int fillFunctionGraphOf(int functionIndex, IFunctionRearSlot rearSlot, IFunctionHeadSlot headSlot) {
		// TODO Auto-generated method stub
		return 0;
	}
}
