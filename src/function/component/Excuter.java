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
	 */
	@Override
	public void linkExcutee(IExcutee excutee)
	{
		this.excutee = excutee;
	}


	
}
