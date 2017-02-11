package unfinishedClass.basicRClass;

import rClass.AbstractRClassWithFunctionList;
import rClass.RReference;
import rClassInterface.IRReference;

public class RByte extends AbstractRClassWithFunctionList {

	public RByte(){
		super("Byte");
	}
	
	@Override
	public IRReference getNewInstance() {
		IRReference newInstance = new RReference();
		newInstance.mallocSpace(1);
		newInstance.writeObject(new Byte("0"), "Byte");
		return newInstance;
	}

}