package testSpace;

import functionInterface.IFunction;
import rClassInterface.IRClass;
import testSpace.testTool.FunctionTester;
import unfinishedClass.RObject;

public class TestRClassOfRObject {
	public static void main(String[] args){
		IRClass rclass = new RObject();
		IFunction function;
		
		try {
			function = rclass.Function("HelloWorldFunction");
			new FunctionTester(function).test();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		
	}

}
