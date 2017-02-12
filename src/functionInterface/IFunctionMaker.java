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
	
	/**
	 * 专门适用于完全自定义型RClass的方法，通过读取相应的function描述文件，
	 * 对CustomFunction的内部进行填充。
	 * @param rearSlot 某个CustomFunction的内部裸露出来的Returnval和Excuter；
	 * @param headSlot 某个CustomFunction的内部裸露出来的Excutee和Parameter；
	 * @return 成功填充返回1，失败返回0。
	 */
	public int fillFunctionGraphOf(IFunctionRearSlot rearSlot, IFunctionHeadSlot headSlot);

}
