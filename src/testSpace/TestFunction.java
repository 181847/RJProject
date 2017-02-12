package testSpace;

import functionInterface.IFunction;
import rClass.RClassLoaderManager;
import testSpace.testRClass.HelloWorldFunction;
import testSpace.testTool.FunctionTester;

public class TestFunction {
	public static void main(String[] args){
		RClassLoaderManager.prepareRClassLoader();
		
		IFunction function = new HelloWorldFunction();
		function.Parameter("n").writeObject(new Integer(45), "Integer");
		function.Parameter("ch").writeObject(new Character('@'), "Character");
		
		new FunctionTester(function).test();
	}

}
