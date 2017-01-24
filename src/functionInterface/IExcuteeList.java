package functionInterface;

public interface IExcuteeList {
	//添加Excutee，用int返回添加的结果，成功返回1
	public int insertExcutee(IExcutee excutee);
	//查找Excutee
	public IExcutee getExcutee(String excuteeName);
	public IExcutee getExcutee(int excuteeNumber);
	//删除Excutee
	public int deleteExcutee(String excuteeName);
}
