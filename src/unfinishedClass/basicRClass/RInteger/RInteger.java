package unfinishedClass.basicRClass.RInteger;

import rClass.RReference;
import rClassInterface.IRClass;
import rClassInterface.IRReference;
import unfinishedClass.AbstractRClassForJava;

public class RInteger extends AbstractRClassForJava implements IRClass{

	public RInteger(){
		super("Integer", 2);
		insertConstructFunctionClass(RIntegerConstructFunction.class);
		insertFunctionClass(IntegerAddFunction.class);
		insertFunctionClass(BasicIntegerAddFunction.class);
	}
	
	@Override
	public IRReference getNewInstance() {
		IRReference newInstance = new RReference();
		newInstance.mallocSpace(1);
		newInstance.writeObject(new Integer(0), "Integer");
		return newInstance;
	}
}
