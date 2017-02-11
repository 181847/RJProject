package testSpace;

import functionInterface.IFunction;
import rClassInterface.IRClass;
import testSpace.testRClass.RObject;
import testSpace.testRClass.RObjectSecond;
import testSpace.testTool.FunctionTester;
import unfinishedClass.RClassLoaderManager;

public class TestRClassOfRObject {
	public static void main(String[] args){
		RClassLoaderManager.prepareRClassLoader();
		IRClass rclass = new RObjectSecond();
		IFunction function;
		
		function = rclass.Function("HelloWorldFunction");
		new FunctionTester(function).test();
		
	}
}
