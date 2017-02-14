package unfinishedClass.basicRClass.RException;

import function.AbstractConstructFunctionForJava;
import functionInterface.IExcuter;
import functionInterface.IFunction;
import functionInterface.IReturnval;

public class RExceptionConstructFunction extends AbstractConstructFunctionForJava implements IFunction {

	public RExceptionConstructFunction() {
		super("Exception", 1);
		insertParameter("String", "message");
	}

	@Override
	public IExcuter run(int paragraph) {
		String exceptionMessage = (String) getParameter(1).readObject();
		if (exceptionMessage == null){
			exceptionMessage = "";
		}
		IReturnval returnval = getReturnval(0);
		returnval.mallocSpace(1);
		returnval.writeObject(new Exception(exceptionMessage), "Exception");
		return Excuter("constructEnd");
	}

}
