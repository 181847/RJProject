package unfinishedClass.basicRClass.RException;

import function.AbstractFunctionNeedParameterForJava;
import functionInterface.IExcuter;
import functionInterface.IFunction;
import functionInterface.IReturnval;

public class PrintStackTraceFunction extends AbstractFunctionNeedParameterForJava implements IFunction {

	public PrintStackTraceFunction() {
		super("PrintStackTrace", 1, 1, 1, 1);
		insertExcutee("print", 1);
		insertTHIS("Exception");
		insertExcuter("printEnd");
		insertReturnval("Exception", "exception");
	}

	@Override
	public IExcuter run(int paragraph) {
		Exception e = (Exception) THIS().readObject();
		e.printStackTrace();
		IReturnval returnval = Returnval("exception");
		returnval.mallocSpace(1);
		returnval.writeObject(e, "Exception");
		return Excuter("printEnd");
	}

}
