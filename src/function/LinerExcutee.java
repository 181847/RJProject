package function;
import runnerInterface.*;

public class LinerExcutee extends AbstractExcutee{
	public LinerExcutee(String excuteeName, int paragraphToFire){
		super(excuteeName, paragraphToFire);
	}

	@Override
	public void sendRunner(IRunner runner)
	{
		if (nextExcuter != null)
			runner.pushExcutee(nextExcuter.getExcutee());
	}
}
