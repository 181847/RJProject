package testSpace;
import rClassInterface.IRReference;

public class RReference extends NameableWithString implements IRReference {
	//是否是Java包装类型的引用
	public boolean isAtom;
	//数据域
	public Object[] datas;
	//成员数量
	public int memberNum;
	//引用所属的RClass名称
	public String referenceClass;
	//数据所属的RClass名称
	public String dataClass;
	
	//构造方法，传入部分参数进行设置，然后调用init()方法设置memberNum\dataClass\datas
	public RReference(String referenceName, boolean isAtom, String referenceClass){
		setName(referenceName);
		this.isAtom = isAtom;
		this.referenceClass = referenceClass;
		init();
	}
	
	//初始化方法，根据isAtom\referenceClass
	//对这个RReference实例进行初始化设置
	public void init(){
		if (RClassLoader.getTypeOf(referenceClass)==0){
			dataClass = referenceClass;
			datas = new Object[1];
			memberNum = 1;
			try{
				switch(referenceClass.charAt(0))
				{
				case 'B': 
					switch(referenceClass.charAt(1)){
					case 'y':
						//对应Byte类型
						datas[0] = new Byte("0");
						break;
						
					case 'o':
						//对应Boolean类型
						datas[0] = new Boolean(false);
						break;
					}
					break;
					
				case 'S':
					//对应Short类型
					datas[0] = new Short((short)0);
					break;
					
				case 'I':
					//对应Integer类型
					datas[0] = new Integer(0);
					break;
				
				case 'L':
					//对应Long类型
					datas[0] = new Long(0);
					break;
					
				case 'F':
					//对应Float类型
					datas[0] = new Float(0);
					break;
					
				case 'D':
					//对应Double类型
					datas[0] = new Double(0);
					break;
					
				case 'C':
					//对应Character类型
					datas[0] = new Character('\0');
					break;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else{
			datas = null;
			dataClass = "";
		}
			
	}
	
	
	//用另一个RReference来对本RReference进行设置
	//基本数据类型按值设置
	//其他类型按引用设置
	//现阶段只考虑相同类型的基本数据类型之间进行设置
	@Override
	public int set(IRReference source) {
		if (source == null)
			return 0;
		switch(RClassLoader.checkRClassMatchType(referenceClass, source.getDataClass()))
		{
		case 1://同一类型，基本数据类型
			duplicateBasicData(source);
			break;
		case 2://同一类型，普通类型，包括原子非基本数据类型
			datas = source.getObjects();
			break;
		case 3:
			//不同类型，source为子类，
			//如果到此，必定不是Java包装类，也不是基本数据类型
			datas = source.getObjects();
			dataClass = source.getDataClass();
			break;
		default:
			return 0;
		}
		return 1;
	}

	@Override
	public void duplicateBasicData(IRReference source) {
		switch(referenceClass.charAt(0))
		{
			case 'B': 
				switch(referenceClass.charAt(1)){
					case 'y':
						//对应Byte类型
						datas[0] = new Byte(source.getObjects()[0]);
						break;

					case 'o':
						//对应Boolean类型
						datas[0] = new Boolean(source.getObjects()[0]);
						break;
				}
				break;

			case 'S':
				//对应Short类型
				datas[0] = new Short(source.getObjects()[0]);
				break;

			case 'I':
				//对应Integer类型
				datas[0] = new Integer(source.getObjects()[0]);
				break;

			case 'L':
				//对应Long类型
				datas[0] = new Long(source.getObjects()[0]);
				break;

			case 'F':
				//对应Float类型
				datas[0] = new Float(source.getObjects()[0]);
				break;

			case 'D':
				//对应Double类型
				datas[0] = new Double(source.getObjects()[0]);
				break;

			case 'C':
				//对应Character类型
				datas[0] = new Character(source.getObjects()[0]);
				break;
		}
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
}
