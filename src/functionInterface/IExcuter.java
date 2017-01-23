package functionInterface;

public interface IExcuter{
	//返回连接的Excutee
	public IExcutee getExcutee();
	//链接Excutee
	public void linkExcutee(IExcutee excutee);
}
