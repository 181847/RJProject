package rClassInterface;



public interface IRReference{
	//将source引用的对象赋给调用者
	public boolean set(IRReference source);
	//调用者和source确认是基本数据类型，基本数据类型数值复制
	public void duplicateBasicData(IRReference source);
	//返回引用的datas数组
	public Object[] getObjects();
}
