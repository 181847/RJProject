package unfinishedClass.customRClass.struct;

/**
 * 本类作为存储RClass信息的结构体，
 * 一般用在RClassStruct的内部专门用于
 * 引用一些父类或者接口的RClass信息。
 */
public class RClassRefStruct extends Struct{
	
	/**
	 * 设置RClass引用的名字。
	 * @param rClassName
	 * 		RClass的名字。
	 */
	public RClassRefStruct(String rClassName) {
		super(rClassName);
	}
	
}