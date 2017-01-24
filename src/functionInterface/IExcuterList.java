package functionInterface;

public interface IExcuterList {
	//添加Excuter，用int返回添加的结果，成功返回1
	public int insertExcuter(String excuterName);
	//查找Excuter
	public IExcuter getExcuter(String excuterName);
	public IExcuter getExcuter(int excuterNumber);
	//删除Excuter
	public int deleteExcuter(String excuterName);
}
