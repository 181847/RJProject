package unfinishedClass.basicRClass.RException;

import basicTool.RLogger;
import rClass.AbstractRClassForJava;
import rClassInterface.IRClass;
import unfinishedClass.basicRClass.RInteger.BasicIntegerAddFunction;
import unfinishedClass.basicRClass.RInteger.IntegerAddFunction;
import unfinishedClass.basicRClass.RInteger.RIntegerConstructFunction;

public class RException extends AbstractRClassForJava implements IRClass {

	public RException() {
		super("Exception");
		insertConstructFunctionClass(RExceptionConstructFunction.class);
		insertFunctionClass(CatchExceptionFunction.class);
		insertFunctionClass(ThrowExceptionFunction.class);
		insertFunctionClass(GetMessageFunction.class);
		insertFunctionClass(PrintStackTraceFunction.class);
	}
	
	@Override
	public int loadFunction() {
		RLogger.log(this.getName() + " 加载成员Function。");
		insertConstructFunctionClass(RIntegerConstructFunction.class);
		insertFunctionClass(IntegerAddFunction.class);
		insertFunctionClass(BasicIntegerAddFunction.class);
		return 1;
	}

}
