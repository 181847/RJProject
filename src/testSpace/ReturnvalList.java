package testSpace;
import functionInterface.*;
import basicInterface.*;

public class ReturnvalList extends NamedItemList implements IReturnvalList
{

	public ReturnvalList(){
		super();
	}

	public ReturnvalList(int space){
		super(space);
	}

	@Override
	public int insertReturnval(IReturnval returnval)
	{
		return insertItem(returnval);
	}

	@Override
	public IReturnval getReturnval(String returnvalName)
	{
		return (IReturnval)getItem(returnvalName);
	}

	@Override
	public IReturnval getReturnval(int returnvalNumber)
	{
		return (IReturnval)getItem(returnvalNumber);
	}

	@Override
	public int deleteReturnval(String returnvalName)
	{
		return deleteItem(returnvalName);
	}
}
