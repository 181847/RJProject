package functionInterface;
import runnerInterface.IRunner;

public interface IExcutee{
	//欢迎Runner的到来，检查参数是否遍历
	public void welcomeRunner(IRunner runner);
	//发动hostFunction的运算功能
	public void fire();
	//为Runner指明下一个要运行的地方
	public void sendRunner(IRunner runner);
}
