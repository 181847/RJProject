package rClassInterface;
import basicInterface.INameable;
import basicInterface.IRClassIDHolder;



public interface IRReference extends INameable,IRClassIDHolder{
	/**
	 * 将source引用的对象赋给调用者，
	 * 这个方法主要用于Parameter和Returnval相互赋值时使用
	 * @param source 赋值时，数据的来源
	 * @return 成功返回1，
	 * source为null返回0，
	 * 类型不匹配返回-1。
	 */
	public int set(IRReference source);
	
	/**
	 * 返回成员数量
	 * @return 成员数量
	 */
	public int getMemberNum();
	
	/**
	 * 返回引用的datas数组
	 * @return 引用的数据数组
	 */
	public Object[] getObjects();
	
	/**
	 * 返回引用的引用类型名称
	 * @return 引用所属的RClass的类型名称
	 */
	public String getReferenceRClass();
	
	/**
	 * 返回引用的具体的数据的类型RClassID
	 * @return 数据所属的RClass的RClassID
	 */
	public int getDataRClassID();
	
	/**
	 * 以Object形式返回第0号单元的引用
	 * 要求只适用于基本数据类型、Java包装类RClass
	 * 任何错误发生时都会返回null
	 * @return
	 */
	public Object readObject();
	
	/**
	 * 向RReference中的第0号单元写入Object，同时注明data的RClass类型
	 * 用整型值返回写入的结果，
	 * 这个方法主要是在构造函数，或者创建一个新的RClass实例的时候使用，
	 * 注意这个方法的使用者一定是一个基本数据类型，或者Java包装类，
	 * 将传入的参数data放进新数组的第0号单元，dataClass变为指定的参数，
	 * 在这个方法中会对RReference中的dataRClassID进行设置。
	 * @param data 要写入RReference的数据
	 * @param dataClass 指定数据的dataClass，
	 * 要求dataClass必须和referenceClass相同，
	 * 否则写入失败。
	 * @return 写入失败返回0，写入成功返回1
	 */
	public int writeObject(Object data, String dataClass);
	
	/**
	 * 本方法专门用于向完全自定义RClass的RReference中写入数据，
	 * 完全自定义RClass的RReference的datas数组存储的都是RReference类，
	 * 调用此方法前需保证调用者当中的datas所指向的数组长度与传入Object数组的长度相同，
	 * 然后依次复制参数数组的值到datas数组当中，
	 * 在这个方法中会对RReference中的dataRClassID进行设置。
	 * @param rReferenceList RReference列表。
	 * @param dataClass 指定的dataClass名字。
	 * @return 写入成功返回1，失败返回0。
	 */
	public int writeRReference(Object[] rReferenceList, String dataRClass);
	
	/**
	 * 专门用于完全自定义RClass的方法，
	 * 获取名为memberName的子成员RReference。
	 * @param memberName 子成员的名字。
	 * @return 子成员RReference。
	 */
	public IRReference Member(String memberName);
	
	/**
	 * 专门用于完全自定义RClass的方法，
	 * 从指定的datas[]数组序号处获取子成员RReference
	 * @param memberIndex 数组下标
	 * @return 子成员RReference
	 */
	public IRReference getMemberInLocation(int memberIndex);
	
	/**
	 * 获取名为memberName的子成员RReference在数组当中的序号。
	 * @param memberName 子成员的名字。
	 * @return 子成员的序号
	 */
	public int locateMemberOf(String memberName);
	
	/**
	 * 移动名为memberName的RReference在datas[]数组当中的位置，
	 * 向前，或向后移动几位，返回最终移动到的位置
	 * @param memberName 要移动的子成员的名字。
	 * @param step 移动的步数，
	 * 负数表示向0号单元移动，
	 * 正数表示想数组的尾部移动，
	 * 0表示不动。
	 * @return 如果不存在相应的子成员，返回-1；
	 * 否则返回子成员移动之后的位置
	 */
	public int moveMemberOf(String memberName, int step);
	
	
	/**
	 * 为RReference的datas数组申请指定长度的Object[]数组，
	 * 如果分配失败就返回0。
	 * @param space 要分配的Object数组长度。
	 * @return 如果成功分配就返回1，失败返回0。
	 */
	public int mallocSpace(int space);
	 
	/**
	 *将datas数组当中的所有单元设为null
	 */
	public int freeSpace();
}
