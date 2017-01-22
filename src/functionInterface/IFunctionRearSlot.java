package functionInterface;

public interface IFunctionRearSlot {
	//添加Excuter，用int返回添加的结果，成功返回1
	int addExcuter(String excuterName);
	//查找Excuter
	IExcuter getExcuter(String excuterName);
	IExcuter getExcuter(int excuterNumber);
	//删除Excuter
	int removeExcuter(String excuterName);
	
	//添加Returnval，用int返回添加的结果，成功返回1
	int addReturnval(String returnvalName, String rClass);
	//查找Parameter
	IReturnval getReturnval(String returnvalName);
	IReturnval getReturnval(int returnvalNumber);
	//删除Parameter
	int removeReturnval(String returnvalName);
}
