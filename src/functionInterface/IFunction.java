package functionInterface;
import basicInterface.IHoldRClassID;
import basicInterface.INameable;

public interface IFunction extends IFunctionHeadSlot,IFunctionRearSlot,INameable,IHoldRClassID{
	
	/**
	 * 开始function的参数处理，以及发动run方法。
	 * @param paragraph 用于区分不同运算功能的数字。
	 * @return 下一步要执行的Excuter。
	 */
	public IExcuter invoke(int paragraph);
	
	/**
	 * 运行，执行Function的运算功能。
	 * @param paragraph 用于区分不同运算功能的数字。
	 * @return 下一步要执行的Excuter。
	 */
	public IExcuter run(int paragraph);
	
	/**
	 * 填充Function内部的其他Function，
	 * 对于CustomFunction才使用到的功能，
	 * 在Java级别包装的RClass的成员Function不会用到这个方法。
	 */
	public void fillContentGraph();
	
	/**
	 * 清空Function当中的临时数据、临时Function的引用
	 * 对于CustomFunction才使用到的功能，
	 * 在Java级别包装的RClass的成员Function不会用到这个方法。
	 */
	public void clearGraph();
	
	/**
	 * 是否需要参数由程序员手动重载这个方法
	 * 表明这个Function是否需要参数
	 * 例如你将要创建的Function需要参数
	 * 只需要重载这个方法为
	 * public boolean needParameters(){
	 *     return true;
	 * }
	 * @return 是否需要参数
	 */
	public boolean needParameters();
	
	
	/**
	 * 得到Function实例对象的节点前方的插口引用。
	 * @return 一个FunctionHeadSlot，
	 * 这个插口当中包含了本Function实例的Excutee和Parameter。
	 */
	public IFunctionHeadSlot getHeadSlot();
	
	/**
	 * 得到Function实例对象的节点后方的插口引用。
	 * @return 一个FunctionRearSlot。
	 * 这个插口当中包含了本Function实例的Excuter和Returnval。
	 */
	public IFunctionRearSlot getRearSlot();
}
