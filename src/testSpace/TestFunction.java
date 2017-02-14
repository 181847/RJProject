package testSpace;

import functionInterface.IFunction;
import rClass.RClassLoaderManager;
import rClassInterface.IRClass;
import rClassInterface.IRClassLoader;
import testSpace.testTool.FunctionTester;

public class TestFunction {
	public static void main(String[] args){
		RClassLoaderManager.prepareRClassLoader();
		IRClassLoader loader = RClassLoaderManager.getRClassLoader();
		IRClass rclass = loader.getRClass("String");
		
		IFunction function = rclass.Function("Print");
		function.Parameter("text").writeObject("testMessage", "String");
		
		new FunctionTester(function).test();
	}

}
