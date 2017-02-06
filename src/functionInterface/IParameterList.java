package functionInterface;

import basicInterface.ICountable;

public interface IParameterList extends ICountable{
	/**
	 * 插入一个Parameter，用int返回插入的结果。
	 * @param parameter 要插入的Parameter。
	 * @return 如果插入的Parameter为null返回0；
	 * 如果已存在相同名字的Parameter就返回2；
	 * 如果插入成功就返回1。
	 */
	public int insertParameter(IParameter parameter);
	
	/**
	 * 通过名字得到一个的Parameter，
	 * @param parameterName 想要获取的Parameter的名字。
	 * @return 指定Parameter的元素，
	 * 如果没有找到就返回null。
	 */
	public IParameter Parameter(String parameterName);
	
	/**
	 * 通过序号得到一个的Parameter，
	 * @param parameterIndex 想要获取的Parameter的序号。
	 * @return 指定Parameter的元素，
	 * 如果没有找到就返回null。
	 */
	public IParameter getParameter(int parameterIndex);
	
	/**
	 * 删除一个Parameter。
	 * @param parameterName 想要删除的的Parameter的名字。
	 * @return 未找到指定名称的Parameter返回-1。
	 * 成功则返回1。
	 */
	public int deleteParameter(String parameterName);
	
	/**
	 * 对当前列表中的所有Parameter调用extractParameter()方法
	 */
	public void prepareParameters();
}
