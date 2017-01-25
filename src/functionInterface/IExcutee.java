package functionInterface;
import basicInterface.INameable;
import runnerInterface.IRunner;

public interface IExcutee extends INameable{
	//欢迎Runner的到来，检查参数是否遍历
	public void welcomeRunner(IRunner runner);
	//发动hostFunction的运算功能
	public void fire();
	//为Runner指明下一个要运行的地方
	public void sendRunner(IRunner runner);
	//设定Excutee所从属的Function
	public void setHostFunction(IFunction hostFunction);
}
