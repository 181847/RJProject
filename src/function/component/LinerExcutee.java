package function.component;
import runnerInterface.*;

public class LinerExcutee extends AbstractExcutee{
	/**
	 * @param excuteeName Excutee的名字
	 * @param paragraphToFire Excutee对应于Function中run(int)的参数，
	 * 这个数字用于对应Function的不同功能。
	 */
	public LinerExcutee(String excuteeName, int paragraphToFire){
		super(excuteeName, paragraphToFire);
	}

	@Override
	public void sendRunner(IRunner runner)
	{
		if (nextExcuter != null){
			runner.pushExcutee(nextExcuter.getExcutee());
			nextExcuter = null;
		}
		isReady = !hostFunction.needParameters();
	}
}
