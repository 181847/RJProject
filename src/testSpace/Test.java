package testSpace;

import rClass.RClassLoaderManager;
import rClassInterface.IRClassLoader;

public class Test {
	public static IRClassLoader loader;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static void prepare(){
		RClassLoaderManager.prepareRClassLoader();
		loader = RClassLoaderManager.getRClassLoader();
	}

}
