package functionInterface;


public interface IFunction extends IFunctionHeadSlot,IFunctionRearSlot{
	//运行，执行Function的运算功能
	//并返回接下来要使用的
	public IExcuter run(int paragraph);
	//对于CustomFunction才使用到的功能
	//填充Function内部的其他Function
	public void fillContentGraph();
	//清空Function当中的临时数据、临时Function的引用
	public void clearGraph();
	
	//是否需要参数
	public boolean needParameter();
	
	
	//得到节点前方插口引用
	public IFunctionHeadSlot getHeadSlot();
	//得到节点后方插口引用
	public IFunctionRearSlot getRearSlot();
}
