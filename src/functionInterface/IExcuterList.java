package functionInterface;
import basicInterface.ICountable;

public interface IExcuterList extends ICountable{
	//添加Excuter，用int返回添加的结果，成功返回1
	public int insertExcuter(IExcuter excuter);
	//查找Excuter
	public IExcuter getExcuter(String excuterName);
	public IExcuter getExcuter(int excuterNumber);
	//删除Excuter
	public int deleteExcuter(String excuterName);
}
