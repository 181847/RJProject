package unfinishedClass.basicRClass.RInteger;

import rClass.RReference;
import rClassInterface.IRReference;
import unfinishedClass.AbstractRClassWithFunctionFactory;

public class RInteger extends AbstractRClassWithFunctionFactory {

	public RInteger(){
		super("Integer");
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
