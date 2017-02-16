package unfinishedClass.basicRClass.RUtils;

import rClass.AbstractRClassForJava;

public class RUtils extends AbstractRClassForJava {

	public RUtils() {
		super("Utils");
		insertFunctionClass(ExceptionMakerFunction.class);
	}
}
