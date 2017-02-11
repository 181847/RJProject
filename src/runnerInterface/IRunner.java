package runnerInterface;
import functionInterface.IExcuteeStack;
import functionInterface.IParameterList;

public interface IRunner extends IExcuteeStack{
	/**
	 * 设置Runner是否能够开始run循环。
	 * @param isRunable RunnerRunner是否能够开始run循环。
	 */
	public void setRunable(boolean isRunable);
	
	/**
	 * run循环开始，
	 * Runner开始不断弹栈运行Excutee，
	 * 以及压栈Excutee，
	 * 每一个循环涉及一个Excutee
	 */
	public void run();
	
	/**
	 * 输入一个参数列表，
	 * 向运行栈压入所有与这些参数链接的Returnval的Excutee。
	 * @param parameterList 参数列表。
	 */
	public void retraverseParameters(IParameterList parameterList);
	
	/**
	 * 设置当前Runner是否处于反向遍历参数的状态。
	 * @param isRetraverseParameter 是否开始参数遍历的布尔值。
	 */
	public void setRetraverseParameterState(boolean isRetraverseParameter);
	
	/**
	 * 设置在Runner当前的run循环周期内，
	 * 是否执行当前循环周期内的Excutee的运算功能。
	 * @param isExcutedable 是否执行Excutee的运算功能。
	 */
	public void setExcutedableState(boolean isExcutedable);
}
