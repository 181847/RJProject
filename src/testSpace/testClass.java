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
		
		IExcuteeList excuteeList = new ExcuteeList(3);
		
		System.out.println(excuteeList.insertExcutee(excutee));
		System.out.println(excuteeList.insertExcutee(new NormalExcutee("secondExcutee", null, 0)));
		System.out.println(excuteeList.getExcutee("firstExcutee").getName());
		
		IExcuterList excuterList = new ExcuterList(3);
		
		System.out.println(excuterList.insertExcuter(excuter));
		System.out.println(excuterList.insertExcuter(new Excuter("secondExcuter")));
		System.out.println(excuterList.getExcuter("firstExcuter").getExcutee().getName());
		
		IParameterList parameterList = new ParameterList(3);
		
		System.out.println(parameterList.insertParameter(parameter));
		System.out.println(parameterList.insertParameter(new Parameter("secondParameter", true, "Integer")));
		System.out.println(parameterList.getParameter("firstParameter").getReturnval().getName());
		
		IReturnvalList returnvalList = new ReturnvalList(3);
		
		System.out.println(returnvalList.insertReturnval(returnval));
		System.out.println(returnvalList.insertReturnval(new Returnval("secondReturnval", true, "Integer")));
		System.out.println(returnvalList.getReturnval("firstReturnval").getName());
		
		IFunctionHeadSlot headSlot = new FunctionHeadSlot(3,3);
		
		System.out.println(headSlot.insertExcutee(excutee));
		System.out.println(headSlot.insertExcutee(new NormalExcutee("thirdExcutee", null, 0)));
		System.out.println(headSlot.getExcutee("firstExcutee").getName());
		System.out.println(headSlot.insertParameter(parameter));
		System.out.println(headSlot.insertParameter(new Parameter("thirdParameter", true, "Integer")));
		System.out.println(headSlot.getParameter("firstParameter").getReturnval().getName());
	
		IFunctionRearSlot rearSlot = new FunctionRearSlot(3,3);

		System.out.println(rearSlot.insertExcuter(excuter));
		System.out.println(rearSlot.insertExcuter(new Excuter("thirdExcuter")));
		System.out.println(rearSlot.getExcuter("firstExcuter").getExcutee().getName());
		System.out.println(rearSlot.insertReturnval(returnval));
		System.out.println(rearSlot.insertReturnval(new Returnval("thirdReturnval", true, "Integer")));
		System.out.println(rearSlot.getReturnval("firstReturnval").getName());
		
	}

}
