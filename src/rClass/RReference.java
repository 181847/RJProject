package rClass;
import basicTool.NameableWithString;
import rClassInterface.IRReference;
import testSpace.RClassLoader;

public class RReference extends NameableWithString implements IRReference {
	//粗略类型标识
	//1至9代表基本数据类型
	//1-Byte
	//2-Boolean
	//3-Short
	//4-Integer
	//5-Long
	//6-Float
	//7-Double
	//8-Character
	//9-String
	//10以及10以上以上代表Java包装的RClass
	//负无穷到-1代表完全自定义的RClass
	//这一项在init()当中从RClassLoader中获取，且只与referenceClass有关
	public int roughType;
	//数据域
	public Object[] datas;
	//成员数量
	public int memberNum;
	//引用所属的RClass名称
	public String referenceClass;
	//数据所属的RClass名称
	public String dataClass;
	
	//构造方法，传入部分参数进行设置，然后调用init()方法设置memberNum\dataClass\datas
	public RReference(String referenceClass, String referenceName){
		setName(referenceName);
		this.referenceClass = referenceClass;
		init();
	}
	
	//初始化方法，根据rough\referenceClass
	//对这个RReference实例进行初始化设置
	public void init(){
		if ((roughType = RClassLoader.getRoughTypeOf(referenceClass)) >= 1 && roughType <= 9){
			memberNum = 1;
			mallocSpace(1);
			switch(roughType){
			case 1:
				writeObject(new Byte("0"), referenceClass);
				break;
			case 2:
				writeObject(new Boolean(false), referenceClass);
				break;
			case 3:
				writeObject(new Short((short)0), referenceClass);
				break;
			case 4:
				writeObject(new Integer(0), referenceClass);
				break;
			case 5:
				writeObject(new Long(0), referenceClass);
				break;
			case 6:
				writeObject(new Float(0), referenceClass);
				break;
			case 7:
				writeObject(new Double(0), referenceClass);
				break;
			case 8:
				writeObject(new Character('\0'), referenceClass);
				break;
			case 9:
				writeObject(new String(""), referenceClass);
				break;
			}
		}//if
		else{
			datas = null;
			dataClass = "";
		}
			
	}
	
	
	public int getMemberNum(){
		return memberNum;
	}
	
	//用另一个RReference来对本RReference进行设置
	//现阶段只考虑相同类型的基本数据类型之间进行设置
	//这里假设RReference的类型是匹配的，或者source为子类
	@Override
	public int set(IRReference source) {
		if (source == null)
			return 0;
		
		datas = source.getObjects();
		
		switch(roughType){
		//除非本RReference是基本数据类型
		//否则dataClass以及memberNum都要重新指定
		case 2:
		case 3:
			dataClass = source.getDataClass();
			memberNum = source.getMemberNum();
			break;
		default:
			return 0;	
		}
		return 1;
	}

		
	
	@Override
	public Object[] getObjects() {
		return datas;
	}

	@Override
	public String getReferenceClass() {
		return referenceClass;
	}

	@Override
	public String getDataClass() {
		return dataClass;
	}

	//读取出第0号单元位置的Object
	@Override
	public Object readObject() {
		//如果这个RReference属于完全自定义的RReference
		//则它的datas数组存储的都是RReference类型的对象
		//不允许用户像这样读取RReference的对象
		//这个方法的返回值一定是一个用户可以直接操作的Java对象
		//roughType
		//1代表基本数据类型
		//2代表Java包装的RClass
		//3代表完全自定义的RClass
		if (roughType < 0 || datas == null)
			return null;
		return datas[0];
	}

	@Override
	public IRReference Member(String memberName) {
		if (roughType >= 0 || datas == null)
			return null;
		
		for (int i = 0; i < datas.length; ++i){
			//这里的toString()函数在NameableWithString类当中重载
			//返回的是这个可命名实例的名字
			if (datas[i].toString().compareTo(memberName) == 0)
				return (IRReference)datas[i];
		}
		
		return null;
	}

	@Override
	public int locateRReferenceOf(String memberName) {
		if (roughType >=  0 || datas == null)
			return -1;
		
		for (int i = 0; i < datas.length; ++i){
			if (datas[i].toString().compareTo(memberName) == 0)
				return i;
		}
		return -1;
	}
	
	@Override
	public IRReference getRReferenceInLocation(int location) {
		//返回不成功的条件有
		//引用类型不是完全自定义的RClass
		//引用未能获得实例，datas仍然指向null
		//数组序号越界
		if (roughType >= 0 || datas == null || location < 0 || location >= datas.length)
			return null;
		return (IRReference)datas[location];
	}

	
	//注意：这个方法不应该在运行过程当中调用
	//它只在定义RClass类型当中的成员变量时会使用到
	//用于调整成员变量的顺序
	//无需顾虑效率
	@Override
	public int moveRReferenceOf(String memberName, int step) {
		int location = locateRReferenceOf(memberName);
		
		//没有找到可以移动的RReference
		if (location < 0 || step == 0)
			return 0;
		
		Object temp = datas[location];
		
		if (step > 0){
			while(step != 0 && location < datas.length){
				datas[location] = datas[location + 1];
				++location;
				--step;
			}
		}else{
			while(step != 0 && location > 0){
				datas[location] = datas[location - 1];
				--location;
				++step;
			}
		}
		
		datas[location] = temp;
		
		return location;
	}

	@Override
	public int getRoughType() {
		return roughType;
	}

	@Override
	public int writeObject(Object data, String dataClass) {
		//引用类型不是基本数据类型或者Java包装类
		//又或者dataClass与referenceClass不相等
		if (roughType <0 || RClassLoader.checkRClassMatchType(referenceClass, dataClass) > 2)
			return 0;
		
		this.dataClass = dataClass;
		datas[0] = data;
		return 1;
	}

	@Override
	public int writeRReference(Object[] rReferenceList, String dataClass) {
		// TODO Auto-generated method stub
		return 0;
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
		if (datas == null)
			return 0;
		for (int i = 0; i < datas.length; ++i)
			datas[i] = null;
		datas = null;
		return 1;
	}



	
}
