package functionInterface;

public interface IExcuterList {
	//添加Excuter，用int返回添加的结果，成功返回1
	int addExcuter(String excuterName);
	//查找Excuter
	IExcuter getExcuter(String excuterName);
	IExcuter getExcuter(int excuterNumber);
	//删除Excuter
	int removeExcuter(String excuterName);
}
