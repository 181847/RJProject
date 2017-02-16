package function.component;

import functionInterface.IExcutee;
import functionInterface.IExcuter;
import functionInterface.IFunction;
import runnerInterface.IRunner;

public class MixExcuteeAndExcuter extends LinerExcutee implements IExcutee, IExcuter {
	public IExcutee excutee;
	
	public MixExcuteeAndExcuter(String excuteeName, int paragraphToFire) {
		super(excuteeName, paragraphToFire);
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
	 * 如果nextExcuter为null，就会把自己当成下一个Excuter，
	 * 将成员变量excutee压入runner的运行栈，
	 * 如果nextExcuter有值，就会按照正常流程操作。
	 * 一般来说这个特殊的Excutee/Excuter只用在SentryFunction中，
	 * 在SentryFunction中的run()方法一般返回的Excuter是null，
	 * 只有在发生异常的时候nextExcuter才会被放置到异常线路上，
	 * 保证即使在SentryFunction中发生的异常也能够被处理。
	 * @param runner 操作对象Runner
	 */
	@Override
	public void sendRunner(IRunner runner){
		if (nextExcuter != null){
			runner.pushExcutee(nextExcuter.getExcutee());
		} else {
			runner.pushExcutee(excutee);
		}
		
		isReady = !hostFunction.needParameters();
	}
}
