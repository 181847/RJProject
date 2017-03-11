package testSpace;

import basicTool.RLogger;
import functionInterface.IFunction;
import rClassInterface.IRClass;
import unfinishedClass.basicRClass.RUtils.ExceptionMakerFunction;
import unfinishedClass.customFunction.HeadSentryFunction;
import unfinishedClass.customFunction.RearSentryFunction;

public class TestRearSentryFunction extends Test {

	public static void main(String[] args) {
		prepare();
		IRClass rclass1 = loader.getRClass("String");
		IRClass rclass2 = loader.getRClass("Exception");
		
		HeadSentryFunction headSentry = new HeadSentryFunction();
		headSentry.insertExcutee("head", 1);
		IFunction exceptionMaker = new ExceptionMakerFunction();
		RearSentryFunction rearSentry = new RearSentryFunction();
		rearSentry.insertExcutee("rear", 1);
		

		headSentry.Excuter("head").linkExcutee(exceptionMaker.Excutee("makeExcpetion"));
		exceptionMaker.Excuter("neverTouched").linkExcutee(rearSentry.Excutee("rear"));
		
		IFunction function1 = rclass1.Function("Print");
		

		
		IFunction excepCatcher = rclass2.Function("CatchException");
		IFunction excepSolver1 = rclass2.Function("PrintStackTrace");
		IFunction excepSolver2 = rclass2.Function("GetMessage");
		IFunction excepSolver3 = rclass1.Function("Print");
		
		headSentry.assignExceptionHandler(rearSentry);
		exceptionMaker.assignExceptionHandler(rearSentry);
		rearSentry.assignExceptionHandler(excepCatcher);
		
		
		//发生异常时的处理方式
		excepCatcher.Excuter("deal").linkExcutee(excepSolver1.Excutee("print"));
		excepSolver1.Parameter("THIS").linkReturnval(excepCatcher.Returnval("exception"));
		excepSolver1.Excuter("printEnd").linkExcutee(excepSolver3.Excutee("print"));
		excepSolver3.Parameter("text").linkReturnval(excepSolver2.Returnval("message"));
		excepSolver2.Parameter("THIS").linkReturnval(excepSolver1.Returnval("exception"));
		
		//未发生异常使得处理方式
		rearSentry.Excuter("rear").linkExcutee(function1.Excutee("print"));
		function1.Parameter("text").writeObject("ExceptionMakerFunction没有发生异常。", "String");
		
		runner.pushExcutee(headSentry.Excutee("head"));
		runner.run();
		
		RLogger.log("testEnd");
	}

}
