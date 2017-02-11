package functionInterface;
import basicInterface.ICountable;

public interface IExcuteeList extends ICountable{
	/**
	 * 插入一个Excutee，用int返回插入的结果。
	 * @param excutee 要插入的Excutee。
	 * @return 如果插入的Excutee为null返回0；
	 * 如果已存在相同名字的Excuter就返回2；
	 * 如果插入成功就返回1。
	 */
	public int insertExcutee(IExcutee excutee);
	
	/**
	 * 通过名字得到一个的Excutee，
	 * @param excuteeName 想要获取的Excutee的名字。
	 * @return 指定Excutee的元素，
	 * 如果没有找到就返回null。
	 */
	public IExcutee Excutee(String excuteeName);
	
	/**
	 * 通过序号得到一个的Excutee，
	 * @param excuteeIndex 想要获取的Excutee的名字。
	 * @return 指定Excutee的元素，
	 * 如果没有找到就返回null。
	 */
	public IExcutee getExcutee(int excuteeIndex);
	
	/**
	 * 删除一个Excutee，
	 * @param excuteeName 想要删除的的Excutee的名字。
	 * @return 未找到指定名称的Excutee返回-1。
	 * 成功则返回1。
	 */
	public int deleteExcutee(String excuteeName);
	
	/**
	 * 获取指定名字的Excutee的序号。
	 * @param excuteeName 目标Excutee。
	 * @return Excutee的序号，如果不存在就返回-1。
	 */
	public int getExcuteeIndexOf(String excuteeName);
}
