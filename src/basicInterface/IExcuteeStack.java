package basicInterface;
import functionInterface.IExcutee;


//这里面要用到的IMarkInExcuteeStack的实现类
//必须和IExcuteeStack的实现类相一致
//不是实现了接口就可以任意改变实际对象的类型
public interface IExcuteeStack{
	/**
	 *压入Excutee
	 */
	public int pushExcutee(IExcutee excutee);
	
	/**
	 *获取顶部的Excutee
	 */
	public IExcutee getTopExcutee();
	
	/**
	 *将顶部的Excutee弹出栈来
	 */
	public IExcutee popExcutee();
	
	/**
	 *将当前的栈空间清理到指定的标记处
	 */
	public int clearTo(IMarkInExcuteeStack mark);
	
	/**
	 *获取当前栈顶的标记
	 */
	public IMarkInExcuteeStack getMark();
}
