package rClass;
import basicTool.NameableWithString;
import rClassInterface.IRReference;

public class RReference extends NameableWithString implements IRReference {
	/**
	 * 粗略类型标识，
	 * 1至9代表基本数据类型：
	 * 1-Byte；
	 * 2-Boolean；
	 * 3-Short；
	 * 4-Integer；
	 * 5-Long；
	 * 6-Float；
	 * 7-Double；
	 * 8-Character；
	 * 9-String；
	 * 10以及10以上代表Java包装的RClass；
	 * 负无穷到-1代表完全自定义的RClass；
	 * 这一项在init()当中从RClassLoader中获取，且只与referenceRClass有关。
	 */
	public int rClassID;
	
	/**
	 * 引用所属的RClass名称。
	 */
	public String referenceRClass;
	
	/**
	 * 数据所属的RClass的RClassID，
	 * 这一项只能在writeObject()，或者writeRReference()中设置
	 */
	public int dataRClassID;
	
	/**
	 * 实际的数据域。
	 */
	public Object[] datas;
	
	/**
	 * 成员数量。
	 */
	public int memberNum;
	
	/**
	 * 构造一个完全空白的RReference。
	 */
	public RReference(){
		//Empty Body
	}
	
	/**
	 * 通常在代码级别创建Java包装类的实例RReference的时候进行调用，
	 * 由程序猿完全掌控其中的内容。
	 * @param referenceRClassID
	 * 		引用的数据所属的RClass的ID。
	 * @param referenceRClass
	 * 		引用数据的RClass的名字。
	 * @param name
	 * 		引用自身的名字。
	 * @param datas
	 * 		引用的数据区域。
	 * @param memberNum
	 * 		成员数量。
	 * @param dataRClassID
	 * 		引用的数据所属的RClassID。
	 */
	public RReference(int referenceRClassID, String referenceRClass, String name, 
			Object[] datas, int memberNum,  int dataRClassID){
		super(name);
		this.rClassID = referenceRClassID;
		this.referenceRClass = referenceRClass;
		this.datas = datas;
		this.memberNum = memberNum;
		this.dataRClassID = dataRClassID;
	}
	
	/**
	 * 构造方法，传入部分参数进行设置，然后调用init()方法设置memberNum\dataClass\datas。
	 * @param referenceRClass 
	 * 		引用所属的RClass类型。
	 * @param referenceName
	 * 		引用的名字。
	 */
	public RReference(String referenceRClass, String referenceName){
		setName(referenceName);
		this.referenceRClass = referenceRClass;
		init();
	}
	
	/**
	 * 初始化方法，根据referenceRClass
	 * 对这个RReference实例进行初始化设置，
	 * 包括根据RClass的名字来设置rClassID，
	 * 如果是基本数据类型，
	 * 还要为引用分配一个默认的数据。
	 */
	public void init(){
		rClassID =	 RClassLoaderManager.getRClassLoader()
										.getRClassIDOf(referenceRClass);
		if (rClassID >= 1 && rClassID <= 9){
			memberNum = 1;
			mallocSpace(1);
			
			switch(rClassID){
			case 1:
				writeObject(new Byte("0"), referenceRClass);
				break;
			case 2:
				writeObject(new Boolean(false), referenceRClass);
				break;
			case 3:
				writeObject(new Short((short)0), referenceRClass);
				break;
			case 4:
				writeObject(new Integer(0), referenceRClass);
				break;
			case 5:
				writeObject(new Long(0), referenceRClass);
				break;
			case 6:
				writeObject(new Float(0), referenceRClass);
				break;
			case 7:
				writeObject(new Double(0), referenceRClass);
				break;
			case 8:
				writeObject(new Character('\0'), referenceRClass);
				break;
			case 9:
				writeObject(new String(""), referenceRClass);
				break;
			}
		} else {
			datas = null;
			dataRClassID = 0;
		}
			
	}
	
	/**
	 * 将source引用的对象赋给调用者，
	 * 这个方法主要用于Parameter和Returnval相互赋值时使用
	 * @param source 
	 * 		赋值时，数据的来源
	 * @return 
	 * 		成功返回1，
	 * 		source为null返回0，
	 * 		类型不匹配返回-1。
	 */
	@Override
	public int set(IRReference source) {
		if (source == null){
			return 0;
		}
		datas = source.getObjects();
		
		//除非本RReference是基本数据类型
		//否则dataClass以及smemberNum都要重新指定
		if (rClassID <= -1 || rClassID >= 10){
			dataRClassID = source.getDataRClassID();
			memberNum = source.getMemberNum();
		}
		return 1;
	}
	
	/**
	 * 获取引用本身所属的RClassID。
	 */
	@Override
	public int getRClassID() {
		return rClassID;
	}
	
	/**
	 * 不实现这个方法，RClassID不能由外部设置，
	 * 在init()方法中就会设置好RClassID。
	 */
	@Override
	public void setRClassID(int rClassID){
		//Empty Body
	}
	
	/**
	 * 返回成员数量。
	 * @return 
	 * 		成员数量。
	 */
	@Override
	public int getMemberNum(){
		return memberNum;
	}
	
	/**
	 * 返回引用的datas数组。
	 * @return
	 * 		引用的数据数组。
	 */
	@Override
	public Object[] getObjects() {
		return datas;
	}

	/**
	 * 返回引用的引用类型名称
	 * @return 
	 * 		引用所属的RClass的类型名称
	 */
	@Override
	public String getReferenceRClass() {
		return referenceRClass;
	}

	/**
	 * 返回引用的具体的数据的类型RClassID
	 * @return 
	 * 		数据所属的RClass的RClassID
	 */
	@Override
	public int getDataRClassID() {
		return dataRClassID;
	}

	/**
	 * 以Object形式返回第0号单元的引用
	 * 要求只适用于Java包装类，
	 * 如果不是Java包装类，或者datas数组为空，
	 * 返回null。
	 * @return 
	 * 		Object对象
	 */
	@Override
	public Object readObject() {
		if (rClassID <= -1 || datas == null){
			return null;
		}
		return datas[0];
	}
	
	/**
	 * 向RReference中的第0号单元写入Object，同时注明data的RClass类型
	 * 用整型值返回写入的结果，
	 * 这个方法主要是在构造函数，或者创建一个新的RClass实例的时候使用，
	 * 注意这个方法的使用者一定是一个基本数据类型，或者Java包装类，
	 * 将传入的参数data放进新数组的第0号单元，dataClass变为指定的参数。
	 * @param data 
	 * 		要写入RReference的数据
	 * @param dataClass 
	 * 		指定数据的dataClass，
	 * 		要求dataClass必须和referenceRClass相同，
	 * 		否则写入失败。
	 * @return 
	 * 		写入失败返回0，写入成功返回1
	 */
	@Override
	public int writeObject(Object data, String dataClass) {
		//引用类型不是基本数据类型或者Java包装类
		//又或者dataClass与referenceRClass不相等
		if (rClassID <0 ||
				RClassLoaderManager
					.getRClassLoader()
					.checkRClassMatchType(referenceRClass, dataClass)
					> 2){
			return 0;
		}
		this.dataRClassID = RClassLoaderManager.getRClassLoader().getRClassIDOf(dataClass);
		datas[0] = data;
		return 1;
	}
	
	/**
	 * 本方法专门用于向完全自定义RClass的RReference中写入数据，
	 * 完全自定义RClass的RReference的datas数组存储的都是RReference类，
	 * 调用此方法前需保证调用者当中的datas所指向的数组长度与传入Object数组的长度相同，
	 * 然后依次复制参数数组的值到datas数组当中。
	 * @param rReferenceList 
	 * 		RReference列表。
	 * @param dataClass 
	 * 		指定的dataClass名字。
	 * @return 
	 * 		写入成功返回1，失败返回0。
	 */
	@Override
	public int writeRReference(Object[] rReferenceList, String dataClass) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 仅针对CustomRClass类型的实例，
	 * 获取这个实例中的特定名字的 子RReference 的方法。
	 * @param memberName
	 * 		子RReference的名字。
	 * @return 
	 * 		如果这个引用所属的RClassID>=0，
	 * 		或者这个引用并没有被分配RClass实例，
	 * 		或者不存在指定名字的子RReference对象，
	 * 		返回null；
	 * 		否则返回指定名字的RReference。
	 */
	@Override
	public IRReference Member(String memberName) {
		if (rClassID >= 0 || datas == null){
			return null;
		}
		
		//这里的toString()函数在NameableWithString类当中重载
		//返回的是这个可命名实例的名字
		for (int i = 0; i < datas.length; ++i){
			if (datas[i].toString().compareTo(memberName) == 0){
				return (IRReference)datas[i];
			}
		}
		return null;
	}

	/**
	 * 专门用于完全自定义RClass的方法，
	 * 从指定的datas[]数组序号处获取子成员RReference
	 * @param location 
	 * 		数组下标。
	 * @return 
	 * 		如果loacation超出datas的长度范围，
	 * 		或者这个引用所属的RClassID>=0，
	 * 		返回null；
	 * 		否则，返回子成员RReference。
	 */
	@Override
	public IRReference getMemberInLocation(int location) {
		if (rClassID >= 0 || datas == null || 
				location < 0 || location >= datas.length){
			return null;
		}
		return (IRReference)datas[location];
	}
	
	/**
	 * 获取名为memberName的子成员RReference在数组当中的序号。
	 * @param memberName 子成员的名字。
	 * @return 子成员的序号
	 */	
	@Override
	public int locateMemberOf(String memberName) {
		if (rClassID >=  0 || datas == null){
			return -1;
		}
		
		for (int i = 0; i < datas.length; ++i){
			if (datas[i].toString().compareTo(memberName) == 0){
				return i;
			}
		}
		return -1;
	}
	
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
	@Override
	public int moveMemberOf(String memberName, int step) {
		int location = locateMemberOf(memberName);
		
		//没有找到可以移动的RReference
		if (location < 0){
			return -1;
		}
		if (step == 0){
			return location;
		}
		
		Object temp = datas[location];
		if (step > 0){
			while (step != 0 && location < datas.length){
				datas[location] = datas[location + 1];
				++location;
				--step;
			}
		} else {
			while (step != 0 && location > 0){
				datas[location] = datas[location - 1];
				--location;
				++step;
			}
		}
		datas[location] = temp;
		return location;
	}
	
	/**
	 *为引用申请指定长度的Object[]数组
	 *如果分配失败就返回0
	 */
	@Override
	public int mallocSpace(int space)
	{
		datas = new Object[space];
		return 1;
	}

	
	/**
	 *将datas数组当中的所有单元设为null
	 *以及datas设为null
	 */
	@Override
	public int freeSpace()
	{
		if (datas == null){
			return 0;
		}
		for (int i = 0; i < datas.length; ++i){
			datas[i] = null;
		}
		
		datas = null;
		return 1;
	}



	
}
