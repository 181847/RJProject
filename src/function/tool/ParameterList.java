package function.tool;
import basicTool.NamedItemList;
import functionInterface.*;

/**
 * 存储参数插口的列表，
 * 此类增加一个方法，
 * 通知内部的所有参数插口去他们相连的返回值哪里获取数据，
 * 用于FunctionHeadSlot中。
 */
public class ParameterList extends NamedItemList implements IParameterList{

	public ParameterList(){
		super();
	}

	public ParameterList(int space){
		super(space);
	}

	@Override
	public int insertParameter(IParameter parameter)
	{
		return insertItem(parameter);
	}

	@Override
	public IParameter Parameter(String parameterName)
	{
		return (IParameter)getItem(parameterName);
	}

	@Override
	public IParameter getParameter(int parameterNumber)
	{
		return (IParameter)getItem(parameterNumber);
	}

	@Override
	public int deleteParameter(String parameterName)
	{
		return deleteItem(parameterName);
	}

	@Override
	public int getParameterIndexOf(String parameterName) {
		return getIndexOf(parameterName);
	}

	@Override
	public void prepareParameters() {
		for (int i = 0; i < itemNum; ++i){
			((IParameter)buffer[i]).extractParameter();
		}
	}
}
