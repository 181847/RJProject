package function;

import functionInterface.IExcuter;
import functionInterface.IFunction;
import functionInterface.IParameter;

/**
 * 本类针对所有需要参数的Function来创建的抽象类，
 * 这个类的Function覆盖了needParameters()方法，
 * 使得调用这个方法一定会返回true，
 * 添加了两个获取和设置THIS参数的方法。
 * 之所以创建这么一种Function抽象类是因为，
 * 方法一般都是需要参数的，极少情况下有不需要参数的Function。
 * 通过这个类来派生出AbstractBasicCalculatorFunction、
 * AbstractConstructionFunctionForJava。
 * 插口详情
 * Excutee：无；
 * Parameter：无；
 * Excuter：
 * 【
 * {ExceptionExcuter，“EXCEPTION”，异常执行出口 }
 * 】
 * Returnval：无。
 */
public abstract class AbstractFunctionNeedParameterForJava extends AbstractFunctionForJava implements IFunction {

	/**
	 * @param name function的名字
	 * @param excuteeList excutee列表的初始化空间数量。
	 * @param parameterList parameter列表的初始化空间数量。
	 * @param excuterList excuter列表的初始化空间数量。
	 * @param returnvalList returnval列表的初始化空间数量。
	 */
	public AbstractFunctionNeedParameterForJava(String name,
			int excuteeList, int parameterList,
			int excuterList,int returnvalList) {
		super(name, excuteeList, parameterList, excuterList, returnvalList);
	}

	@Override
	public abstract IExcuter run(int paragraph);

	/**
	 * @return 继承了AbstractFunctionNeedParameterForJava的Function类，
	 * 如果不重载，这个返回值永远为true。
	 */
	@Override
	public boolean needParameters() {
		return true;
	}

	/**
	 * 自动插入一个指定类型的名字为“THIS”的参数，
	 * 作为Function的主要调用者。
	 * @param rClass THIS参数的类型
	 * @return 如果插入的Parameter为null返回0；
	 *  如果已存在相同名字的Parameter就返回2； 
	 *  如果插入成功就返回1。
	 */
	public int insertTHIS(String rClass){
		return insertParameter(rClass, "THIS");
	}
	
	/**
	 * 返回名为"THIS"的参数对象。
	 * @return 名为"THIS"的参数对象。
	 */
	public IParameter THIS(){
		return Parameter("THIS");
	}
}
