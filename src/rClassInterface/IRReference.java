package rClassInterface;
import basicInterface.INameable;



public interface IRReference extends INameable{
	//将source引用的对象赋给调用者
	//这个方法主要用于Parameter和Returnval相互赋值时使用
	public int set(IRReference source);
	
	//返回引用的所属RClass的大致类型
	//1代表基本数据类型
	//2代表Java包装的RClass
	//3代表完全自定义的RClass
	public int getRoughType();
	
	//返回成员数量
	public int getMemberNum();
	
	//返回引用的datas数组
	public Object[] getObjects();
	
	//返回引用的引用类型
	public String getReferenceClass();
	
	//返回引用的具体的数据的类型
	public String getDataClass();
	
	//以Object形式返回第0号单元的引用
	//要求只适用于基本数据类型、Java包装类RClass及其子类RClass
	//任何错误发生时都会返回null
	public Object readObject();
	
	//向RReference中的第0号单元写入Object，同时注明data的RClass类型
	//用整型值返回写入的结果
	//这个方法并不常用，主要是在构造函数，或者创建一个新的RClass实例的时候使用
	//注意这个方法的使用者一定是一个基本数据类型，或者Java包装类
	//datas数组长度只有一位，写入Object时会重新申请一个长度为一的数组
	//然后将datas指向这个新的数组，以及将传入的参数data放进新数组的第0号单元
	//由于只适用于基本数据类型和Java包装类
	//要求dataClass必须和reference相同，否则返回0
	public int writeObject(Object data, String dataClass);
	
	//用一个Object数组存储一系列RReference
	//写入时会重新分配一段长度的Object数组
	//然后将参数中的引用复制到新的Object数组当中
	//datas指向新的数组
	public int writeRReference(Object[] rReferenceList, String dataClass);
	
	//获取名为memberName的RReference
	public IRReference Member(String memberName);
	
	//从指定的datas[]数组序号处获取RReference
	public IRReference getRReferenceInLocation(int location);
	
	//获取名为memberName的RReference在数组当中的序数
	public int locateRReferenceOf(String memberName);
	
	//移动名为memberName的RReference在datas[]数组当中的位置，
	//向前，或向后移动几位，返回最终移动到的位置
	public int moveRReferenceOf(String memberName, int step);
	
	
	/**
	 *为引用申请指定长度的Object[]数组
	 *如果分配失败就返回0
	 */
	public int mallocSpace(int space);
	 
	/**
	 *将datas数组当中的所有单元设为null
	 */
	public int freeSpace();
}
