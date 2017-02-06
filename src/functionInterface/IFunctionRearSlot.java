package functionInterface;

public interface IFunctionRearSlot extends IExcuterList, IReturnvalList{
	/**
	 * 得到ExcuterList。
	 * @return 一个Excuter列表。
	 */
	public IExcuterList getExcuterList();
	
	/**
	 * 得到ReturnvalList。
	 * @return 一个Returnval列表。
	 */
	public IReturnvalList getReturnvalList();
}
