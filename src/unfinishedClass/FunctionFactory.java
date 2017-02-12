package unfinishedClass;

import basicTool.NamedItemList;
import functionInterface.IFunction;
import functionInterface.IFunctionFactory;
import functionInterface.IFunctionMaker;

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
}
