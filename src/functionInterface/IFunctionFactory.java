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
	
	/**
	 * 通过customFunction的名字，
	 * 来填充自定义customFunction的内容图表。
	 * @param funcitonName
	 * 		customFunction的名字。
	 * @param headSentryFunction
	 * 		customFunction的headSentryFunction。
	 * @param rearSentryFunction
	 * 		customFunction的rearSentryFunction。
	 * @return
	 * 		成功返回1。
	 */
	public int fillFunctionGraphOf(String functionName,
			IFunction headSentryFunction,
			IFunction rearSentryFunction);

	/**
	 * 通过customFunction的序号，
	 * 来填充自定义customFunction的内容图表。
	 * @param funcitonName
	 * 		customFunction的名字。
	 * @param headSentryFunction
	 * 		customFunction的headSentryFunction。
	 * @param rearSentryFunction
	 * 		customFunction的rearSentryFunction。
	 * @return
	 * 		成功返回1。
	 */
	public int fillFunctionGraphOf(int functionIndex,
			IFunction headSentryFunction,
			IFunction rearSentryFunction);
}
