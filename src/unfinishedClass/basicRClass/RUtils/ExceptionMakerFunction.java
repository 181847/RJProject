package unfinishedClass.basicRClass.RUtils;

import function.AbstractFunctionForJava;
import functionInterface.IExcuter;
import functionInterface.IFunction;

public class ExceptionMakerFunction extends AbstractFunctionForJava implements IFunction {

	public ExceptionMakerFunction() {
		super("ExceptionMaker", 1, 0, 1, 0);
		insertExcutee("makeExcpetion", 1);
		insertExcuter("neverTouched");
	}

	@Override
	public IExcuter run(int paragraph) {
		Object exceptionMaker = null;
		exceptionMaker.toString();
		return Excuter("neverTouched");
	}
	
	@Override
	public boolean needParameters(){
		return false;
	}
}
