package unfinishedClass.basicRClass;

import rClass.AbstractRClassWithFunctionList;
import rClass.RReference;
import rClassInterface.IRReference;

public class RFloat extends AbstractRClassWithFunctionList {

	public RFloat(){
		super("Float");
	}
	
	@Override
	public IRReference getNewInstance() {
		IRReference newInstance = new RReference();
		newInstance.mallocSpace(1);
		newInstance.writeObject(new Float(0), "Float");
		return newInstance;
	}

}
