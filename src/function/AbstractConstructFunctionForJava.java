package function;

import functionInterface.IExcuter;
import functionInterface.IFunction;

public abstract class AbstractConstructFunctionForJava extends AbstractFunctionNeedParameterForJava implements IFunction {
	
	/**
	 * Excutee: “construct”，
	 * Parameter: “source”，
	 * Excuter: “EXCEPTION”，
	 * Excuter: “constructEnd”，
	 * Returnval: “newInstance”。
	 * 一个自身RClass类型的返回值，
	 * 参数可以任意多个，但至少有一个自身RClass类型的参数。
	 * @param name 构造Function的名字，
	 * 这一项也会用来添加一个本类型的参数和返回值，
	 * 所以务必使这个名字，
	 * 和构造Function所属的RClass的名字相同。
	 * @param parameterList 参数列表的初始化空间数量，
	 * 构造Function会自动在这个空间数量上加一个，
	 * 这多出来的一个用来放置一个本RClass类型的Parameter。
	 */
	public AbstractConstructFunctionForJava(String name, int parameterList){
		super(name, 1, parameterList + 1, 1, 1);
		super.insertExcutee("construct", 1);
		super.insertParameter(name, "source");
		super.insertExcuter("constructEnd");
		super.insertReturnval(name, "newInstance");
	}

	/**
	 * 子类唯一需要重载的方法run()。
	 */
	@Override
	public abstract IExcuter run(int paragraph);
	
	/**
	 * 强制重载这个添加Excutee的方法，
	 * 不允许用户自行添加Excutee。
	 */
	@Override
	public int insertExcutee(String excuteeeName, int paragrah){
		//Empty Body
		return 0;
	}
	
	/**
	 * 强制重载这个添加Excuter的方法，
	 * 不允许用户自行添加Excuter。
	 */
	@Override
	public int insertExcuter(String excuterName){
		return 0;
	}
	
	/**
	 * 强制重载这个添加Returnval的方法，
	 * 不允许用户自行添加Returnval。
	 */
	@Override
	public int insertReturnval(String rClass, String returnvalName){
		return 0;
	}
}
