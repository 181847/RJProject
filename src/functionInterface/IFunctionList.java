package functionInterface;

import basicInterface.INamedItemList;

public interface IFunctionList extends INamedItemList {
	/**
	 * 添加Function。
	 * @param 要加入的Function实例。
	 * @return 成功返回1。
	 */
	public int insertFunction(IFunction function);
	
	/**
	 * 通过名字查找Function。
	 * @param functionName 要查找的function的名字。
	 * @return 失败返回null。
	 */
	public IFunction getFunction(String functionName);
	
	/**
	 * 通过序号查找Function。
	 * @param functionNumber 要查找的function的序号，调用者必须知道目标的序号。
	 * @return 失败返回null。
	 */
	public IFunction getFunction(int functionNumber);
	
	/**
	 * 删除指定名称的Function。
	 * @param functionName 要删除的目标Function的名字。
	 * @return 删除成功返回1，失败返回0。
	 */
	public int deleteFunction(String functionName);
}
