package function.tool;

import basicTool.NamedItemList;
import basicTool.RLogger;
import functionInterface.IFunction;
import functionInterface.IFunctionFactory;
import functionInterface.IFunctionMaker;

/**
 * 存储FunctionMaker的列表，用于RClass对象中。
 */
public class FunctionFactory extends NamedItemList implements IFunctionFactory {
	
	public FunctionFactory(int space){
		super(space);
	}
	
	public FunctionFactory(){
		super();
	}

	@Override
	public int getFunctionIndexOf(String functionName) {
		return getIndexOf(functionName);
	}

	@Override
	public IFunction getFunction(String functionName) {
		IFunctionMaker currentMaker = (IFunctionMaker)getItem(functionName);
		if (currentMaker == null){
			return null;
		}
		return currentMaker.makeFunction();
	}

	@Override
	public IFunction getFunction(int functionIndex) {
		IFunctionMaker currentMaker = (IFunctionMaker)getItem(functionIndex);
		if (currentMaker == null){
			return null;
		}
		return currentMaker.makeFunction();
	}

	@Override
	public int insertFunctionMaker(IFunctionMaker functionMaker) {
		return insertItem(functionMaker);
	}

	@Override
	public int fillFunctionGraphOf(String functionName, 
			IFunction headSentryFunction, 
			IFunction rearSentryFunction) {
		IFunctionMaker currentMaker = (IFunctionMaker)getItem(functionName);
		if (currentMaker == null){
			RLogger.logError("FunctionFactory 填充CustomFunction出错，"
					+ "没有找到指定名字的FunctionMaker，请检查："
					+ "CustomFunction的名字：" + functionName);
		}
		
		return currentMaker.fillFunctionGraphOf(headSentryFunction, rearSentryFunction);
	}

	@Override
	public int fillFunctionGraphOf(int functionIndex, IFunction headSentryFunction, IFunction rearSentryFunction) {
		IFunctionMaker currentMaker = (IFunctionMaker)getItem(functionIndex);
		if (currentMaker == null){
			RLogger.logError("FunctionFactory 填充CustomFunction出错，"
					+ "没有找到指定编号的FunctionMaker，请检查："
					+ "FunctionFactory的元素数量：" + getNum()
					+ "CustomFunction的编号：" + functionIndex);
		}
		return currentMaker.fillFunctionGraphOf(headSentryFunction, rearSentryFunction);
	}
}
