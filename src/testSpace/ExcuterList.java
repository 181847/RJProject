package testSpace;
import functionInterface.IExcuterList;
import functionInterface.IExcuter;

public class ExcuterList extends NamedItemList implements IExcuterList
{

	public ExcuterList(){}

	public ExcuterList(int space){
		super(space);
	}

	@Override
	public int insertExcuter(IExcuter excuter)
	{
		return insertItem(excuter);
	}

	@Override
	public IExcuter getExcuter(String excuterName)
	{
		return (IExcuter)getItem(excuterName);
	}

	@Override
	public IExcuter getExcuter(int excuterNumber)
	{
		return (IExcuter)getItem(excuterNumber);
	}

	@Override
	public int deleteExcuter(String excuterName)
	{
		return deleteItem(excuterName);
	}
}
