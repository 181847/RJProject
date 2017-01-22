package functionInterface;

public interface IParameterList {
	//添加Parameter，用int返回添加的结果，成功返回1
	int addParameter(String ParameterName, String rClass);
	//查找Parameter
	IParameter getParameter(String parameterName);
	IParameter getParameter(int parameterNumber);
	//删除Parameter
	int removeParameter(String parameterName);
}
