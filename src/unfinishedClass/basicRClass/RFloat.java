package unfinishedClass.basicRClass;

import rClass.AbstractRClassForJava;
import rClass.RReference;
import rClassInterface.IRClass;
import rClassInterface.IRReference;
import unfinishedClass.RLogger;

public class RFloat extends AbstractRClassForJava implements IRClass {

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
	
	@Override
	public int loadFunction() {
		RLogger.log(this.getName() + " 加载成员Function。");
		return 0;
	}

}
