package testSpace.originalTest_without_jUnit.testRClass;
import basicTool.RLogger;
import rClass.AbstractRClassForJava;
import rClass.RReference;
import rClassInterface.IRClass;
import rClassInterface.IRReference;
import unfinishedClass.basicRClass.RInteger.BasicIntegerAddFunction;
import unfinishedClass.basicRClass.RInteger.IntegerAddFunction;
import unfinishedClass.basicRClass.RInteger.RIntegerConstructFunction;
import unfinishedClass.basicRClass.RUtils.HelloWorldFunction;

public class RObject extends AbstractRClassForJava implements IRClass {
	
	public RObject(){
		super("RObject");
	}
	
	@Override
	public IRReference getNewInstance() {
		IRReference newInstance = new RReference();
		newInstance.mallocSpace(1);
		newInstance.writeObject(new Object(), getName());
		return newInstance;
	}
	
	@Override
	public int loadFunction() {
		RLogger.log(this.getName() + " 加载成员Function。");
		insertFunctionClass(HelloWorldFunction.class);
		return 1;
	}
}