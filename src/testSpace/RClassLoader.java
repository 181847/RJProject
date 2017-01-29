package testSpace;
import rClassInterface.IRClass;

public class RClassLoader{

	public static int loadRClass(String rClassPath) {
		System.out.println("loadRClass() called: did nothing with " + "rClassPath");
		return 0;
	}

	//现阶段只考虑基本数据类型相等的情况
	public static int checkRClassMatchType(String rClassA, String rClassB) {
		if (rClassA.compareTo(rClassB) == 0)
			return 1;
		
		return Integer.MAX_VALUE;
	}

	//获得RClass的一个引用
	public static IRClass getRClass(String rClass) {
		return null;
	}
	
	//查看这个RClass的类型，0代表基本数据类型
	public static int getRoughTypeOf(String rClass){
		if (rClass.compareTo("Byte")==0 || rClass.compareTo("Short")==0 || rClass.compareTo("Integer")==0 || rClass.compareTo("Long")==0 || rClass.compareTo("Float")==0 || rClass.compareTo("Double")==0 || rClass.compareTo("Character")==0 || rClass.compareTo("Boolean")==0)
			return 1;
		else
			return -1;
	}

}
