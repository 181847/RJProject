package testSpace;

import rClassInterface.IRReference;
import unfinishedClass.RReferenceInfo;

public class TestRReferenceInfo extends Test {

	public static void main(String[] args) {
		prepare();
		RReferenceInfo rInfo = new RReferenceInfo("String", "text", "helloWorld");
		IRReference ref = rInfo.getInstance();

		System.out.println(ref.getReferenceRClass() + " " + ref.getName() + " " + ref.readObject());
	}

}
