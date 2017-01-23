package testSpace;
import functionInterface.*;
import runnerInterface.*;

public class Excutee implements IExcutee
{
	public boolean isReady;
	public IFunction hostFunction;
	public int paragraphToFire;
	public int nextExcuterInRearSlot;

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
			runner.setExcutedableState(true);
			//打开参数遍历状态
			runner.setRetraverseParameterState(false);
			//向运行栈压入与参数链接的
			//BaseCalculator的Excutee
			runner.retraverseParameters(hostFunction.getParameterList());
		}
	}

	@Override
	public void fire()
	{
		hostFunction.run(paragraphToFire);
	}

	@Override
	public void sendRunner(IRunner runner)
	{
		// TODO: Implement this method
	}

	@Override
	public IExcutee getNextExcutee()
	{
		// TODO: Implement this method
		return null;
	}




	
}
