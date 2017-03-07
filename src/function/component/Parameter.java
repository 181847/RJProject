package function.component;
import functionInterface.*;
import rClass.RClassLoaderManager;
import rClass.RReference;

/**
 * 参数插口，
 * 用来保存当前Function所需的参数，
 * 参数插口与返回值插口相连接，
 * 并且从返回值插口获取数据。
 */
public class Parameter extends RReference implements IParameter{
	IReturnval returnvalSource;
	
	/**
	 * @param referenceClass 参数的RClass类型名字。
	 * @param parameterName 参数引用的名字。
	 */
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
		if ( 1 == RClassLoaderManager.getRClassLoader().checkRClassMatchType(returnval.getRClassID(), getRClassID()) ){
			returnvalSource = returnval;
		}
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
