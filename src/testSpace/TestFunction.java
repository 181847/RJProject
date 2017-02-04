package testSpace;

import functionInterface.IFunction;
import testSpace.testTool.FunctionTester;
import unfinishedClass.HelloWorldFunction;

public class TestFunction {
	public static void main(String[] args){
		IFunction function = new HelloWorldFunction();
		function.Parameter("n").writeObject(new Integer(45), "Integer");
		function.Parameter("ch").writeObject(new Character('@'), "Character");
		
		new FunctionTester(function).test();
	}

}
