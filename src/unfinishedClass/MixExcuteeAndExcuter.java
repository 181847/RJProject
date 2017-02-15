package unfinishedClass;

import basicTool.NameableWithString;
import functionInterface.IExcutee;
import functionInterface.IExcuter;
import functionInterface.IFunction;
import runnerInterface.IRunner;

public class MixExcuteeAndExcuter extends NameableWithString implements IExcutee, IExcuter {
	public IExcutee excutee;
	public boolean isReady;
	public IFunction hostFunction;
	public int paragraphToFire;
	
	public MixExcuteeAndExcuter(String excuteeName, int paragraphToFire) {
		super(excuteeName);
	}

	/**
	 * 返回连接的Excutee
	 * @return Excuter所连接的Excutee
	 */
	@Override
	public IExcutee getExcutee() {
		return excutee;
	}

	/**
	 * 链接Excutee
	 * @param excutee 链接的对象Excutee
	 * @return 插入元素为null返回0，成功返回1。
	 */
	@Override
	public int linkExcutee(IExcutee excutee) {
		if (excutee == null){
			return 0;
		}
		this.excutee = excutee;
		return 1;
	}
	
	/**
	 * 由于这个类属于Excutee和Excuter的混合类型，
	 * 成员变量有一个Excutee（即下一个要发动的对象），
	 * 这里无需获取IExcuter，直接将成员Excutee压入runner的运行栈内。
	 * @param runner 操作对象Runner
	 */
	@Override
	public void sendRunner(IRunner runner){
		runner.pushExcutee(excutee);
		isReady = !hostFunction.needParameters();
	}

	/**
	 * fire()方法发动hostFunction的invoke()方法，
	 * 这里不需要获取下一个Excuter，因为这个Excutee本身就是一个Excuter，
	 * 相当于这个Excutee自创建以来就已经连接着一个Excuter，
	 * 在sendRunner()当中直接将自己的一个成员变量excutee压入Runner。
	 * @return  返回自身的引用，
	 * 之所以返回自身是为了在Runner当中能够连续执行对于Excutee的方法，
	 * 详情参考Runner类当中run()方法的内部定义。
	 */
	@Override
	public IExcutee fire() {
		hostFunction.invoke(paragraphToFire);
		return this;
	}

	/**
	 * 由于这个类既是Excutee也是Excuter，
	 * 所以调用这个方法返回的就是自身。
	 * @return 自身。
	 */
	@Override
	public IExcuter getExcuter() {
		return this;
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
