package functionInterface;

import basicInterface.ICountable;

public interface IParameterList extends ICountable{
	//添加Parameter，用int返回添加的结果，成功返回1
	public int insertParameter(IParameter parameter);
	//查找Parameter
	public IParameter Parameter(String parameterName);
	public IParameter getParameter(int parameterNumber);
	//删除Parameter
	public int deleteParameter(String parameterName);
	public void prepareParameters();
}
