package unfinishedClass;

import functionInterface.IExcuter;
import functionInterface.IFunction;

public class HeadSentryFunction extends AbstractSentryFunction implements IFunction {

	public HeadSentryFunction() {
		super("unknownNameForHeadSentryFunction", 5, 5, 5, 5);
	}

	@Override
	public IExcuter run(int paragraph) {
		System.out.println("Runner has walked through the headSentryFunction.");
		return null;
	}

	@Override
	public boolean needParameters() {
		return getParameterList().getNum() > 0;
	}
	
	/**
	 * 这个方法被重载为向这个Function同时插入同类型、同名称的Parameter和Returnval，
	 * 而且这两个对象共用同一个RReference，
	 * 此方法的功能和调用这个类的insertReturnval(String, String)是一样的。
	 * @param rClass 参数和返回值的类型。
	 * @param name 参数和返回值的名字。
	 * @return 插入成功返回1，
	 * 元素为null返回0，
	 * 只有一个列表当中存在同名元素时返回2，
	 * 两个列表当中都存在同名元素时返回4。
	 */
	@Override
	public int insertParameter(String rClass, String parameterName){
		return insertParameterAndReturnval(rClass, parameterName);
	}
	
	/**
	 * 这个方法被重载为向这个Function同时插入同类型、同名称的Parameter和Returnval，
	 * 而且这两个对象共用同一个RReference，
	 * 此方法的功能和调用这个类的insertParameter(String, String)是一样的。
	 * @param rClass 参数和返回值的类型。
	 * @param name 参数和返回值的名字。
	 * @return 插入成功返回1，
	 * 元素为null返回0，
	 * 只有一个列表当中存在同名元素时返回2，
	 * 两个列表当中都存在同名元素时返回4。
	 */
	@Override
	public int insertReturnval(String rClass, String returnvalName){
		return insertParameterAndReturnval(rClass, returnvalName);
	}
	
	/**
	 * 这个方法被重载为向这个Function同时插入同名称的Excutee和Excuter，
	 * 而且强制Excutee发动后会执行Excuter。
	 * @param name 执行入口和执行出口的名字。
	 * @param paragraphToFire Excutee对应于Function中run(int)的参数，
	 * 这个数字用于对应Function的不同功能。
	 * @return 插入成功返回1，
	 * 元素为null返回0，
	 * 只有一个列表当中存在同名元素时返回2，
	 * 两个列表当中都存在同名元素时返回4。
	 */
	@Override
	public int insertExcutee(String excuteeName, int paragraphToFire){
		return insertExcuteeAndExcuter(excuteeName, paragraphToFire);
	}
}
