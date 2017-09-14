package testSpace.originalTest_without_jUnit.unordered;

import basicInterface.IModifier;
import functionInterface.IFunction;
import rClass.RClassLoaderManager;
import rClassInterface.IRClass;
import runner.Runner;
import runnerInterface.IRunner;

public class testBasicCalculator {
	public static void main(String[] args){
		
		RClassLoaderManager.prepareRClassLoader();
		IRClass rClass = RClassLoaderManager.getRClassLoader().getRClass("Integer");
		IFunction function1 = rClass.Function("IntegerAddFunction");
		IFunction function2 = rClass.Function("BasicIntegerAddFunction");
		
		IModifier modifier1 = (IModifier) function1;
		IModifier modifier2 = (IModifier) function2;
		
		modifier1.modify("1");
		modifier2.modify("1");
		
		function1.Parameter("param_1").writeObject(new Integer(1), "Integer");
		function1.Parameter("param_2").linkReturnval(function2.Returnval("result"));
		
		function2.Parameter("param_1").writeObject(new Integer(2), "Integer");
		function2.Parameter("param_2").writeObject(new Integer(3), "Integer");
		
		IRunner runner = new Runner("LiuXiang");
		runner.pushExcutee(function1.Excutee("add"));
		
		runner.setRunable(true);
		runner.run();
		
		
		System.out.println("the result of function1: " + function1.Returnval("result").readObject());
	}
}
