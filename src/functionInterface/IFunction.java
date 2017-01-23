package functionInterface;


public interface IFunction{
	//运行，执行Function的运算功能
	//并返回接下来要使用的Excuter
	public IExcuter run(int paragraph);
	//对于CustomFunction才使用到的功能
	//填充Function内部的其他Function
	public void fillContentGraph();
	//清空Function当中的临时数据、临时Function的引用
	public void clearGraph();
	
	//是否需要参数
	public boolean needParameter();
	
	//添加Excutee，用int返回添加的结果，成功返回1
	public int addExcutee(String excuteeName, int paragraphToFire, int nextExcuterInRearSlot);
	//查找Excutee
	public IExcutee getExcutee(String excuteeName);
	public IExcutee getExcutee(int excuteeNumber);
	//删除Excutee
	public int removeExcutee(String excuteeName);
	
	//添加Excuter，用int返回添加的结果，成功返回1
	public int addExcuter(String excuterName);
	//查找Excuter
	public IExcuter getExcuter(String excuterName);
	public IExcuter getExcuter(int excuterNumber);
	//删除Excuter
	public int removeExcuter(String excuterName);
	
	//添加Parameter，用int返回添加的结果，成功返回1
	public int addParameter(String ParameterName, String rClass);
	//查找Parameter
	public IParameter getParameter(String parameterName);
	public IParameter getParameter(int parameterNumber);
	//删除Parameter
	public int removeParameter(String parameterName);
	
	//添加Returnval，用int返回添加的结果，成功返回1
	public int addReturnval(String returnvalName, String rClass);
	//查找Parameter
	public IReturnval getReturnval(String returnvalName);
	public IReturnval getReturnval(int returnvalNumber);
	//删除Parameter
	public int removeReturnval(String returnvalName);
	
	//得到Function的参数列表
	public IParameterList getParameterList()
}
