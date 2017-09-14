package rClassInterface;
import basicInterface.INameable;
import basicInterface.IRClassIDHolder;
import functionInterface.IFunction;
import functionInterface.IFunctionHeadSlot;
import functionInterface.IFunctionRearSlot;

public interface IRClass extends INameable,IRClassIDHolder{
	/**
	 * 从RClass中创建一个RReference，
	 * 这个RReference的datas数组一定是有实例对象的，
	 * 基本数据类型返回的RReference的datas指向一个一维数组，
	 * 第0号单元放置一个Java的基本数据类型的包装类（String类型放置一个String类），
	 * Java包装类型的RClass返回的RReference由程序猿自行设定，
	 * 要求datas指向一个一维数组，
	 * 第0号单元放置一个被包装的Java的包装类的Object型引用，
	 * 完全自定义的RClass返回的RReference的datas指向一个固定长度的一维数组，
	 * 这个数组的长度由相应RClass的成员数据数量决定，每个单元放置的都是一个RReference实例，
	 * 这些子RReference的datas是否为null由它的所属的RClass类型决定
	 * @return 返回一类RClass的一个实例对象RReference
	 */
	public IRReference getNewInstance();
	
	/**
	 * 向RClass中插入Function的方法，
	 * RClass的加载成员Function的时候是在分配RClassID之后才进行的，
	 * 在RClass被分配RClassID之后，
	 * 系统统一调用一次这个方法用来加载RClass的成员Function。
	 * @return 插入成功返回1，如果有失败返回0。
	 */
	public int loadFunction();
	
	/**
	 * 获得IRClass的唯一的一个构造Function，
	 * 这个Function用来创建一个RClass的对象实例。
	 * @return 构造Function
	 */
	public IFunction ConstructFunction();
	
	/**
	 * 通过名字获取一个RClass当中指定的Function实例。
	 * @param functionName 所需要的Function的名字。
	 * @return 返回一个Function的新实例。
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public IFunction Function(String functionName);
	
	/**
	 * 通过数组下标获取一个RClass当中指定的Function的新实例，
	 * 用户必须知道当前要获取的Function在FunctionList当中的位置。
	 * @param functionNumber Function在FunctionList当中的位置。
	 * @return 一个Function的新实例。
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public IFunction getFunction(int functionIndex);
	
	/**
	 * 得到指定名字Function的序号。
	 * @param 想要获取的Function的名字。
	 * @return 一个指定名字的function在RClass当中的序号。
	 */
	public int getFunctionIndexOf(String functionName);
	
	/**
	 * 专门适用于完全自定义型RClass的方法，通过名字读取相应的function描述文件，
	 * 通过customFunction的名字，
	 * 来填充自定义customFunction的内容图表。
	 * @param funcitonName
	 * 		customFunction的名字。
	 * @param headSentryFunction
	 * 		customFunction的headSentryFunction。
	 * @param rearSentryFunction
	 * 		customFunction的rearSentryFunction。
	 * @return
	 * 		成功返回1。
	 */
	public int fillFunctionGraphOf(String functionName,
			IFunction headSentryFunction,
			IFunction rearSentryFunction);
	
	/**
	 * 专门适用于完全自定义型RClass的方法，通过序号读取相应的function描述文件，
	 * 通过customFunction的序号，
	 * 来填充自定义customFunction的内容图表。
	 * @param funcitonName
	 * 		customFunction的名字。
	 * @param headSentryFunction
	 * 		customFunction的headSentryFunction。
	 * @param rearSentryFunction
	 * 		customFunction的rearSentryFunction。
	 * @return
	 * 		成功返回1。
	 */
	public int fillFunctionGraphOf(int functionIndex,
			IFunction headSentryFunction,
			IFunction rearSentryFunction);
	
}
