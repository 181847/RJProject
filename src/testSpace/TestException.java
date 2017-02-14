package testSpace;

import functionInterface.IFunction;
import rClass.RClassLoaderManager;
import rClassInterface.IRClass;
import rClassInterface.IRClassLoader;
import runner.Runner;
import runnerInterface.IRunner;

public class TestException {

	public static void main(String[] args) {
		RClassLoaderManager.prepareRClassLoader();
		IRClassLoader loader = RClassLoaderManager.getRClassLoader();
		IRClass rclass1 = loader.getRClass("String");
		IRClass rclass2 = loader.getRClass("Exception");
		
		IFunction function1 = rclass1.Function("Print");
		IFunction construct = rclass2.ConstructFunction();
		IFunction function2 = rclass2.Function("GetMessage");
		IFunction function3 = rclass2.Function("ThrowException");
		IFunction function4 = rclass2.Function("CatchException");
		
		construct.Parameter("message").writeObject("hello Exception!", "String");
		construct.Excuter("constructEnd").linkExcutee(function3.Excutee("throw"));
		function3.Parameter("exception").linkReturnval(construct.Returnval("newInstance"));
		function3.assignExceptionHandler(function4);
		
		function4.Excuter("deal").linkExcutee(function1.Excutee("print"));
		function1.Parameter("text").linkReturnval(function2.Returnval("message"));
		function2.Parameter("exception").linkReturnval(function4.Returnval("exception"));
		
		IRunner runner = new Runner("LiuXiang");
		runner.pushExcutee(construct.Excutee("construct"));
		
		runner.setRunable(true);
		runner.run();
		
		System.out.println("testEnd");
	}

}
