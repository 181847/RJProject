package unfinishedClass.basicRClass;

import rClass.AbstractRClassWithFunctionList;
import rClass.RReference;
import rClassInterface.IRReference;

public class RShort extends AbstractRClassWithFunctionList {

	public RShort(){
		super("Short");
	}
	
	@Override
	public IRReference getNewInstance() {
		IRReference newInstance = new RReference();
		newInstance.mallocSpace(1);
		newInstance.writeObject(new Short((short)0), "Short");
		return newInstance;
	}

}
