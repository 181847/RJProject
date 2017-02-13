package rClass;
import rClassInterface.IRClassLoader;
import unfinishedClass.basicRClass.*;
import unfinishedClass.basicRClass.RInteger.RInteger;

public class RClassLoaderManager {
	public static IRClassLoader rClassLoader;
	public static int positiveSpace = 100;
	public static int negativeSpace = 100;
	public static float loadFactor = 0.75f;

	public static IRClassLoader getRClassLoader(){
		return rClassLoader;
	}
	
	public static void prepareRClassLoader(){
		rClassLoader = new RClassLoader(positiveSpace, negativeSpace, loadFactor);
		rClassLoader.loadJarRClass(new RByte());
		rClassLoader.loadJarRClass(new RBoolean());
		rClassLoader.loadJarRClass(new RShort());
		rClassLoader.loadJarRClass(new RInteger());
		rClassLoader.loadJarRClass(new RLong());
		rClassLoader.loadJarRClass(new RFloat());
		rClassLoader.loadJarRClass(new RDouble());
		rClassLoader.loadJarRClass(new RCharacter());
		rClassLoader.loadJarRClass(new RString());
	}
}
