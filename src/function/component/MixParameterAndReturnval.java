package function.component;

import functionInterface.IExcutee;
import functionInterface.IParameter;
import functionInterface.IReturnval;

/**
 * 这个特殊种类的类既可以当Parameter，
 * 也可以当作Returnval，
 * 作参数和返回值时，所包含的数据都是一样的，
 * 这个类专门用于HeadSentryFunction和RearSentryFunction。
 * @author 75309
 *
 */
public class MixParameterAndReturnval extends Parameter implements IParameter, IReturnval {

	public MixParameterAndReturnval(String referenceClass, String parameterName) {
		super(referenceClass, parameterName);
	}
	
	/**
	 * 这个类作为Returnval的时候并不能使得Runner反向遍历基本运算Function，
	 * 所以这个方法的返回值一直为null。
	 */
	@Override
	public IExcutee getExcutee() {
		//Empty Body
		return null;
	}

	/**
	 * 这个类作为Returnval的时候并不能使得Runner反向遍历基本运算Function，
	 * 所以这个方法有任何实际功能，返回值一直为0。
	 */
	@Override
	public int linkExcutee(IExcutee excutee) {
		//Empty Body
		return 0;
	}

}
