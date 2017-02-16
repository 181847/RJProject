package unfinishedClass.basicRClass.RException;

import rClass.AbstractRClassForJava;
import rClassInterface.IRClass;

public class RException extends AbstractRClassForJava implements IRClass {

	public RException() {
		super("Exception");
		insertConstructFunctionClass(RExceptionConstructFunction.class);
		insertFunctionClass(CatchExceptionFunction.class);
		insertFunctionClass(ThrowExceptionFunction.class);
		insertFunctionClass(GetMessageFunction.class);
		insertFunctionClass(PrintStackTraceFunction.class);
	}

}
