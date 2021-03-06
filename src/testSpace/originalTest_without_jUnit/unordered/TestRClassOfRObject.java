package testSpace.originalTest_without_jUnit.unordered;

import functionInterface.IFunction;
import rClass.RClassLoaderManager;
import rClassInterface.IRClass;
import testSpace.originalTest_without_jUnit.testRClass.RObjectSecond;
import testSpace.originalTest_without_jUnit.testTool.FunctionTester;

public class TestRClassOfRObject {
	public static void main(String[] args){
		RClassLoaderManager.prepareRClassLoader();
		IRClass rclass = new RObjectSecond();
		RClassLoaderManager.getRClassLoader().loadJarRClass(rclass);
		
		IFunction function;
		function = rclass.Function("HelloWorldFunction");
		new FunctionTester(function).test();
		
		System.out.println("RObjectSecond ID:" + rclass.getRClassID());
	}
}
