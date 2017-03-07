package function;

import function.component.ReturnvalWithExcutee;
import functionInterface.IExcuter;
import functionInterface.IFunction;
import functionInterface.IReturnval;

/**
 * 基本运算节点的Function，
 * 这个Function重载了通过字符串添加返回值的方法，
 * 这个增加的返回值组件类型是特殊的ReturnvalWithExcutee，
 * 这个返回值一旦被Runner涉及，
 * 就会进而发动这个Function的运算功能，
 * 基本运算节点不允许用户自行添加执行入口，
 * 只有一个名为“fire”的执行入口，
 * 而且这个执行入口不应该显示的触发，
 * 而是应该通过这个Function的返回值组件来触发。
 * 插口详情
 * Excutee：
 * 【（阻止子类添加）
 * {LinerExcutee, “fire”，基本运算节点唯一的执行入口}
 * 】
 * Parameter：无；
 * Excuter：
 * 【（阻止子类添加）
 * {ExceptionExcuter，“EXCEPTION”，异常执行出口 }
 * 】
 * Returnval：无，添加的类型是ReturnvalWithExcutee。
 */
public abstract class AbstractBasicCalculatorFunction extends AbstractFunctionNeedParameterForJava implements IFunction {
	/**
	 * Excutee: “fire”，
	 * Excuter: “EXCEPTION”。
	 * 基本运算节点统一包括一个名为“fire”的Excutee。
	 * @param name 基本运算Function的名字。
	 * @param parameterList 参数空间数量。
	 * @param returnvalList 返回值空间数量。
	 */
	public AbstractBasicCalculatorFunction(String name, int parameterList, int returnvalList) {
		super(name, 1, parameterList,
				0, returnvalList);
		super.insertExcutee("fire", 1);
	}
	
	@Override
	public abstract IExcuter run(int paragraph);
	
	/**
	 * 在这个方法中，程序猿只需要给定参数的类型和参数的名字，
	 * 内部自行创建一个Returnval，
	 * 然后将Returnval连接到本function的唯一一个excutee，
	 * 最后添加到Function中。
	 * @param rClass 参数的类型。
	 * @param returnvalName 参数的名字。
	 * @return 如果插入的Returnval为null返回0；
	 * 如果已存在相同名字的Returnval就返回2；
	 * 如果插入成功就返回1。
	 */
	@Override
	public int insertReturnval(String rClass, String returnvalName){
		IReturnval returnval = new ReturnvalWithExcutee(rClass, returnvalName);
		returnval.linkExcutee(getExcutee(0));
		return super.insertReturnval(returnval);
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
	 * 强制重载这个添加Excuter的方法，
	 * 不允许用户自行添加Excuter。
	 */
	@Override
	public int insertExcuter(String excuterName){
		//Empty Body
		return 0;
	}
}
