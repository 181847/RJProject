package function.tool;
import functionInterface.IExcuterList;
import basicTool.NamedItemList;
import functionInterface.IExcuter;

/**
 * 存储执行出口的列表，用于FunctionRearSlot中。
 */
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
	public IExcuter Excuter(String excuterName)
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

	@Override
	public int getExcuterIndexOf(String excuterName) {
		return getIndexOf(excuterName);
	}
}
