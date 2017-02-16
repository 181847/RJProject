package unfinishedClass;

import function.AbstractFunctionCatchException;
import functionInterface.IExcuter;
import functionInterface.IFunction;

public class RearSentryFunction extends AbstractFunctionCatchException implements IFunction {
	/**
	 * Excutee: "CATCH"，
	 * Excuter: “EXCEPTION”。
	 * 默认构造方法，
	 * 名字初始化为“unknownNameForHeadSentryFunction”，
	 * 各个Function的组件列表的空间数量均为5个。
	 */
	public RearSentryFunction() {
		super("unknownNameForRearSentryFunction", 5, 5, 5, 5);
	}

	/**
	 * Excutee : "CATCH"
	 * @param name function的名字
	 * @param excuteeList excutee列表的初始化空间数量。
	 * @param parameterList parameter列表的初始化空间数量。
	 * @param excuterList parameter列表的初始化空间数量。
	 * @param returnvalList returnval列表的初始化空间数量。
	 */
	public RearSentryFunction(String name, int excuteeList, int parameterList, int excuterList, int returnvalList) {
		super(name, excuteeList, parameterList, excuterList, returnvalList);
	}
	@Override
	public IExcuter run(int paragraph) {
		Logger.log("Runner has walked through the rearSentryFunction.");
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
		MixParameterAndReturnval shareTheData = new MixParameterAndReturnval(rClass, parameterName);
		return insertParameter(shareTheData) 
				* insertReturnval(shareTheData);
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
		return insertParameter(rClass, returnvalName);
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
		MixExcuteeAndExcuter shareTheExcutee = new MixExcuteeAndExcuter(excuteeName, paragraphToFire);
		return insertExcutee(shareTheExcutee)
				* insertExcuter(shareTheExcutee);
	}
	
	/**
	 * 强制重载这个添加Excuter的方法，
	 * 不允许用户自行添加Excuter。
	 */
	@Override
	public int insertExcuter(String excuterName){
		return 0;
	}
}
