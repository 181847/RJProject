package functionInterface;

public interface IReturnvalList {
	//添加Returnval，用int返回添加的结果，成功返回1
	int addReturnval(String returnvalName, String rClass);
	//查找Parameter
	IReturnval getReturnval(String returnvalName);
	IReturnval getReturnval(int returnvalNumber);
	//删除Parameter
	int removeReturnval(String returnvalName);
}
