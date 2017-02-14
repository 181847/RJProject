package testSpace.testRClass;
import rClass.AbstractRClassForJava;
import rClass.RReference;
import rClassInterface.IRClass;
import rClassInterface.IRReference;

public class RObject extends AbstractRClassForJava implements IRClass {
	
	public RObject(){
		super("RObject");
		insertFunctionClass(HelloWorldFunction.class);
	}
	
	@Override
	public IRReference getNewInstance() {
		IRReference newInstance = new RReference();
		newInstance.mallocSpace(1);
		newInstance.writeObject(new Object(), getName());
		return newInstance;
	}
}