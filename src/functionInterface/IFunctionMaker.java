package functionInterface;

import basicInterface.INameable;

/**
 * IFunctionMaker是一个可命名对象，
 * 它的名字和它所要创造的function的名字相同。
 */
public interface IFunctionMaker extends INameable {
	/**
	 * 创建一个新的Function实例对象。
	 * @return 新的Function实例对象。
	 */
	public IFunction makeFunction();
}
