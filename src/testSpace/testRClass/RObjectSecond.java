package testSpace.testRClass;

import rClass.AbstractRClassForJava;
import rClassInterface.IRClass;

public class RObjectSecond extends AbstractRClassForJava implements IRClass {

	public RObjectSecond() {
		super("RObjectSecond");
		insertFunctionClass(HelloWorldFunction.class);
	}
}
