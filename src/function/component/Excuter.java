package function.component;
import basicTool.NameableWithString;
import functionInterface.*;

public class Excuter extends NameableWithString implements IExcuter{
	IExcutee excutee;
	
	public Excuter(String excuterName){
		setName(excuterName);
		excutee = null;
	}

	/**
	 * 返回连接的Excutee
	 * @return Excuter所连接的Excutee
	 */
	@Override
	public IExcutee getExcutee()
	{
		return excutee;
	}

	/**
	 * 链接Excutee
	 * @param excutee 链接的对象Excutee
	 * @return 插入元素为null返回0，成功返回1。
	 */
	@Override
	public int linkExcutee(IExcutee excutee)
	{
		if (excutee == null){
			return 0;
		}
		this.excutee = excutee;
		return 1;
	}


	
}
