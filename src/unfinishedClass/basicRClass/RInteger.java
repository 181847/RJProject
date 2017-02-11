package unfinishedClass.basicRClass;

import rClass.AbstractRClassWithFunctionList;
import rClass.RReference;
import rClassInterface.IRReference;

public class RInteger extends AbstractRClassWithFunctionList {

	public RInteger(){
		super("Integer");
	}
	
	@Override
	public IRReference getNewInstance() {
		IRReference newInstance = new RReference();
		newInstance.mallocSpace(1);
		newInstance.writeObject(new Integer(0), "Integer");
		return newInstance;
	}

}
