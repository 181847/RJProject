package basicInterface;

public interface INamedItemList extends ICountable{
	//插入一个可命名对象，用int返回插入的结果
	public int insertItem(INameable namedItem);
	//得到一个可命名对象
	public INameable getItem(String name);
	public INameable getItem(int index);
	//删除一个可命名对象
	public int deleteItem(String name);
}
