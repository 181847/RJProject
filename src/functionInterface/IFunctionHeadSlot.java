package functionInterface;

public interface IFunctionHeadSlot extends IExcuteeList, IParameterList{
	/**
	 * 得到ExcuteeList。
	 * @return 一个Excutee列表。
	 */
	public IExcuteeList getExcuteeList();
	
	/**
	 * 得到ParameterList。
	 * @return 一个Parameter列表。
	 */
	public IParameterList getParameterList();
}
