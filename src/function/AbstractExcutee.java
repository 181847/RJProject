package function;
import basicTool.NameableWithString;
import functionInterface.*;
import runnerInterface.*;

public abstract class AbstractExcutee extends NameableWithString implements IExcutee{
	public boolean isReady;
	public IFunction hostFunction;
	public int paragraphToFire;
	public IExcuter nextExcuter;
	
	public AbstractExcutee(String excuteeName, int paragraphToFire){
		super(excuteeName);
		this.paragraphToFire = paragraphToFire;
		isReady = false;
		nextExcuter = null;
	}
	
	public abstract void sendRunner(IRunner runner);

	
	
	//设定Runner的相关运行权限
	//以及遍历参数来源的基本运算节点
	@Override
	public void welcomeRunner(IRunner runner)
	{
		if (isReady){
			//参数准备完成可运行
			runner.setExcutedableState(true);
			//关闭遍历参数状态
			runner.setRetraverseParameterState(false);
		}else{
			//参数未遍历，不可执行
			runner.setExcutedableState(false);
			//打开参数遍历状态
			runner.setRetraverseParameterState(true);
			//向运行栈压入与参数链接的
			//BaseCalculator的Excutee
			runner.retraverseParameters(hostFunction.getParameterList());
			isReady = true;
		}
	}

	
	//发动hostFunction的函数功能
	@Override
	public IExcutee fire()
	{
		nextExcuter = hostFunction.invoke(paragraphToFire);
		return this;
	}

	@Override
	public void setHostFunction(IFunction hostFunction) {
		this.hostFunction = hostFunction;
		isReady = !hostFunction.needParameters();
	}

	@Override
	public IExcuter getExcuter()
	{
		return nextExcuter;
	}


	
	
}
