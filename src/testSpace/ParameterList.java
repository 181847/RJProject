package testSpace;
import functionInterface.*;

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
	public IParameter getParameter(String parameterName)
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
}
