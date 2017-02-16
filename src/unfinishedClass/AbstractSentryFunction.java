package unfinishedClass;

import function.AbstractFunctionForJava;
import function.component.MixExcuteeAndExcuter;
import function.component.MixParameterAndReturnval;
import functionInterface.IExcuter;
import functionInterface.IFunction;

/**
 * 此抽象类暂时废除。
 */
public abstract class AbstractSentryFunction extends AbstractFunctionForJava implements IFunction {
	public AbstractSentryFunction(String name, int excuteeList, int parameterList, int excuterList, int returnvalList) {
		super(name, excuteeList, parameterList, excuterList, returnvalList);
	}

	@Override
	public abstract IExcuter run(int paragraph);

	@Override
	public abstract boolean needParameters();

	/**
	 * 这个方法很特殊，
	 * 他通过参数可以创建一种RReference的子类，
	 * 这种子类实现了IParameter接口和IReturnval接口，
	 * 这个方法会将这个混合类的实例同时插入到本Function的
	 * ParamterList和ReturnvalList中，
	 * 如此一来使得SentryFunction拥有同样类型和名称的参数和返回值，
	 * 而且参数和返回值的数据完全相同（因为是同一个实例），
	 * 方便参数传递。
	 * @param rClass 参数和返回值的类型。
	 * @param name 参数和返回值的名字。
	 * @return 插入成功返回1，
	 * 元素为null返回0，
	 * 只有一个列表当中存在同名元素时返回2，
	 * 两个列表当中都存在同名元素时返回4。
	 */
	public int insertParameterAndReturnval(String rClass, String name){
		MixParameterAndReturnval shareTheData = new MixParameterAndReturnval(rClass, name);
		return insertParameter(shareTheData) 
				* insertReturnval(shareTheData);
	}
	
	/**
	 * 这个方法很特殊，
	 * 他通过参数可以创建一种LinerExcutee的子类，
	 * 这种子类实现了IExcutee接口和IExcuter接口，
	 * 这个方法会将这个混合类的实例同时插入到本Function的
	 * ExcuteeList和ExcuterList中，
	 * 如此一来使得SentryFunction拥有同样名称的执行入口和执行出口。
	 * @param name 执行入口和执行出口的名字。
	 * @param paragraphToFire Excutee对应于Function中run(int)的参数，
	 * 这个数字用于对应Function的不同功能。
	 * @return 插入成功返回1，
	 * 元素为null返回0，
	 * 只有一个列表当中存在同名元素时返回2，
	 * 两个列表当中都存在同名元素时返回4。
	 */
	public int insertExcuteeAndExcuter(String name, int paragraphToFire){
		MixExcuteeAndExcuter shareTheExcutee = new MixExcuteeAndExcuter(name, paragraphToFire);
		return insertExcutee(shareTheExcutee)
				* insertExcuter(shareTheExcutee);
	}
	
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
	 * 强制重载这个添加Excutee的方法，
	 * 不允许用户自行添加Excutee。
	 */
	@Override
	public int insertParameter(String rClass, String parameterName){
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
