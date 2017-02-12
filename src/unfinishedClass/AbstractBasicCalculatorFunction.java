package unfinishedClass;

import function.FunctionNeedParameters;
import function.component.LinerExcutee;
import functionInterface.IExcutee;
import functionInterface.IExcuter;
import functionInterface.IFunction;
import functionInterface.IReturnval;

public abstract class AbstractBasicCalculatorFunction extends FunctionNeedParameters implements IFunction {
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
		super.insertExcutee(new LinerExcutee("fire", 1));
		
	}

	@Override
	public abstract IExcuter run(int paragraph);

	@Override
	public void fillContentGraph() {}

	@Override
	public void clearGraph() {}
	
	/**
	 * 强制重载这个添加Excutee的方法，
	 * ConstructFunction只需要有一个Excutee，
	 * 而这个唯一的一个Excutee在AbstractConstructFunction的构造方法中添加。
	 * AbstractConstructFunction.insertExcutee()无任何作用。
	 */
	@Override
	public int insertExcutee(IExcutee excutee){
		return 0;
	}
	
	/**
	 * 强制重载这个添加Excuter的方法，
	 * ConstructFunction只需要有一个Excuter，
	 * 而这个唯一的一个Excuter在AbstractConstructFunction的构造方法中添加。
	 * AbstractConstructFunction.insertExcuter()无任何作用。
	 */
	@Override
	public int insertExcuter(IExcuter excuter){
		return 0;
	}
	
	@Override
	public int insertReturnval(IReturnval returnval){
		if (returnval != null){
			returnval.linkExcutee(getExcutee(0));
		}
		
		return super.insertReturnval(returnval);
	}
}
