package testSpace;
import rClassInterface.*;
import functionInterface.*;

public class testClass {

	public static void main(String[] args) {
		IFunction function = new HelloWorldFunction();
		IExcutee excuteeForFire = function.getExcutee("fire");
		excuteeForFire.fire();
		excuteeForFire = function.getExcutee("fireSlot2");
		excuteeForFire.fire();
	}

}
