package functionInterface;

public interface IFunctionFactory {
	/**
	 * 插入一个新的FunctionMaker。
	 * @param functionMaker 要插入的FunctionMaker。
	 * @return 如果插入的元素为null返回0；
	 * 如果已存在相同名字的元素就返回2；
	 * 如果插入成功就返回1。
	 */
	public int insertFunctionMaker(IFunctionMaker functionMaker);
	
	/**
	 * 添加一个能够创建构造Function的FunctionMaker。
	 * @param constructFunctionMaker 能够创建构造Function的FunctionMaker。
	 * @return 添加成功返回1，如果ConstructFunction已存在就返回-1，
	 * 并且不对原来的ConstructFunctionMaker做任何的修改。
	 */
	public int insertConstructFunctionMaker(IFunctionMaker constructFunctionMaker);
	
	/**
	 * 这个特殊的方法专门用于获取FunctionFactory当中的构造Function。
	 * @return 一个ConstructFunction。
	 */
	public IFunction getConstructFunction();
	
	/**
	 * 获取指定名字的Function的序号，不包括构造Function。
	 * @param functionName Function的名字。
	 * @return  指定名字的Function在这个FunctionFactory中的序号。
	 */
	public int getFunctionIndexOf(String functionName);
	
	/**
	 * 通过名字获取Function，不包括构造Function。
	 * @param functionName Function的名字。
	 * @return 新的Function的实例对象。
	 */
	public IFunction getFunction(String functionName);
	
	/**
	 * 通过序号获取Function，不包括构造Function。
	 * @param functionIndex Function的序号。
	 * @return 新的Function的实例对象。
	 */
	public IFunction getFunction(int functionIndex);
}