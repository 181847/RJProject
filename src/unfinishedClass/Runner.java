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
	
	/**
	 * Runner的构造方法。
	 * @param name 设定runner的名字。
	 */
	public Runner(String name){
		super(name);
		excuteeStack = new StaticExcuteeStack(200);
	}
	
	/**
	 * 设置Runner是否能够开始run循环。
	 * @param isRunable RunnerRunner是否能够开始run循环。
	 */
	@Override
	public void setRunable(boolean isRunable) {
		this.isRunable = isRunable;
	}
	
	/**
	 * Runner开始循环执行运行栈内的Excutee。
	 */
	@Override
	public void run()
	{
		int runLimit = 0;
		while(isRunable && !excuteeStack.isEmpty() && runLimit++ < 1000){
			excuteeStack.getTopExcutee().welcomeRunner(this);
			
			if (isExcutedable){
				excuteeStack.popExcutee().fire().sendRunner(this);
			}
		}
	}
	
	/**
	 * 输入一个参数列表，
	 * 向运行栈压入所有与这些参数链接的Returnval的Excutee。
	 * @param parameterList 参数列表。
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
	 * 设置当前Runner是否处于反向遍历参数的状态。
	 * @param isRetraverseParameter 是否开始参数遍历的布尔值
	 */
	@Override
	public void setRetraverseParameterState(boolean isRetraverseParameter)
	{
		this.isRetraverseParameter = isRetraverseParameter;
	}
	
	/**
	 * 设置在Runner当前的run循环周期内，
	 * 是否执行当前循环周期内的Excutee的运算功能。
	 * @param isExcutedable 是否执行Excutee的运算功能。
	 */
	@Override
	public void setExcutedableState(boolean isExcutedable)
	{
		this.isExcutedable = isExcutedable;
	}
	
	/**
	 * 压入Excutee。
	 * @param 要压入栈内的元素
	 * @return 如果栈满就直接返回2；
	 * 如果传入的元素为null返回0；
	 * 成功返回1。
	 */
	@Override
	public int pushExcutee(IExcutee excutee)
	{
		return excuteeStack.pushExcutee(excutee);
	}

	/**
	 * 获取顶部的Excutee，但不将其从栈内弹出来。
	 * @return 栈顶的Excutee。
	 */
	@Override
	public IExcutee getTopExcutee()
	{
		return excuteeStack.getTopExcutee();
	}

	/**
	 * 将顶部的Excutee弹出栈来。
	 * @return 栈顶的Excutee。
	 */
	@Override
	public IExcutee popExcutee()
	{
		return excuteeStack.popExcutee();
	}

	/**
	 * 将当前的栈空间清理到指定的标记处。
	 * @param mark 与ExcuteeStack相匹配的栈元素位置标记
	 * @return 清除位置超过栈顶（大于等于top）返回2；
	 * 正常清除返回1；
	 * mark为null返回0；
	 * mark类型不合法返回-1；
	 * 清除位置小于栈底返回-2。
	 */
	@Override
	public int clearTo(IMarkInExcuteeStack mark)
	{
		return excuteeStack.clearTo(mark);
	}

	/**
	 * 获取当前栈顶的标记，
	 * @return 当前栈顶元素的位置标记，可用于之后栈高度增加后，
	 * 清除栈内元素到指定标记处。
	 */
	@Override
	public IMarkInExcuteeStack getMark()
	{
		return excuteeStack.getMark();
	}

	/**
	 * 检查当前excuteeStack与指定IMarkInExcuteeStack的实例对象的相容性，
	 * @param mark 一个ExcuteeStack的标记对象，
	 * @return 当前mark是否能够用于当前ExcuteeStack。
	 */
	@Override
	public boolean checkMark(IMarkInExcuteeStack mark) {
		return excuteeStack.checkMark(mark);
	}

	/**
	 * @return 栈是否为空。
	 */
	@Override
	public boolean isEmpty() {
		return excuteeStack.isEmpty();
	}
}
