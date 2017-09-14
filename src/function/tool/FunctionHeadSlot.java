package function.tool;
import functionInterface.*;

/**
 * 存储执行入口列表 和 参数插口列表，用于Function中，
 * 对执行入口和参数插口统一管理。
 */
public class FunctionHeadSlot implements IFunctionHeadSlot{
	public IExcuteeList excuteeList;
	public IParameterList parameterList;
	
	public FunctionHeadSlot(){
		excuteeList = new ExcuteeList();
		parameterList = new ParameterList();
	}
	
	public FunctionHeadSlot(int excuteeListSpace, int parameterListSpace){
		excuteeList = new ExcuteeList(excuteeListSpace);
		parameterList = new ParameterList(parameterListSpace);
	}
	
	/**
	 * 返回excutee和parameter的总数
	 * @return excutee和parameter的总数
	 */
	@Override
	public int getNum() {
		return excuteeList.getNum() + parameterList.getNum();
	}

	@Override
	public int insertExcutee(IExcutee excutee)
	{
		return excuteeList.insertExcutee(excutee);
	}

	@Override
	public IExcutee Excutee(String excuteeName)
	{
		return excuteeList.Excutee(excuteeName);
	}
	
	@Override
	public IExcutee getExcutee(int excuteeNumber)
	{
		return excuteeList.getExcutee(excuteeNumber);
	}
	
	@Override
	public int deleteExcutee(String excuteeName)
	{
		return excuteeList.deleteExcutee(excuteeName);
	}
	
	@Override
	public int getExcuteeIndexOf(String excuteeName) {
		return excuteeList.getExcuteeIndexOf(excuteeName);
	}

	@Override
	public int insertParameter(IParameter parameter)
	{
		return parameterList.insertParameter(parameter);
	}

	@Override
	public IParameter Parameter(String parameterName)
	{
		return parameterList.Parameter(parameterName);
	}

	@Override
	public IParameter getParameter(int parameterNumber)
	{
		return parameterList.getParameter(parameterNumber);
	}

	@Override
	public int deleteParameter(String parameterName)
	{
		return parameterList.deleteParameter(parameterName);
	}

	@Override
	public IExcuteeList getExcuteeList()
	{
		return excuteeList;
	}

	@Override
	public IParameterList getParameterList()
	{
		return parameterList;
	}
	
	@Override
	public int getParameterIndexOf(String parameterName) {
		return parameterList.getParameterIndexOf(parameterName);
	}
	
	@Override
	public void prepareParameters() {
		parameterList.prepareParameters();
	}
}
