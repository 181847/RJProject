package rClassInterface;
import basicInterface.INameable;



public interface IRReference extends INameable{
	//将source引用的对象赋给调用者
	public int set(IRReference source);
	//调用者和source确认是基本数据类型，基本数据类型数值复制
	public void duplicateBasicData(IRReference source);
	//返回引用的datas数组
	public Object[] getObjects();
	//返回引用的引用类型
	public String getReferenceClass();
	//返回引用的具体的数据的类型
	public String getDataClass();
}
