package testSpace;
import functionInterface.*;
import runnerInterface.IRunner;

public class testClass {

	public static void main(String[] args) {
		IFunction function = new HelloWorldFunction();
		IFunction function2 = new HelloWorldFunction();
		
		function.Parameter("n").writeObject(new Integer(45), "Integer");
		function.Parameter("ch").writeObject(new Character('@'), "Character");
		
		
		function.Excuter("endTry").linkExcutee(function2.Excutee("tryPara&Retu"));
		function2.Parameter("n").linkReturnval(function.Returnval("return"));
		//循环测试
		//
		function2.Excuter("endTry").linkExcutee(function.Excutee("tryPara&Retu"));
		function.Parameter("n").linkReturnval(function2.Returnval("return"));
		
		IRunner runner = new Runner("LiuXiang");
		runner.pushExcutee(function.Excutee("tryPara&Retu"));
		
		runner.setRunable(true);
		
		long startMili1 = System.currentTimeMillis();
		runner.run();
		long endMili1 = System.currentTimeMillis();
		
		
		long startMili2 = System.currentTimeMillis();
		for (int i = 0; i < 5000000; ++i){
			System.out.println(i);
		}
		long endMili2 = System.currentTimeMillis();
		
		System.out.println("timeCost1:" + (endMili1 - startMili1));
		System.out.println("timeCost2:" + (endMili2 - startMili2));
		
	}

}
