package functionInterface;

public interface IParameterList {
	//添加Parameter，用int返回添加的结果，成功返回1
	public int insertParameter(IParameter parameter);
	//查找Parameter
	public IParameter getParameter(String parameterName);
	public IParameter getParameter(int parameterNumber);
	//删除Parameter
	public int deleteParameter(String parameterName);
}
