package unfinishedClass.basicRClass;

import rClass.AbstractRClassWithFunctionList;
import rClass.RReference;
import rClassInterface.IRReference;

public class RBoolean extends AbstractRClassWithFunctionList {

	public RBoolean(){
		super("Boolean");
	}
	
	@Override
	public IRReference getNewInstance() {
		IRReference newInstance = new RReference();
		newInstance.mallocSpace(1);
		newInstance.writeObject(new Boolean(false), "Boolean");
		return newInstance;
	}

}