package unfinishedClass.basicRClass;

import rClass.AbstractRClassForJava;
import rClass.RReference;
import rClassInterface.IRClass;
import rClassInterface.IRReference;

public class RBoolean extends AbstractRClassForJava implements IRClass {

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