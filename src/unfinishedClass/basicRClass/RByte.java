package unfinishedClass.basicRClass;

import rClass.AbstractRClassForJava;
import rClass.RReference;
import rClassInterface.IRClass;
import rClassInterface.IRReference;
import unfinishedClass.RLogger;

public class RByte extends AbstractRClassForJava implements IRClass {

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

	@Override
	public int loadFunction() {
		RLogger.log("RByte 加载成员Function。");
		return 0;
	}

}