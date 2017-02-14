package testSpace;

import rClass.RClassLoaderManager;
import rClassInterface.IRClass;
import rClassInterface.IRClassLoader;

public class TestOneRClass {

	public static void main(String[] args) {
		RClassLoaderManager.prepareRClassLoader();
		IRClassLoader loader = RClassLoaderManager.getRClassLoader();
		IRClass rclass = loader.getRClass("Exception");
		System.out.println(rclass.toString() + rclass.getRClassID());
	}

}
