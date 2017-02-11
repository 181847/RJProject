package testSpace.testRClass;

import rClassInterface.IRClass;
import unfinishedClass.AbstractRClassWithFunctionFactory;

public class RObjectSecond extends AbstractRClassWithFunctionFactory implements IRClass {

	public RObjectSecond() {
		super("RObjectSecond");
		insertFunctionClass(HelloWorldFunction.class);
	}
}
