package unfinishedClass.basicRClass.RException;

import function.AbstractBasicCalculatorFunction;
import functionInterface.IExcuter;
import functionInterface.IFunction;

public class GetMessageFunction extends AbstractBasicCalculatorFunction implements IFunction {

	public GetMessageFunction() {
		super("GetMessage", 1, 1);
		insertParameter("Exception", "exception");
		insertReturnval("String", "message");
	}

	@Override
	public IExcuter run(int paragraph) {
		Exception e = (Exception) Parameter("exception").readObject();
		Returnval("message").writeObject(e.getMessage(), "String");
		return null;
	}
}
