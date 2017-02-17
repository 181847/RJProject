package testSpace;

import testSpace.testRClass.RObjectSecond;
import testSpace.testTool.FunctionTester;
import unfinishedClass.FunctionInfo;

public class TestFunctionInfo extends Test {
	public static void main(String[] args){
		prepare();
		loader.loadJarRClass(new RObjectSecond());
		FunctionInfo hwf = new FunctionInfo("RObjectSecond", "HelloWorldFunction");
		
		new FunctionTester(hwf.getInstance()).test();
	}
}
