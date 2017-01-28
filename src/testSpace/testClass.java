package testSpace;
import functionInterface.*;

public class testClass {

	public static void main(String[] args) {
		IFunction function = new HelloWorldFunction();
		function.Excutee("fire").fire();
		function.Excutee("fireSlot2").fire();
	}

}
