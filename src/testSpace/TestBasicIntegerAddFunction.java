package testSpace;

import basicInterface.IModifier;
import functionInterface.IFunction;
import rClass.RClassLoaderManager;
import rClassInterface.IRClass;
import rClassInterface.IRClassLoader;
import testSpace.testTool.FunctionTester;

public class TestBasicIntegerAddFunction {

	public static void main(String[] args) {
		RClassLoaderManager.prepareRClassLoader();
		IRClassLoader rClassLoader = RClassLoaderManager.getRClassLoader();
		IRClass rClass = rClassLoader.getRClass("Integer");
		
		IFunction testFunctionObject = rClass.Function("BasicIntegerAddFunction");
		
		IModifier modifier = (IModifier) testFunctionObject;
		modifier.modify("3");
		
		testFunctionObject.getParameter(0).writeObject(new Integer(12), "Integer");
		testFunctionObject.getParameter(2).writeObject(new Integer(-8), "Integer");
		
		FunctionTester functionTester = new FunctionTester(testFunctionObject);
		functionTester.test();
		
		modifier.modify("4");
		functionTester.test();
		
		testFunctionObject.Returnval("result").getExcutee().fire();
	}

}
