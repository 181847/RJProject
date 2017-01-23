package runnerInterface;
import functionInterface.IParameterList;

public interface IRunner{
	//Runner开始执行运行栈内的Excutee
	public void run();
	//设置在Runner当前的运行循环周期内
	//是否执行当前Excutee的运算功能
	public void setExcutedableState(boolean isExcutedable);
	//设置当前Runner是否处于反向遍历参数的状态
	public void setRetraverseParameterState(boolean isRetraverseParameter);
	//输入一个参数列表，
	//向运行栈压入所有与这些参数链接的
	//BaseCalculatorFunction的Excutee
	void retraverseParameters(IParameterList parameterList)
}
