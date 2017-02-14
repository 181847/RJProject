package unfinishedClass.basicRClass.RException;

import function.AbstractFunctionNeedParameterForJava;
import functionInterface.IExcuter;
import functionInterface.IFunction;

/**
 * Excutee: throw 正常线路的入口。
 * Parameter: Exception e “Exception”类型的参数（RReference）。
 * Excuter: EXCEPTION 从父类哪里继承到的Excuter抛出异常时，直接使用这个插口。
 * 本Function主动将Runner转入到异常线路，
 * 因为每个Function都会默认有一个名为“EXCEPTION”的Excuter，
 * 本Function将Runner转到这个Excuter上，并且将参数中的Exception参数提取出来，
 * 放进这个Excuter中。
 * @author 75309
 *
 */
public class ThrowExceptionFunction extends AbstractFunctionNeedParameterForJava implements IFunction {

	public ThrowExceptionFunction() {
		super("ThrowException", 1, 1, 0, 0);
		insertExcutee("throw", 1);
		insertParameter("Exception", "exception");
	}

	@Override
	public IExcuter run(int paragraph) {
		Exception e = (Exception) Parameter("exception").readObject();
		return dealWithException(e);
	}

}
