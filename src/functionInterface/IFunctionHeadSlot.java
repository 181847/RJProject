package functionInterface;

public interface IFunctionHeadSlot {
	//添加Excutee，用int返回添加的结果，成功返回1
	int addExcutee(String excuteeName, int paragraphToFire, int nextExcuterInRearSlot);
	//查找Excutee
	IExcutee getExcutee(String excuteeName);
	IExcutee getExcutee(int excuteeNumber);
	//删除Excutee
	int removeExcutee(String excuteeName);
	
	//添加Parameter，用int返回添加的结果，成功返回1
	int addParameter(String ParameterName, String rClass);
	//查找Parameter
	IParameter getParameter(String parameterName);
	IParameter getParameter(int parameterNumber);
	//删除Parameter
	int removeParameter(String parameterName);
}
