package testSpace;

import functionInterface.IFunction;
import rClass.RClassLoaderManager;
import rClassInterface.IRClass;
import testSpace.testTool.FunctionTester;

public class TestRClassLoader {

	public static void main(String[] args) {
		RClassLoaderManager.prepareRClassLoader();
		RClassLoaderManager.getRClassLoader().loadRClassFrom("D:/tempFile/RObject.jar");
		
		int rClassID = RClassLoaderManager.getRClassLoader().getRClassIDOf("RObject");
		IRClass rClass = RClassLoaderManager.getRClassLoader().getRClass("RObject");
		
		System.out.println("RObject的ID: " + rClassID);
		
		IFunction function;
		function = rClass.Function("HelloWorldFunction");
		new FunctionTester(function).test();
	}

}
