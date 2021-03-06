package unfinishedClass.basicRClass;

import basicTool.RLogger;
import rClass.AbstractRClassForJava;
import rClass.RReference;
import rClassInterface.IRClass;
import rClassInterface.IRReference;

public class RLong extends AbstractRClassForJava implements IRClass {

	public RLong(){
		super("Long");
	}
	
	@Override
	public IRReference getNewInstance() {
		IRReference newInstance = new RReference();
		newInstance.mallocSpace(1);
		newInstance.writeObject(new Long(0), "Long");
		return newInstance;
	}
	
	@Override
	public int loadFunction() {
		RLogger.log(this.getName() + " 加载成员Function。");
		return 0;
	}

}
