package unfinishedClass;
import runnerInterface.*;
import functionInterface.*;
import basicTool.*;
import basicInterface.*;

public class Runner extends NameableWithString implements IRunner,INameable{
	public IExcuteeStack excuteeStack;
	public IExcutee excutee;
	public boolean isRetraverseParameter;
	public boolean isExcutedable;
	public boolean isRunable;
	
	public Runner(String name){
		super(name);
		excuteeStack = new StaticExcuteeStack(200);
	}
	
	/**
	 *Runner开始执行运行栈内的Excutee
	 */
	@Override
	public void run()
	{
		int tempLimit = 0;
		while(isRunable && !excuteeStack.isEmpty() && tempLimit++ < 1000){
			excuteeStack.getTopExcutee().welcomeRunner(this);
			if (isExcutedable){
				excuteeStack.popExcutee().fire().sendRunner(this);
			}
		}
	}
	
	/**
	 *输入一个参数列表，
	 *向运行栈压入所有与这些参数链接的
	 *BaseCalculatorFunction的Excutee
	 *先不考虑剔除重复调用的节点
	 */
	@Override
	public void retraverseParameters(IParameterList parameterList)
	{
		IReturnval tempReturnval;
		for (int i = parameterList.getNum() - 1; i > 0; --i){
			tempReturnval = parameterList.getParameter(i).getReturnval();
			if (tempReturnval != null){
				excuteeStack.pushExcutee(tempReturnval.getExcutee());
			}
		}
	}
	
	/**
	 *设置当前Runner是否处于反向遍历参数的状态
	 */
	@Override
	public void setRetraverseParameterState(boolean isRetraverseParameter)
	{
		this.isRetraverseParameter = isRetraverseParameter;
	}
	
	/**
	 *设置在Runner当前的运行循环周期内
	 *是否执行当前Excutee的运算功能
	 */
	@Override
	public void setExcutedableState(boolean isExcutedable)
	{
		this.isExcutedable = isExcutedable;
	}
	
	/**
	 *将Excutee压入Runner的运行
	 */
	@Override
	public int pushExcutee(IExcutee excutee)
	{
		return excuteeStack.pushExcutee(excutee);
	}

	/**
	 *获取顶部的Excutee
	 */
	@Override
	public IExcutee getTopExcutee()
	{
		return excuteeStack.getTopExcutee();
	}

	/**
	 *将顶部的Excutee弹出栈来
	 */
	@Override
	public IExcutee popExcutee()
	{
		return excuteeStack.popExcutee();
	}

	/**
	 *将当前的栈空间清理到指定的标记处
	 */
	@Override
	public int clearTo(IMarkInExcuteeStack mark)
	{
		return excuteeStack.clearTo(mark);
	}

	/**
	 *获取当前栈顶的标记
	 */
	@Override
	public IMarkInExcuteeStack getMark()
	{
		return excuteeStack.getMark();
	}

	/**
	 * 检查当前excuteeStack与指定IMarkInExcuteeStack的实例对象的相容性
	 */
	@Override
	public boolean checkMark(IMarkInExcuteeStack mark) {
		return excuteeStack.checkMark(mark);
	}

	/**
	 * 栈是否为空
	 */
	@Override
	public boolean isEmpty() {
		return excuteeStack.isEmpty();
	}

	@Override
	public void setRunable(boolean isRunable) {
		this.isRunable = isRunable;
	}

}
