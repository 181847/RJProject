package unfinishedClass.basicRClass.RInteger;

import rClass.AbstractRClassForJava;
import rClass.RReference;
import rClassInterface.IRClass;
import rClassInterface.IRReference;
import unfinishedClass.RLogger;
import unfinishedClass.basicRClass.RUtils.ExceptionMakerFunction;
import unfinishedClass.basicRClass.RUtils.HelloWorldFunction;

public class RInteger extends AbstractRClassForJava implements IRClass{

	public RInteger(){
		super("Integer", 2);
	}
	
	@Override
	public IRReference getNewInstance() {
		IRReference newInstance = new RReference();
		newInstance.mallocSpace(1);
		newInstance.writeObject(new Integer(0), "Integer");
		return newInstance;
	}
	
	@Override
	public int loadFunction() {
		RLogger.log(this.getName() + " 加载成员Function。");
		insertConstructFunctionClass(RIntegerConstructFunction.class);
		insertFunctionClass(IntegerAddFunction.class);
		insertFunctionClass(BasicIntegerAddFunction.class);
		return 1;
	}
}
