package unfinishedClass.customRClass.struct;

/**
 * 本类用于保存一个变量的信息，
 * 包括变量的类型，
 * 变量的名字，
 * 对于基本数据类型（例如整形，Boolean）还会保存初始化值。
 */
public class VarStruct extends Struct {
	//注意：变量的名字由Struct中的name成员存储。
	
	/**
	 * 变量类型
	 */
	protected RClassRefStruct varRClassType;
	
	/**
	 * 初始化数据信息。
	 */
	protected String initData;

	/**
	 * 按照字符串中的信息来构造变量结构，
	 * 字符串的格式形如“basic.Integer member3 -12”，其中：<br>
	 * “basic.Integer”表示变量的类型，<br>
	 * “member3”表示变量的名字，<br>
	 * “-12”表示变量的初始化值，
	 * 因为这个变量的类型是整形，
	 * 所以可以有初始值，
	 * 初始化过程中假定信息是符合格式规范的，
	 * （两个或者三个空格）
	 * 否则可能发生异常。
	 * @param varInfo
	 * 		变量信息。
	 */
	public VarStruct(String varInfo) {
		//首先将变量信息按照空格来分段。
		String[] infoArray = varInfo.split(" ");
		
		setName(infoArray[0]);
		varRClassType = new RClassRefStruct(infoArray[1]);
		
		if (infoArray.length >= 3){
			initData = infoArray[2];
		}
	}
}
