package testSpace;
import rClassInterface.*;
import functionInterface.*;

public class testClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IRReference ref;
		ref = new RReference("firstInt", true, "Integer");
		
		Object[] a = ref.getObjects();
		
		a[0] = new Integer(78);
		
		System.out.println(ref.getName() +" is type of " + ref.getReferenceClass() + " whose value is " + ((Integer)(ref.getObjects()[0])).toString() );
		
		IExcutee excutee;
		IExcuter excuter;
		
		excutee = new NormalExcutee("firstExcutee", null, 0);
		excuter = new Excuter("firstExcuter");
		
		excuter.linkExcutee(excutee);
		
		System.out.println(excuter.getName() +" LINK " +excuter.getExcutee().getName());
	}

}
