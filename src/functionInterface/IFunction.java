package functionInterface;


public interface IFunction{
	//运行，执行Function的运算功能
	void run(int paragraph);
	//对于CustomFunction才使用到的功能
	//填充Function内部的其他Function
	void fillContentGraph();
	//清空Function当中的临时数据、临时Function的引用
	void clearGraph();
	
	//是否需要参数
	boolean needParameter();
	
	//添加Excutee，用int返回添加的结果，成功返回1
	int addExcutee(String excuteeName, int paragraphToFire, int nextExcuterInRearSlot);
	//查找Excutee
	IExcutee getExcutee(String excuteeName);
	IExcutee getExcutee(int excuteeNumber);
	//删除Excutee
	int removeExcutee(String excuteeName);
	
	//添加Excuter，用int返回添加的结果，成功返回1
	int addExcuter(String excuterName);
	//查找Excuter
	IExcuter getExcuter(String excuterName);
	IExcuter getExcuter(int excuterNumber);
	//删除Excuter
	int removeExcuter(String excuterName);
	
	//添加Parameter，用int返回添加的结果，成功返回1
	int addParameter(String ParameterName, String rClass);
	//查找Parameter
	IParameter getParameter(String parameterName);
	IParameter getParameter(int parameterNumber);
	//删除Parameter
	int removeParameter(String parameterName);
	
	//添加Returnval，用int返回添加的结果，成功返回1
	int addReturnval(String returnvalName, String rClass);
	//查找Parameter
	IReturnval getReturnval(String returnvalName);
	IReturnval getReturnval(int returnvalNumber);
	//删除Parameter
	int removeReturnval(String returnvalName);
	

}
