package functionInterface;
import basicInterface.INameable;

public interface IExcuter extends INameable{
	//返回连接的Excutee
	public IExcutee getExcutee();
	//链接Excutee
	public void linkExcutee(IExcutee excutee);
}
