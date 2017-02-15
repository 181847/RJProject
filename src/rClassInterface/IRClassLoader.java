package rClassInterface;
/**
 * 只需要有一个RClassLoader，因此将这个类的所有方法设为Static
 * 直接通过类名访问，不需要在另外划分空间设置引用保存一个RClassLoader
 */
public interface IRClassLoader{
	/**
	 * 从指定的文件读取rClass，要求文件的名字就是RClass的名字。
	 * @param rClassPath 文件的绝对路径。
	 * @return 为RClass注册的RClassID。
	 */
	public int loadRClassFrom(String rClassPath);
	
	/**
	 * 检查两个RClass的相互关系是怎样的，
	 * 现阶段只考虑基本数据类型相等的情况。
	 * @param rClassA RClass类型A，可以用来检查是否为父类。
	 * @param rClassB RClass类型B，可以用来检查是否为子类。
	 * @return 现阶段只考虑相等情况，相等返回1；
	 * 不等返回Integer的最大值。
	 */
	public int checkRClassMatchType(String rClassA, String rClassB);
	
	/**
	 * 通过RClassID的情况检查两个RClass类型的关系，
	 * 目前只有在都是正数，且相等的情况下返回1，其他情况下返回0。
	 * @param rClassAID RClassID
	 * @param rClassBID 另一个RClassID
	 * @return 检查结果，相同且都为Java包装类，返回1；
	 * 否则返回0。
	 */
	public int checkRClassMatchType(int rClassAID, int rClassBID);
	
	/**
	 * 获得一个RClass类型实例。
	 * @param rClass 要获取的RClas的名字。
	 * @return RClass的类型实例引用。
	 * 
	 */
	public IRClass getRClass(String rClass);
	
	/**
	 * 获得一个RClass类型实例。
	 * @param rClassID RClassID。
	 * @return RClass的类型实例引用。
	 */
	public IRClass getRCllass(int rClassID);
	
	/**
	 * 查看这个RClass的粗略类型标识，1至9代表基本数据类型。
	 * @param rClass rClass的名字。
	 * @return 粗略类型标识，
	 * 10以及10以上的是Java包装类；
	 * 负数代表完全自定义RClass；
	 * 0代表空类型；
	 * 当前只考虑9种基本数据类型。
	 */
	public int getRClassIDOf(String rClass);
	
	/**
	 * 将一个RClass实例对象装载进入RClassLoader，
	 * 并建立RClass的名字到RClassID的联系。
	 * @param loadingRClass 要装载的RClass类型对象。
	 * @return 为RClass注册的RClassID。
	 */
	public int loadJarRClass(IRClass rClassToLoad);
}