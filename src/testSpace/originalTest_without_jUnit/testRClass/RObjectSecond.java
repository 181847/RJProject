package testSpace.originalTest_without_jUnit.testRClass;

import basicTool.RLogger;
import rClass.AbstractRClassForJava;
import rClassInterface.IRClass;
import unfinishedClass.basicRClass.RUtils.HelloWorldFunction;

public class RObjectSecond extends AbstractRClassForJava implements IRClass {

	public RObjectSecond() {
		super("RObjectSecond");
	}
	
	@Override
	public int loadFunction() {
		RLogger.log(this.getName() + " 加载成员Function。");
		insertFunctionClass(HelloWorldFunction.class);
		return 1;
	}
}
