package functionInterface;
import basicInterface.INameable;
import runnerInterface.IRunner;

public interface IExcutee extends INameable{
	/**
	 * 欢迎Runner的到来，检查参数是否遍历。
	 * @param runner 当前要执行这个Excutee的Runner。
	 */
	public void welcomeRunner(IRunner runner);
	
	/**
	 * 发动hostFunction的运算功能。
	 * @return 返回自身的引用，
	 * 之所以返回自身是为了在Runner当中能够连续执行对于Excutee的方法，
	 * 详情参考Runner类当中run()方法的内部定义。
	 */
	public IExcutee fire();
	
	/**
	 * 为Runner指明下一个要运行的地方
	 * @param runner 将要送出的Runner
	 */
	public void sendRunner(IRunner runner);
	
	/**
	 * 设定Excutee所从属的Function
	 * @param hostFunction Excutee所从属的Function对象
	 */
	public void setHostFunction(IFunction hostFunction);
	
	/**
	 * 获得Excuter。
	 * @return 一般来说就是本Excutee的接下来所连接的Excuter。
	 */
	public IExcuter getExcuter();
}
