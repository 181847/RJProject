package functionInterface;
import basicInterface.INameable;

public interface IExcuter extends INameable{
	/**
	 * 返回连接的Excutee
	 * @return Excuter所连接的Excutee
	 */
	public IExcutee getExcutee();
	
	/**
	 * 链接Excutee
	 * @param excutee 链接的对象Excutee
	 * @return 插入失败返回0
	 */
	public int linkExcutee(IExcutee excutee);
}
