package testSpace;
import functionInterface.*;
import basicInterface.*;

public class ExcuteeList extends NamedItemList implements IExcuteeList
{
	
	public ExcuteeList(){}
	
	public ExcuteeList(int space){
		super(space);
	}

	@Override
	public int insertExcutee(IExcutee excutee)
	{
		return insertItem(excutee);
	}

	@Override
	public IExcutee getExcutee(String excuteeName)
	{
		return (IExcutee)getItem(excuteeName);
	}

	@Override
	public IExcutee getExcutee(int excuteeNumber)
	{
		return (IExcutee)getItem(excuteeNumber);
	}

	@Override
	public int deleteExcutee(String excuteeName)
	{
		return deleteItem(excuteeName);
	}
}