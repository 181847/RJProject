package function.component;

import functionInterface.IExcutee;
import functionInterface.IExcuter;
import runnerInterface.IRunner;

/**
 * 混合执行入口和执行出口，
 * 这种特殊的执行接口类用于SentryFunction当中，
 * SentryFunction本身应该不具备任何执行代码，
 * 他的作用只是作为CustomFunction的外壳，
 * 将Runner以及参数数据转入或传出CustomFunction，
 * 除了异常执行出口，
 * SentryFunction的其他执行入口和执行出口都应该一一对应，
 * 名字完全相同，
 * 于是这种混合类型的执行接口就出现了。
 * 既可以作为执行入口也可以执行执行出口，
 * 内部共用一个字符串名字。
 * 除非SentryFunction中特别指定执行出口（比如发生异常时转到异常出口），
 * 否则其他正常情况下，
 * 这个执行入口在执行完毕之后会将Runner转到内部的excutee成员指定的执行入口上，
 * 因为他自己本身就是一个执行出口。
 */
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
