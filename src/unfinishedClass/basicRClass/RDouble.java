package unfinishedClass.basicRClass;

import rClass.AbstractRClassForJava;
import rClass.RReference;
import rClassInterface.IRClass;
import rClassInterface.IRReference;

public class RDouble extends AbstractRClassForJava implements IRClass {

	public RDouble(){
		super("Double");
	}
	
	@Override
	public IRReference getNewInstance() {
		IRReference newInstance = new RReference();
		newInstance.mallocSpace(1);
		newInstance.writeObject(new Double(0), "Double");
		return newInstance;
	}

}
