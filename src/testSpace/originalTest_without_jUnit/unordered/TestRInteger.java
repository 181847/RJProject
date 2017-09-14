package testSpace.originalTest_without_jUnit.unordered;

import basicInterface.IModifier;
import functionInterface.IFunction;
import rClass.RClassLoaderManager;
import rClassInterface.IRClass;
import rClassInterface.IRClassLoader;
import testSpace.originalTest_without_jUnit.testTool.FunctionTester;

public class TestRInteger {

	public static void main(String[] args) {
		RClassLoaderManager.prepareRClassLoader();
		IRClassLoader rClassLoader = RClassLoaderManager.getRClassLoader();
		IRClass rClass = rClassLoader.getRClass("Integer");
		
		IFunction testFunctionObject = rClass.Function("IntegerAddFunction");
		
		IModifier modifier = (IModifier) testFunctionObject;
		modifier.modify("3");
		
		testFunctionObject.getParameter(0).writeObject(new Integer(75), "Integer");
		testFunctionObject.getParameter(2).writeObject(new Integer(-8), "Integer");
		
		FunctionTester functionTester = new FunctionTester(testFunctionObject);
		functionTester.test();
		
		modifier.modify("4");
		functionTester.test();
	}
}
