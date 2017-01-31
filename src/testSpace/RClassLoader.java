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
		switch(rClass.charAt(0)){
		case 'B':
			if (rClass.compareTo("Byte") == 0)
				return 1;
			if (rClass.compareTo("Boolean") == 0)
				return 2;
			break;
		case 'C':
			if (rClass.compareTo("Character") == 0)
				return 8;
			break;
		case 'D':
			if (rClass.compareTo("Double") == 0)
				return 7;
			break;
		case 'F':
			if (rClass.compareTo("Float") == 0)
				return 6;
			break;
		case 'L':
			if (rClass.compareTo("Long") == 0)
				return 5;
			break;
		case 'I':
			if (rClass.compareTo("Integer") == 0)
				return 4;
			break;
		case 'S':
			if (rClass.compareTo("Short") == 0)
				return 3;
			if (rClass.compareTo("String") == 0)
				return 9;
			break;
		}
		
		return 0;
	}

}
