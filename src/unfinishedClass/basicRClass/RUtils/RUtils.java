package unfinishedClass.basicRClass.RUtils;

import basicTool.RLogger;
import rClass.AbstractRClassForJava;

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
