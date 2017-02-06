package functionInterface;
import basicInterface.ICountable;

public interface IReturnvalList extends ICountable{
	/**
	 * 插入一个Returnval，用int返回插入的结果。
	 * @param returnval 要插入的Returnval。
	 * @return 如果插入的Returnval为null返回0；
	 * 如果已存在相同名字的Returnval就返回2；
	 * 如果插入成功就返回1。
	 */
	public int insertReturnval(IReturnval returnval);
	
	/**
	 * 通过名字得到一个的Returnval，
	 * @param returnvalName 想要获取的Returnval的名字。
	 * @return 指定Returnval的元素，
	 * 如果没有找到就返回null。
	 */
	public IReturnval Returnval(String returnvalName);
	
	/**
	 * 通过序号得到一个的Returnval，
	 * @param returnvalIndex 想要获取的Returnval的名字。
	 * @return 指定Returnval的元素，
	 * 如果没有找到就返回null。
	 */
	public IReturnval getReturnval(int returnvalIndex);
	
	/**
	 * 删除一个Returnval，
	 * @param returnvalName 想要删除的的Returnval的名字。
	 * @return 未找到指定名称的Returnval返回-1。
	 * 成功则返回1。
	 */
	public int deleteReturnval(String returnvalName);
	
	/**
	 * 获取指定名字的Returnval的序号。
	 * @param returnvalName 目标Returnval。
	 * @return Returnval的序号，如果不存在就返回-1。
	 */
	public int getReturnvalIndexOf(String returnvalName);
}
