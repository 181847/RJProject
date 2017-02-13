package unfinishedClass;

import function.component.*;
import functionInterface.IExcutee;
import functionInterface.IExcuter;
import functionInterface.IFunction;
import functionInterface.IParameter;
import functionInterface.IReturnval;

public abstract class AbstractFunctionForJava extends AbstractFunctionWithSlot implements IFunction {

	/**
	 * @param name function的名字
	 * @param excuteeList excutee列表的初始化空间数量。
	 * @param parameterList parameter列表的初始化空间数量。
	 * @param excuterList excuter列表的初始化空间数量。
	 * @param returnvalList returnval列表的初始化空间数量。
	 */
	public AbstractFunctionForJava(String name,
			int excuteeList, int parameterList, 
			int excuterList, int returnvalList) {
		super(name, excuteeList, parameterList, excuterList, returnvalList);
	}

	@Override
	public abstract IExcuter run(int paragraph);

	@Override
	public void fillContentGraph() {
		//Empty Body
	}

	@Override
	public void clearGraph() {
		//Empty Body
	}
	
	@Override
	public abstract boolean needParameters();
	
	//*************************************add methods************************************************
	/**
	 * 在这个方法中，程序猿只需要给定excutee的名字，
	 * 和这个excutee对应run()中不同功能的数字，
	 * 内部自行创建一个LinerExcutee并添加到Function中。
	 * @param excuteeName excutee的名字。
	 * @param paragraph 对应run()中不同功能的数字。
	 * @return 如果插入的Excutee为null返回0；
	 * 如果已存在相同名字的Excuter就返回2；
	 * 如果插入成功就返回1。
	 */
	public int insertExcutee(String excuteeName, int paragraph){
		IExcutee excutee = new LinerExcutee(excuteeName, paragraph);
		return super.insertExcutee(excutee);
	}
	
	/**
	 * 在这个方法中，程序猿只需要给定参数的类型和参数的名字，
	 * 内部自行创建一个Parameter并添加到Function中。
	 * @param rClass 参数的类型。
	 * @param parameterName 参数的名字。
	 * @return 如果插入的Parameter为null返回0；
	 * 如果已存在相同名字的Parameter就返回2；
	 * 如果插入成功就返回1。
	 */
	public int insertParameter(String rClass, String parameterName){
		IParameter parameter = new Parameter(rClass, parameterName);
		return super.insertParameter(parameter);
	}
	
	/**
	 * 在这个方法中，程序猿只需要给定Excuter的名字，
	 * 内部自行创建一个Excuter并添加到Function中。
	 * @param excuterName Excuter的名字。
	 * @return 如果插入的Excuter为null返回0；
	 * 如果已存在相同名字的Excuter就返回2；
	 * 如果插入成功就返回1。
	 */
	public int insertExcuter(String excuterName){
		IExcuter excuter = new Excuter(excuterName);
		return super.insertExcuter(excuter);
	}
	
	/**
	 * 在这个方法中，程序猿只需要给定参数的类型和参数的名字，
	 * 内部自行创建一个Returnval并添加到Function中。
	 * @param rClass 参数的类型。
	 * @param returnvalName 参数的名字。
	 * @return 如果插入的Returnval为null返回0；
	 * 如果已存在相同名字的Returnval就返回2；
	 * 如果插入成功就返回1。
	 */
	public int insertReturnval(String rClass, String returnvalName){
		IReturnval returnval = new Returnval(rClass, returnvalName);
		return super.insertReturnval(returnval);
	}
}
