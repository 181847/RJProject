package unfinishedClass.basicRClass.RInteger;

import rClass.RReference;
import rClassInterface.IRReference;
import unfinishedClass.AbstractRClassWithFunctionFactory;

public class RInteger extends AbstractRClassWithFunctionFactory {

	public RInteger(){
		super("Integer");
		insertFunctionClass(IntegerAddFunction.class);
	}
	
	@Override
	public IRReference getNewInstance() {
		IRReference newInstance = new RReference();
		newInstance.mallocSpace(1);
		newInstance.writeObject(new Integer(0), "Integer");
		return newInstance;
	}

}
