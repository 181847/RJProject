package testSpace;
import rClassInterface.*;
import functionInterface.*;
import basicInterface.*;

public class testClass {

	public static void main(String[] args) {
		IRReference ref;
		ref = new Parameter("firstInt", true, "Integer");
		
		Object[] a = ref.getObjects();
		
		a[0] = new Integer(78);
		
		System.out.println(ref.getName() +" is type of " + ref.getReferenceClass() + " whose value is " + ((Integer)(ref.getObjects()[0])).toString() );
		
		IExcutee excutee;
		IExcuter excuter;
		
		excutee = new NormalExcutee("firstExcutee", null, 0);
		excuter = new Excuter("firstExcuter");
		
		excuter.linkExcutee(excutee);
		
		System.out.println(excuter.getName() +" LINK " +excuter.getExcutee().getName());
		
		IParameter parameter;
		IReturnval returnval;
		
		parameter = new Parameter("firstParameter", true, "Integer");
		returnval = new Returnval("firstReturnval", true, "Integer");
		
		parameter.linkReturnval(returnval);
		
		System.out.println("Parameter: " + parameter.getName() + " LINK Returnval: " +parameter.getReturnval().getName());
		
		ExcuteeList excuteeList = new ExcuteeList(3);
		
		System.out.println(excuteeList.addExcutee(excutee));
		System.out.println(excuteeList.addExcutee(new NormalExcutee("secondExcutee", null, 0)));
		System.out.println(excuteeList.getExcutee("firstExcutee").getName());
		
		
		
		
		
	}

}
