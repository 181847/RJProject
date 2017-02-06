package rClassInterface;
/**
 * 废弃接口，废弃接口，废弃接口，废弃接口，废弃接口，
 * 本接口暂时被废弃，因为在程序中
 * 只需要有一个RClassLoader，因此将这个类的所有方法设为Static
 * 直接通过类名访问，不需要在另外划分空间设置引用保存一个RClassLoader
 */
public interface IRClassLoader{
	/**
	 * 从指定的文件路径加载RClass，用一个int数值返回加载的结果。
	 * @param rClassPath 文件路径。
	 * @return 1 代表加载成功，0表示加载失败。
	 */
	int loadRClass(String rClassPath);
	int checkRClassMatchType(String rClassA, String rClassB);
	/**
	 * 通过名字得到一个RClass
	 * @param rClassName 所需要的RClass名字
	 * @return RClass的实例对象
	 */
	IRClass getRClass(String rClassName);
}
/**
 * 废弃接口，废弃接口，废弃接口，废弃接口，废弃接口，
 */