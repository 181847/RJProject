package unfinishedClass.basicRClass.RString;

import function.AbstractFunctionNeedParameterForJava;
import functionInterface.IExcuter;
import functionInterface.IFunction;

public class PrintFunction extends AbstractFunctionNeedParameterForJava implements IFunction {

	public PrintFunction() {
		super("Print", 1, 1, 1, 0);
		insertExcutee("print", 1);
		insertParameter("String", "text");
		insertExcuter("printEnd");
	}

	@Override
	public IExcuter run(int paragraph) {
		String text = (String) Parameter("text").readObject();
		System.out.println(text);
		return Excuter("printEnd");
	}

}
