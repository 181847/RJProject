package unfinishedClass.basicRClass.RString;

import rClass.AbstractRClassForJava;
import rClass.RReference;
import rClassInterface.IRClass;
import rClassInterface.IRReference;

public class RString extends AbstractRClassForJava implements IRClass {

	public RString(){
		super("String");
		insertFunctionClass(PrintFunction.class);
	}
	
	@Override
	public IRReference getNewInstance() {
		IRReference newInstance = new RReference();
		newInstance.mallocSpace(1);
		newInstance.writeObject(new String(""), "String");
		return newInstance;
	}

}
