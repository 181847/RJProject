package testSpace;

import rClassInterface.IRClass;
import testSpace.testTool.FunctionTester;
import unfinishedClass.RClassLoaderManager;

public class TestRClassLoader {

	public static void main(String[] args) {
		RClassLoaderManager.prepareRClassLoader();
		RClassLoaderManager.getRClassLoader().loadRClassFrom("D:/tempFile/RObject.jar");
		
		int rClassID = RClassLoaderManager.getRClassLoader().getRClassIDOf("RObject");
		IRClass rClass = RClassLoaderManager.getRClassLoader().getRClass("RObject");
		
		System.out.println("RObject的ID: " + rClassID);
		try {
			new FunctionTester(rClass.Function("HelloWorldFunction")).test();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
