package functionInterface;

public interface IExcuteeList {
	//添加Excutee，用int返回添加的结果，成功返回1
	int addExcutee(String excuteeName, int paragraphToFire, int nextExcuterInRearSlot);
	//查找Excutee
	IExcutee getExcutee(String excuteeName);
	IExcutee getExcutee(int excuteeNumber);
	//删除Excutee
	int removeExcutee(String excuteeName);
}
