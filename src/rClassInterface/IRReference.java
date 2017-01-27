package rClassInterface;
import basicInterface.INameable;



public interface IRReference extends INameable{
	//将source引用的对象赋给调用者
	public int set(IRReference source);
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
	public int writeObject(Object data, String dataClass);
}
