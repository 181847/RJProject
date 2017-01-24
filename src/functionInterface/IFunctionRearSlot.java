package functionInterface;

public interface IFunctionRearSlot {
	//添加Excuter，用int返回添加的结果，成功返回1
	public int addExcuter(String excuterName);
	//查找Excuter
	public IExcuter getExcuter(String excuterName);
	public IExcuter getExcuter(int excuterNumber);
	//删除Excuter
	public int removeExcuter(String excuterName);
	
	//添加Returnval，用int返回添加的结果，成功返回1
	public int addReturnval(String returnvalName, String rClass);
	//查找Parameter
	public IReturnval getReturnval(String returnvalName);
	public IReturnval getReturnval(int returnvalNumber);
	//删除Parameter
	public int removeReturnval(String returnvalName);
}
