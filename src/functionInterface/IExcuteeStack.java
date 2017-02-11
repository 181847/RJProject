package functionInterface;

/**
 * 这里面要用到的IMarkInExcuteeStack的实现类，
 * 必须和IExcuteeStack的实现类相一致，
 * 不是实现了接口就可以任意改变实际对象的类型。
 */
public interface IExcuteeStack{
	
	/**
	 * 栈是否为空。
	 * @return 空则返回false；
	 * 不空返回true。
	 */
	public boolean isEmpty();
	
	/**
	 * 压入Excutee。
	 * @param 要压入栈内的元素
	 * @return 如果栈满就直接返回2；
	 * 如果传入的元素为null返回0；
	 * 成功返回1。
	 */
	public int pushExcutee(IExcutee excutee);
	
	/**
	 * 获取顶部的Excutee，但不将其从栈内弹出来。
	 * @return 栈顶的Excutee。
	 */
	public IExcutee getTopExcutee();
	
	/**
	 * 将顶部的Excutee弹出栈来。
	 * @return 栈顶的Excutee。
	 */
	public IExcutee popExcutee();
	
	/**
	 * 将当前的栈空间清理到指定的标记处。
	 * @param mark 与ExcuteeStack相匹配的栈元素位置标记
	 * @return 清除位置超过栈顶（大于等于top）返回2；
	 * 正常清除返回1；
	 * mark为null返回0；
	 * mark类型不合法返回-1；
	 * 清除位置小于栈底返回-2。
	 */
	public int clearTo(IMarkInExcuteeStack mark);
	
	/**
	 * 获取当前栈顶的标记，
	 * @return 当前栈顶元素的位置标记，可用于之后栈高度增加后，
	 * 清除栈内元素到指定标记处。
	 */
	public IMarkInExcuteeStack getMark();
	
	/**
	 * 检查当前excuteeStack与指定IMarkInExcuteeStack的实例对象的相容性，
	 * @param mark 一个ExcuteeStack的标记对象，
	 * @return 当前mark是否能够用于当前ExcuteeStack。
	 */
	public boolean checkMark(IMarkInExcuteeStack mark);
}
