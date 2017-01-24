package functionInterface;

public interface IFunctionHeadSlot {
	//添加Excutee，用int返回添加的结果，成功返回1
	public int addExcutee(String excuteeName, int paragraphToFire, int nextExcuterInRearSlot);
	//查找Excutee
	public IExcutee getExcutee(String excuteeName);
	public IExcutee getExcutee(int excuteeNumber);
	//删除Excutee
	public int removeExcutee(String excuteeName);
	
	//添加Parameter，用int返回添加的结果，成功返回1
	public int addParameter(String ParameterName, String rClass);
	//查找Parameter
	public IParameter getParameter(String parameterName);
	public IParameter getParameter(int parameterNumber);
	//删除Parameter
	public int removeParameter(String parameterName);
}
