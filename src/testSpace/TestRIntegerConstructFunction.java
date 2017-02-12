package testSpace;

import functionInterface.IFunction;
import rClass.RClassLoaderManager;
import rClassInterface.IRClass;
import rClassInterface.IRClassLoader;
import testSpace.testTool.FunctionTester;

public class TestRIntegerConstructFunction {

	public static void main(String[] args) {
		RClassLoaderManager.prepareRClassLoader();
		IRClassLoader rClassLoader = RClassLoaderManager.getRClassLoader();
		IRClass rClass = rClassLoader.getRClass("Integer");
		
		IFunction constructor = rClass.ConstructFunction();
		
		FunctionTester functionTester = new FunctionTester(constructor);
		functionTester.test();
		
		constructor.Parameter("value").writeObject("45", "String");
		functionTester.test();
	}

}
