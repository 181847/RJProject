package unfinishedClass.basicRClass.RException;

import basicInterface.IExceptionHolder;
import functionInterface.IExcuter;
import functionInterface.IFunction;
import functionInterface.IReturnval;
import unfinishedClass.AbstractFunctionCatchException;

/**
 * Excutee: catch 异常线路的入口。
 * Paramter: String ExceptionType 用于日后扩展的字符参数，目前无实际作用。
 * Excuter：deal 正常线路的出口。
 * Returnval：以RReference形式存在的Exception参数。
 * 本Function用于处理异常状态线路下的Runner，
 * 接受异常线路中的Exception对象，并将其用参数分离出来，
 * 然后使得Runner通过名为“deal”的Excuter，回到正常线路上来。
 */
public class CatchExceptionFunction extends AbstractFunctionCatchException implements IFunction {

	public CatchExceptionFunction() {
		super("CatchException", 1, 0, 1, 1);
		insertParameter("String", "exceptionType");
		insertExcuter("deal");
		insertReturnval("Exception", "exception");
	}

	@Override
	public IExcuter run(int paragraph) {
		Exception exception = ( (IExceptionHolder) Excutee("CATCH") ).getException();
		IReturnval returnval = Returnval("e");
		returnval.mallocSpace(1);
		returnval.writeObject(exception, "Exception");
		return Excuter("deal");
	}
}
