package testSpace;
import functionInterface.*;

public class testClass {

	public static void main(String[] args) {
		IFunction function = new HelloWorldFunction();
		
		function.Parameter("n").writeObject(new Integer(45), "Integer");
		function.Parameter("ch").writeObject(new Character('@'), "Character");
		
		function.Excutee("fire").fire();
		function.Excutee("fireSlot2").fire();
		function.Excutee("tryPara&Retu").fire();
		
		System.out.println((Integer)function.Returnval("return").readObject());
	}

}
