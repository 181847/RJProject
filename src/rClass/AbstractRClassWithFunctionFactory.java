package rClass;

import basicTool.RLogger;
import function.tool.FunctionFactory;
import functionInterface.IFunction;
import functionInterface.IFunctionFactory;
import functionInterface.IFunctionMaker;
import rClassInterface.IRClass;
import rClassInterface.IRReference;

/**
 * 这个类型的Function用一个functionFactory来存储生产Function实例对象的FunctionMaker，
 * 注意：构造Function只能有一个，且构造Function的Maker不会放在FunctionFactory中，
 * 而是由AbstractRClass中的一个成员变量来存储ConstructFunctionMaker。
 */
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
	
	@Override
	public abstract IRReference getNewInstance();
	
	/**
	 * 专门适用于完全自定义型RClass的方法，通过名字读取相应的function描述文件，
	 * 通过customFunction的名字，
	 * 来填充自定义customFunction的内容图表。
	 * @param funcitonName
	 * 		customFunction的名字。
	 * @param headSentryFunction
	 * 		customFunction的headSentryFunction。
	 * @param rearSentryFunction
	 * 		customFunction的rearSentryFunction。
	 * @return
	 * 		functionName为null或者空串，返回0；
	 * 		两个Function参数为null返回-1；
	 * 		成功返回1。
	 */
	@Override
	public int fillFunctionGraphOf(String functionName,
			IFunction headSentryFunction,
			IFunction rearSentryFunction){
		
		if (functionName == null ||
				functionName.isEmpty()){
			RLogger.logError("RClass填充functionGraph出错，functionName为null或空串。");
			return 0;
		}//if
		if (headSentryFunction == null ||
				rearSentryFunction == null){
			RLogger.logError("RClass填充functionGraph出错，"
					+ "headSentryFunction或rearSentryFunction为null。");
			return -1;
		}//if
		return functionFactory.fillFunctionGraphOf(functionName, 
				headSentryFunction, rearSentryFunction);
	}

	/**
	 * 专门适用于完全自定义型RClass的方法，通过序号读取相应的function描述文件，
	 * 通过customFunction的序号，
	 * 来填充自定义customFunction的内容图表。
	 * @param funcitonName
	 * 		customFunction的名字。
	 * @param headSentryFunction
	 * 		customFunction的headSentryFunction。
	 * @param rearSentryFunction
	 * 		customFunction的rearSentryFunction。
	 * @return
	 * 		functionIndex小于0，返回0；
	 * 		两个Function参数为null返回-1；
	 * 		成功返回1。
	 */
	@Override
	public int fillFunctionGraphOf(int functionIndex,
			IFunction headSentryFunction,
			IFunction rearSentryFunction){
		
		if (functionIndex < 0){
			RLogger.logError("RClass填充functionGraph出错，functionName为null或空串。");
			return 0;
		}//if
		if (headSentryFunction == null ||
				rearSentryFunction == null){
			RLogger.logError("RClass填充functionGraph出错，"
					+ "headSentryFunction或rearSentryFunction为null。");
			return -1;
		}//if
		return functionFactory.fillFunctionGraphOf(functionIndex, 
				headSentryFunction, rearSentryFunction);
	}
	
	/**
	 * 向FunctionFactory插入一个FunctionMaker。
	 * @param functionMaker functionMaker实例。
	 * @return 如果已存在相同名字的元素就返回2，如果插入成功就返回1。
	 */
	public int insertFunctionMaker(IFunctionMaker functionMaker){
		functionMaker.setRClassID(getRClassID());
		return functionFactory.insertFunctionMaker(functionMaker);
	}

	/**
	 * 获取指定名字的Function（或者FunctionMaker）的序号，
	 * @param functionName
	 * 		Function（或者FunctionMaker）的名字。
	 * @return
	 * 		Function（或者FunctionMaker）的序号。
	 */
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
