package function;

import function.component.ReturnvalWithExcutee;
import functionInterface.IExcutee;
import functionInterface.IExcuter;
import functionInterface.IFunction;
import functionInterface.IReturnval;

public abstract class AbstractBasicCalculatorFunction extends AbstractFunctionNeedParameterForJava implements IFunction {
	/**
	 * 基本运算节点统一包括一个名为“fire”的Excutee，
	 * 不包含Excuter。
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
	 * 强制重载这个添加Returnval的方法，
	 * 不允许用户自行添加Returnval的实例对象，
	 * 用户如果想添加Returnval，
	 * 请使用方法：int insertReturnval(String returnvalString)，
	 * 这个方法只需要传入Returnval的名字就可以添加一个新的Returnval了。
	 */
	@Override
	public int insertReturnval(IReturnval returnval){
		//Empty Body
		return 0;
	}
	
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
	public int insertExcutee(IExcutee excutee){
		//Empty Body
		return 0;
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
	public int insertExcuter(IExcuter excuter){
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
