package unfinishedClass.basicRClass;

import rClass.AbstractRClassForJava;
import rClass.RReference;
import rClassInterface.IRClass;
import rClassInterface.IRReference;
import unfinishedClass.RLogger;

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
	
	@Override
	public int loadFunction() {
		RLogger.log(this.getName() + " 加载成员Function。");
		return 0;
	}

}