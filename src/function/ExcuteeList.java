package function;
import basicTool.NamedItemList;
import functionInterface.*;

public class ExcuteeList extends NamedItemList implements IExcuteeList
{
	
	public ExcuteeList(){
		//按照父类NameItemList的无参构造方法
		//自动申请五个元素空间
	}
	
	public ExcuteeList(int space){
		super(space);
	}
	
	@Override
	public int insertExcutee(IExcutee excutee)
	{
		return insertItem(excutee);
	}

	@Override
	public IExcutee Excutee(String excuteeName)
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

	@Override
	public int getExcuteeIndexOf(String excuteeName) {
		return getIndexOf(excuteeName);
	}
}
