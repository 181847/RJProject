package functionInterface;
import rClassInterface.IRReference;

public interface IParameter extends IRReference{
	/**
	 * 从Parameter所连接的Returnval那里获得数据，
	 * Parameer是一种RReference，
	 * Returnval也是一种RReference，
	 * 获取数据相当于对Parameter向Returnval调用RReference类的set()方法
	 */
	public void extractParameter();
	
	/**
	 * 链接一个Returnval
	 * @param returnval 要连接的Returnval
	 */
	public void linkReturnval(IReturnval returnval);
	
	/**
	 * 返回链接的Returnval
	 * @return 本Parameter所连接的Returnval
	 */
	public IReturnval getReturnval();
}
