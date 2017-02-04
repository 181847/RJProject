package testSpace;

import functionInterface.IFunction;
import runnerInterface.IRunner;
import unfinishedClass.HelloWorldFunction;
import unfinishedClass.Runner;

public class TestRunner {
	public static void main(String[] args) {
		IFunction function = new HelloWorldFunction();
		IFunction function2 = new HelloWorldFunction();
		
		function.Parameter("n").writeObject(new Integer(45), "Integer");
		function.Parameter("ch").writeObject(new Character('@'), "Character");
		
		
		function.Excuter("endTry").linkExcutee(function2.Excutee("tryPara&Retu"));
		function2.Parameter("n").linkReturnval(function.Returnval("return"));
		//循环链接两个Function的执行节点以及参数
		function2.Excuter("endTry").linkExcutee(function.Excutee("tryPara&Retu"));
		
		//创建新Runner
		IRunner runner = new Runner("LiuXiang");
		runner.pushExcutee(function.Excutee("tryPara&Retu"));
		
		runner.setRunable(true);
		
		long startMili1 = System.currentTimeMillis();
		runner.run();
		long endMili1 = System.currentTimeMillis();
		
		
		long startMili2 = System.currentTimeMillis();
		int costNum = 0;
		for (int i = 0; i < 500; ++i){
			System.out.println(i);
			costNum += costNum += 45;
		}
		long endMili2 = System.currentTimeMillis();
		
		System.out.println("timeCost1:" + (endMili1 - startMili1));
		System.out.println("timeCost2:" + (endMili2 - startMili2));
		
	}
}
