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
		return new RReference(10, new Integer(0), 1, "RObject", "RObject");
	}
}
