package unfinishedClass;

import rClass.RReference;
import rClassInterface.IRClass;
import rClassInterface.IRReference;

public class RObject extends AbstractRClassWithFunctionList implements IRClass {
	
	public RObject(){
		super("ROjbect");
		insertFunction(new HelloWorldFunction());
	}
	
	@Override
	public IRReference getNewInstance() {
		IRReference newInstance = new RReference();
		newInstance.mallocSpace(1);
		newInstance.writeObject(new Integer(0), "RObject");
		return newInstance;
	}
}
