package unfinishedClass;

import function.FunctionNeedParameters;
import function.component.*;
import functionInterface.IExcutee;
import functionInterface.IExcuter;
import functionInterface.IFunction;
import functionInterface.IReturnval;

public abstract class AbstractConstructFunction extends FunctionNeedParameters implements IFunction {
	
	/**
	 * 构造Function只允许有一个Excutee，
	 * 一个Excuter，
	 * 一个Returnval，
	 * 参数可以任意多个。
	 * @param name 构造Function的名字，这一项会用来添加一个本类型的Paramter
	 * 这个名字应该和构造Function所属的RClass的名字相同。
	 * @param parameterList 参数列表的初始化空间数量，
	 * 构造Function会自动在这个空间数量上加一个，
	 * 这多出来的一个用来放置一个隐藏的本RClass类型的Parameter。
	 */
	public AbstractConstructFunction(String name, int parameterList){
		super(name, 1, parameterList + 1, 1, 1);
		super.insertExcutee(new LinerExcutee("construct", 1));
		super.insertParameter(new Parameter(name, "source"));
		super.insertExcuter(new Excuter("constructEnd"));
		super.insertReturnval(new Returnval(name, "newInstance"));
	}

	/**
	 * 子类唯一需要重载的方法run()。
	 */
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
	
	/**
	 * 强制重载这个添加Returnval的方法，
	 * ConstructFunction只需要有一个Returnval，
	 * 而这个唯一的一个Returnval在AbstractConstructFunction的构造方法中添加。
	 * AbstractConstructFunction.insertReturnval()无任何作用。
	 */
	@Override
	public int insertReturnval(IReturnval returnval){
		return 0;
	}
}
