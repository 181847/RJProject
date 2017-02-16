package testSpace;

import rClass.RClassLoaderManager;
import rClassInterface.IRClassLoader;
import runner.Runner;
import runnerInterface.IRunner;

public class Test {
	public static IRClassLoader loader;
	public static IRunner runner;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static void prepare(){
		RClassLoaderManager.prepareRClassLoader();
		loader = RClassLoaderManager.getRClassLoader();
		
		runner = new Runner("LiuXiang");
		runner.setRunable(true);
	}

}
