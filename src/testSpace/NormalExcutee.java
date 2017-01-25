package testSpace;
import functionInterface.*;
import runnerInterface.*;

public class NormalExcutee extends NameableWithString implements IExcutee{
	public boolean isReady;
	public IFunction hostFunction;
	public int paragraphToFire;
	public IExcuter nextExcuter;
	
	public NormalExcutee(String excuteeName, int paragraphToFire){
		setName(excuteeName);
		this.paragraphToFire = paragraphToFire;
		isReady = false;
		nextExcuter = null;
	}

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
		}
	}

	@Override
	public void fire()
	{
		nextExcuter = hostFunction.run(paragraphToFire);
	}

	@Override
	public void sendRunner(IRunner runner)
	{
		if (nextExcuter != null){
			runner.pushExcutee(nextExcuter.getExcutee());
			nextExcuter = null;
		}
	}

	@Override
	public void setHostFunction(IFunction hostFunction) {
		this.hostFunction = hostFunction;
	}
	
	
}
