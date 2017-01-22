package rClassInterface;


public interface IRClassLoader{
	//从指定的文件路径加载RClass，用一个int数值返回加载的结果
	int loadRClass(String rClassPath);
	//检查两个RClass的相对关系
	//case 1:同一类型，基本数据类型
	//case 2:同一类型，普通类型，包括原子非基本数据类型
	//case 3:不同类型，source为子类，如果到此，必定不是Java包装类，也不是基本数据类型
	int checkRClassMatchType(String rClassA, String rClassB);
	//通过名字得到一个RClass
	IRClass getRClass(String rClassName);
}
