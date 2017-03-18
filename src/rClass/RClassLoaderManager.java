package rClass;
import rClassInterface.IRClassLoader;
import unfinishedClass.basicRClass.*;
import unfinishedClass.basicRClass.RInteger.RInteger;
import unfinishedClass.basicRClass.RString.RString;
import unfinishedClass.basicRClass.RUtils.RUtils;
import unfinishedClass.customRClass.RClassLoader;

/**
 * 全局的RClassLoader管理者，
 * 通过这个RClassManager，
 * 使得任何地方都能通过这个类的静态方法来获得整个程序的RClassLoader。
 */
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
		//rClassLoader.loadJarRClass(new RException());
		rClassLoader.loadJarRClass(new RUtils());
	}
}
