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
	
	/**
	 * 为functionFactory，插入一个FunctionMaker，
	 * 这里不手动传入Function的名字，而是由程序自动生成一个Function实例，
	 * 然后从这个新生成的Function实例获得名字，进而设置FunctionMaker的名字。
	 * @param functionClass 用于直接创建Function的FunctionClass。
	 * @return 如果已存在相同名字的元素就返回2； 如果插入成功就返回1。
	 */
	public int insertFunctionClass(Class<? extends IFunction> functionClass){
		return functionFactory.insertFunctionMaker(new FunctionMakerWithClass(functionClass));
		
	}
	
	/**
	 * 为FunctionFactory，插入一个构造FunctionMaker。
	 * @param functionClass 一个用于产生构造Function的Function类型。
	 * @return 成功返回1，如果已存在就返回-1，
	 * 不对原来的构造Function做任何改动。
	 */
	public int insertConstructFunctionClass(Class<? extends IFunction> functionClass){
		return functionFactory.insertConstructFunctionMaker(new FunctionMakerWithClass(functionClass));
	}
	
	/**
	 * 获取这个RClass的构造Function。
	 * @return 一个构造Function实例。
	 */
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
