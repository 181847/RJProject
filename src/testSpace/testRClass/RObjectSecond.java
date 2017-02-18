package testSpace.testRClass;

import rClass.AbstractRClassForJava;
import rClassInterface.IRClass;
import unfinishedClass.basicRClass.RUtils.HelloWorldFunction;

public class RObjectSecond extends AbstractRClassForJava implements IRClass {

	public RObjectSecond() {
		super("RObjectSecond");
		insertFunctionClass(HelloWorldFunction.class);
	}
}
