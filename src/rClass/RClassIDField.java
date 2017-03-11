package rClass;
import rClassInterface.IRClass;

/**
 * 本类用来存储RClass对象实例，并且为存储的RClass对象分配一个RClassID，
 * 针对Java定义的RClass（称为JarRClass）和用脚本定义的RClass（称为ZipRClass）
 * 正区域positiveField专门存储JarRClass，
 * 负区域用来存储ZipRClass。
 */
public class RClassIDField {
	/**
	 * 一次空间不足时应该增加的空间数量。
	 */
	public static int ensureCapacity = 100;
	
	/**
	 * 正区域，对应的RClassID全部为正数，
	 * 专门存储基本数据类型RClass以及Java包装类型RClass。
	 */
	IRClass[] positiveField;
	
	/**
	 * 正区域的能够使用的最大的RClassID，从1开始到正无穷。
	 */
	int positiveUseableID = 1;
	
	/**
	 * 负区域的能够使用的最大的RClassID，从-1开始到负无穷。
	 */
	int negativeUseableID = -1;
	
	/**
	 * 默认构造函数为正区域创建100个存储RClass的空间。
	 */
	public RClassIDField(){
		positiveField = new IRClass[1000];
	}
	
	/**
	 * 为IDFiled分配存储空间。
	 * @param positiveSpace 
	 * 		用于存储基本数据类型RClass以及Java包装类型RClass的空间数量。
	 * @param negativeSpace 
	 * 		用于存储完全自定义RClass的空间数量。
	 */
	public RClassIDField(int positiveSpace, int negativeSpace){
		positiveField = new IRClass[positiveSpace];
	}
	
	/**
	 * 这个方法专门用于注册基本数据类型RClass以及Java包装类型的RClass。
	 * @param rClassToRegist 要注册的RClass的Java实例。
	 * @return 加载成功的RClassID。
	 */
	public int registJarRClass(IRClass rClassToRegist){
		ensurePositiveFieldSpace();
		positiveField[positiveUseableID] = rClassToRegist;
		return positiveUseableID++;
	}
	
	/**
	 * 这个方法专门用于注册完全自定义RClass，
	 * 这个方法只是用来记录信息的，不会对rClassToRegist做任何修改，
	 * rClassToRegist内部相关成员数据和Function的初始化，请在方法外面完成。
	 * @param rClassToRegist 
	 * 		要注册的RClass的Java实例。
	 * @param fathers 
	 * 		这个RClass的父类ID，可以有多个，允许对接口多继承。
	 * @return 
	 * 		加载成功的RClassID。
	 */
	public int registZipRClass(IRClass rClassToRegist){
		//TODO
		return 0;
	}
	
	/**
	 * 确保用来注册IRClass的正区域空间足够，
	 * 如果正区域空间不够（positiveUseableID 到达 positiveField长度），
	 * 就要增加ensureCapacity个区域空间大小。
	 * @return 
	 * 		空间足够就返回一个正数，大小为positiveField长度；
	 * 		空间不足，就返回一个负数，大小为positiveField增长后的长度的负数。
	 */
	public int ensurePositiveFieldSpace(){
		if (positiveUseableID >= positiveField.length){
			resize(true);
			return -positiveField.length;
		}
		return positiveField.length;
	}
	
	/**
	 * 如果传入的参数为真，就扩容正区域部分，
	 * 如果传入的参数为假，就扩容负区域部分。
	 * 扩容的空间大小由ensureCapacity决定。
	 * @param resizePositiveField 
	 * 		是否扩容正区域部分。
	 */
	public void resize(boolean resizePositiveField){
		if (resizePositiveField){
			IRClass[] newField = new IRClass[positiveField.length + ensureCapacity];
			for (int i = positiveUseableID; i >= 0; --i){
				newField[i] = positiveField[i];
			}
			positiveField = newField;
			newField = null;
		}
	}
	
	/**
	 * 确保用来注册IRClass的负区域空间足够，
	 * 如果负区域空间不够，
	 * 就要增加ensureCapacity个区域空间大小。
	 * @return
	 */
	public int ensureNegativeFieldSpace(){
		//TODO
		return 0;
		
	}
	
	/**
	 * 通过RClassID获得RClass，目前只考虑正区域内注册的RClass，如果ID不再注册范围内
	 * （小于等于negativeUseableID；或大于等于positiveUseableID；或等于0），返回null。
	 * @param rClassID 
	 * 		RClass注册得到的ID。
	 * @return 
	 * 		指定ID的RClass，如果没有则返回null。
	 */
	public IRClass getRClass(int rClassID){
		if (rClassID <= negativeUseableID || rClassID == 0 || rClassID >= positiveUseableID){
			return null;
		} else if (rClassID > 0) {
			return positiveField[rClassID];
		}
		return null;
	}
}
