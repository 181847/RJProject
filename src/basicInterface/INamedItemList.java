package basicInterface;

public interface INamedItemList extends ICountable{
	/**
	 * 返回存储了多少个对象，
	 * @return 可命名列表的元素数量。
	 */
	public int getNum();
	
	/**
	 * 插入一个可命名对象，用int返回插入的结果。
	 * @param namedItem 要插入命名列表的对象。
	 * @return 如果插入的元素为null返回0；
	 * 如果已存在相同名字的元素就返回2；
	 * 如果插入成功就返回1。
	 */
	public int insertItem(INameable namedItem);
	
	/**
	 * 通过名字得到一个可命名对象，
	 * @param name 想要获取的命名对象的名字。
	 * @return 指定名字的元素，
	 * 如果没有找到就返回null。
	 */
	public INameable getItem(String name);
	
	/**
	 * 通过序号得到一个可命名对象。
	 * @param index 想要获取的命名对象的在命名列表中的序号。
	 * @return 指定序号的元素，
	 * 如果没有找到就返回null。
	 */
	public INameable getItem(int index);
	
	/**
	 * 删除一个可命名对象，
	 * @param name 想要删除的的命名对象的名字。
	 * @return 未找到指定名称的可命名对象返回-1。
	 * 成功则返回1。
	 */
	public int deleteItem(String name);
	
	/**
	 * 返回指定可命名对象的序号。
	 * @param name 指定的名字
	 * @return 成功返回大于等于0的数字，
	 * 失败返回-1。
	 */
	public int getIndexOf(String name);
}
