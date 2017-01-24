package functionInterface;

public interface IReturnvalList {
	//添加Returnval，用int返回添加的结果，成功返回1
	public int insertReturnval(String returnvalName, String rClass);
	//查找Parameter
	public IReturnval getReturnval(String returnvalName);
	public IReturnval getReturnval(int returnvalNumber);
	//删除Parameter
	public int deleteReturnval(String returnvalName);
}
