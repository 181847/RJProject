package testSpace.originalTest_without_jUnit.unordered;

import functionInterface.IFunction;
import rClassInterface.IRClass;
import runner.Runner;
import runnerInterface.IRunner;
import unfinishedClass.customFunction.HeadSentryFunction;

public class TestHeadSentryFunction extends Test {

	public static void main(String[] args) {
		prepare();
		HeadSentryFunction fun1 = new HeadSentryFunction();
		fun1.setName("firstSentry");
		fun1.insertExcutee("road1", 1);
		fun1.insertParameter("String", "text");
		
		IRClass rclass = loader.getRClass("String");
		IFunction fun2 = rclass.Function("Print");
		
		fun2.Parameter("text").linkReturnval(fun1.Returnval("text"));
		fun1.Excuter("road1").linkExcutee(fun2.Excutee("print"));
		
		fun1.Parameter("text").writeObject("Hello SentryFunction!", "String");
		
		IRunner runner = new Runner("LiuXiang");
		runner.pushExcutee(fun1.Excutee("road1"));
		runner.setRunable(true);
		
		runner.run();
	}

}
