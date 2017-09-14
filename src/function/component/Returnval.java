package function.component;
import functionInterface.*;
import rClass.RReference;

/**
 * 返回值插口，
 * 用来保存当前Function执行完成的输出数据，
 * 返回值插口与参数插口相连接，
 * 为参数提供数据。
 */
public class Returnval extends RReference implements IReturnval{
	/**
	 * @param referenceClass 返回值的类型。
	 * @param returnvalName 返回值的名字。
	 */
	public Returnval(String referenceClass, String returnvalName){
		super(referenceClass, returnvalName);
	}

	/**
	 * 这种类型的Returnval不具备反向传递Runner的功能，
	 * 所以这个方法直接返回null。
	 */
	@Override
	public IExcutee getExcutee(){
		return null;
	}

	/**
	 * 这种类型的Returnval不具备反向传递Runner的功能，
	 * 所以这个方法不会将返回值与任何Function的Excutee相连接。
	 */
	@Override
	public int linkExcutee(IExcutee excutee){
		//Empty Body
		return 0;
	}
}
