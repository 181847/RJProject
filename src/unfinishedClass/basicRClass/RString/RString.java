package unfinishedClass.basicRClass.RString;

import rClass.AbstractRClassForJava;
import rClass.RReference;
import rClassInterface.IRClass;
import rClassInterface.IRReference;
import unfinishedClass.RLogger;
import unfinishedClass.basicRClass.RUtils.ExceptionMakerFunction;
import unfinishedClass.basicRClass.RUtils.HelloWorldFunction;

public class RString extends AbstractRClassForJava implements IRClass {

	public RString(){
		super("String");
	}
	
	@Override
	public IRReference getNewInstance() {
		IRReference newInstance = new RReference();
		newInstance.mallocSpace(1);
		newInstance.writeObject(new String(""), "String");
		return newInstance;
	}
	
	@Override
	public int loadFunction() {
		RLogger.log(this.getName() + " 加载成员Function。");
		insertFunctionClass(PrintFunction.class);
		return 1;
	}

}
