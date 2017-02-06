package function;
import functionInterface.*;
import rClass.RReference;

public class Parameter extends RReference implements IParameter{
	IReturnval returnvalSource;
	
	public Parameter(String referenceClass, String parameterName){
		super(referenceClass, parameterName);
		returnvalSource = null;
	}

	/**
	 * 从Parameter所连接的Returnval那里获得数据，
	 * Parameer是一种RReference，
	 * Returnval也是一种RReference，
	 * 获取数据相当于对Parameter向Returnval调用RReference类的set()方法
	 */
	@Override
	public void extractParameter()
	{
		set(returnvalSource);
	}
	
	/**
	 * 链接一个Returnval
	 * @param returnval 要连接的Returnval
	 */
	@Override
	public void linkReturnval(IReturnval returnval)
	{
		returnvalSource = returnval;
	}
	
	/**
	 * 返回链接的Returnval
	 * @return 本Parameter所连接的Returnval
	 */
	@Override
	public IReturnval getReturnval()
	{
		return returnvalSource;
	}
}
