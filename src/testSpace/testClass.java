package testSpace;
import rClassInterface.*;
import functionInterface.*;
import basicInterface.*;

public class testClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
		
		INamedItemList excuterList = new NamedItemList(3);
		
		System.out.println(excuterList.insertItem(excuter));
		System.out.println(excuterList.insertItem(new Excuter("secondExcuter")));
		System.out.println(((IExcuter)(excuterList.getItem("firstExcuter"))).getExcutee().getName());
		
		
		
		
		
	}

}
