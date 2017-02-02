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
		
		IRunner runner = new Runner("LiuXiang");
		runner.pushExcutee(function.Excutee("tryPara&Retu"));
		
		runner.setRunable(true);;
		runner.run();
	}

}
