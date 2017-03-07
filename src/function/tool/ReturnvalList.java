package function.tool;
import basicTool.NamedItemList;
import functionInterface.*;

/**
 * 存储执行入口的列表，用于FunctionRearSlot中。
 */
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
	public IReturnval Returnval(String returnvalName)
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

	@Override
	public int getReturnvalIndexOf(String returnvalName) {
		return getIndexOf(returnvalName);
	}
}
