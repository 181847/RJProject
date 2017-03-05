package unfinishedClass.basicRClass.RUtils;

import rClass.AbstractRClassForJava;
import unfinishedClass.RLogger;

public class RUtils extends AbstractRClassForJava {

	public RUtils() {
		super("Utils");
		
	}
	

	
	@Override
	public int loadFunction() {
		RLogger.log(this.getName() + " 加载成员Function。");
		insertFunctionClass(ExceptionMakerFunction.class);
		insertFunctionClass(HelloWorldFunction.class);
		return 1;
	}
}
