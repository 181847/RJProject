package function.component;
import basicTool.NameableWithString;
import functionInterface.*;
import runnerInterface.*;

/**
 * 此类是Excutee的抽象类，
 * 子类需要实现sendRunner的方法。
 */
public abstract class AbstractExcutee extends NameableWithString implements IExcutee{
	public IExcuter nextExcuter;
	public boolean isReady;
	public IFunction hostFunction;
	public int paragraphToFire;
	
	public AbstractExcutee(String excuteeName, int paragraphToFire){
		super(excuteeName);
		this.paragraphToFire = paragraphToFire;
		isReady = false;
	}
	
	/**
	 * 抽象类AbstractExcutee中待重载的方法
	 */
	@Override
	public abstract void sendRunner(IRunner runner);
	
	/**
	 * 发动hostFunction的运算功能。
	 * @return 返回自身的引用，
	 * 之所以返回自身是为了在Runner当中能够连续执行对于Excutee的方法，
	 * 详情参考Runner类当中run()方法的内部定义。
	 */
	@Override
	public IExcutee fire(){
		nextExcuter = hostFunction.invoke(paragraphToFire);
		return this;
	}
	
	/**
	 * 获得Excuter。
	 * @return 一般来说就是本Excutee的接下来所连接的Excuter。
	 */
	@Override
	public IExcuter getExcuter(){
		return nextExcuter;
	}

	/**
	 * 返回指定可命名对象的序号。
	 * @param name 指定的名字
	 * @return 成功返回大于等于0的数字，
	 * 失败返回-1。
	 */
	@Override
	public void welcomeRunner(IRunner runner){
		if (isReady){
			runner.setExcutedableState(true);
			runner.setRetraverseParameterState(false);
		}else{
			runner.setExcutedableState(false);
			runner.setRetraverseParameterState(true);
			runner.retraverseParameters(hostFunction.getParameterList());
			isReady = true;
		}
	}

	/**
	 * 设定Excutee所从属的Function
	 * @param hostFunction Excutee所从属的Function对象
	 */
	@Override
	public void setHostFunction(IFunction hostFunction) {
		this.hostFunction = hostFunction;
		isReady = !hostFunction.needParameters();
	}
}
