package testSpace;
import rClassInterface.*;

public class testClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IRReference ref;
		ref = new RReference(true, "Integer");
		
		ref.setName("firstInt");
		
		Object[] a = ref.getObjects();
		
		a[0] = new Integer(78);
		
		System.out.println(((Integer)(ref.getObjects()[0])).toString() );
	}

}
