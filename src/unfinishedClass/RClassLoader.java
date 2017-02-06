package unfinishedClass;
import rClassInterface.IRClass;

public class RClassLoader{

	public static int loadRClass(String rClassPath) {
		System.out.println("loadRClass() called: did nothing with " + "rClassPath");
		return 0;
	}

	/**
	 * 检查两个RClass的相互关系是怎样的，
	 * 现阶段只考虑基本数据类型相等的情况。
	 * @param rClassA RClass类型A，可以用来检查是否为父类。
	 * @param rClassB RClass类型B，可以用来检查是否为子类。
	 * @return 现阶段只考虑相等情况，相等返回1；
	 * 不等返回Integer的最大值。
	 */
	public static int checkRClassMatchType(String rClassA, String rClassB) {
		if (rClassA.compareTo(rClassB) == 0){
			return 1;
		}
		return Integer.MAX_VALUE;
	}

	/**
	 * 获得一个RClass类型实例。
	 * @param rClass 要获取的RClas的名字。
	 * @return RClass的类型实例引用。
	 * 
	 */
	public static IRClass getRClass(String rClass) {
		return null;
	}
	
	/**
	 * 查看这个RClass的粗略类型标识，1至9代表基本数据类型。
	 * @param rClass rClass的名字。
	 * @return 粗略类型标识，
	 * 10以及10以上的是Java包装类；
	 * 负数代表完全自定义RClass；
	 * 0代表空类型；
	 * 当前只考虑9种基本数据类型。
	 */
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
