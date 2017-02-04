package rClassInterface;
import basicInterface.INameable;
import functionInterface.IFunction;
import functionInterface.IFunctionHeadSlot;
import functionInterface.IFunctionRearSlot;

public interface IRClass extends INameable{
	/**
	 * 从RClass中创建一个RReference
	 * 这个RReference的datas数组一定是有实例对象的
	 * 基本数据类型返回的RReference的datas指向一个一维数组，
	 * 第0号单元放置一个Java的基本数据类型的包装类（String类型放置一个String类）
	 * Java包装类型的RClass返回的RReference由程序猿自行设定，
	 * 要求datas指向一个一维数组，
	 * 第0号单元放置一个被包装的Java的包装类的Object型引用
	 * 完全自定义的RClass返回的RReference的datas指向一个固定长度的一维数组，
	 * 这个数组的长度由相应RClass的成员数据数量决定，每个单元放置的都是一个RReference实例，
	 * 这些子RReference的datas是否为null由它的RClass类型决定
	 * （基本数据类型的datas一定指向一个长度为一的一维数组）
	 */
	public IRReference getNewInstance();
	
	/**
	 * 得到指定名字Function的序号
	 */
	public int getFunctionIndexOf(String functionName);
	
	/**
	 * 通过名字获取一个RClass当中指定的Function实例
	 * @param functionName 所需要的Function的名字
	 * @return 返回一个Function实例
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public IFunction Function(String functionName) throws InstantiationException, IllegalAccessException;
	
	/**
	 * 通过数组下标获取一个RClass当中指定的Function实例
	 * 用户必须知道当前要获取的Function在FunctionList当中的位置
	 * @param functionNumber Function在FunctionList当中的位置
	 * @return 一个Function的实例对象
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public IFunction getFunction(int functionNumber) throws InstantiationException, IllegalAccessException;
	
	/**
	 * 专门适用于完全自定义型RClass的方法，通过名字读取相应的function
	 * 用于获取相应的FunctionSlot，然后对CustomFunction的内部进行填充
	 */
	public int fillFunctionGraphOf(String functionName,
									IFunctionHeadSlot headSlot,
									IFunctionRearSlot rearSlot);
	
	/**
	 * 专门适用于完全自定义型RClass的方法，通过数组下标读取相应的function
	 * 用于获取相应的FunctionSlot，然后对CustomFunction的内部进行填充
	 */
	public int fillFunctionGraphOf(int functionNumber,
									IFunctionHeadSlot headSlot,
									IFunctionRearSlot rearSlot);
	
}
