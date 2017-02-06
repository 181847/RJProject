package functionInterface;
import basicInterface.ICountable;

public interface IExcuterList extends ICountable{
	/**
	 * 插入一个Excuter，用int返回插入的结果。
	 * @param excuter 要插入的Excuter。
	 * @return 如果插入的Excuter为null返回0；
	 * 如果已存在相同名字的Excuter就返回2；
	 * 如果插入成功就返回1。
	 */
	public int insertExcuter(IExcuter excuter);
	
	/**
	 * 通过名字得到一个的Excuter，
	 * @param excuterName 想要获取的Excuter的名字。
	 * @return 指定Excuter的元素，
	 * 如果没有找到就返回null。
	 */
	public IExcuter Excuter(String excuterName);
	
	/**
	 * 通过序号得到一个的Excuter，
	 * @param excuterIndex 想要获取的Excuter的名字。
	 * @return 指定Excuter的元素，
	 * 如果没有找到就返回null。
	 */
	public IExcuter getExcuter(int excuterIndex);
	
	/**
	 * 删除一个Excuter，
	 * @param excuterName 想要删除的的Excuter的名字。
	 * @return 未找到指定名称的Excuter返回-1。
	 * 成功则返回1。
	 */
	public int deleteExcuter(String excuterName);
	
	/**
	 * 获取指定名字的Excuter的序号。
	 * @param excuterName 目标Excuter。
	 * @return Excuter的序号，如果不存在就返回-1。
	 */
	public int getExcuterIndexOf(String excuterName);
}
