package testSpace.testRClass;

import rClass.AbstractRClassWithFunctionFactory;
import rClassInterface.IRClass;

public class RObjectSecond extends AbstractRClassWithFunctionFactory implements IRClass {

	public RObjectSecond() {
		super("RObjectSecond");
		insertFunctionClass(HelloWorldFunction.class);
	}
}
