package unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct.struct;

/**
 * 本类用于保存一个变量的信息，
 * 包括变量的类型，
 * 变量的名字，
 * 对于基本数据类型（例如整形，Boolean）还会保存初始化值。
 */
public class VarStruct extends Struct {

	/**
	 * 按照字符串中的信息来构造变量结构，
	 * 字符串的格式形如“basic.Integer member3 -12”，其中：<br>
	 * “basic.Integer”表示变量的类型，<br>
	 * “member3”表示变量的名字，<br>
	 * “-12”表示变量的初始化值，
	 * 因为这个变量的类型是整形，
	 * 所以可以有初始值。
	 * @param varInfo
	 * 		变量信息。
	 */
	public VarStruct(String varInfo) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 获取变量的名字，
	 * 注意：不是变量的类型的名字，
	 * 是变量的名字，
	 * 假如变量信息是“basic.Integer localVar3 = 12”，
	 * 则返回的字符串就是“localVar3”。
	 * @return
	 * 		变量的名字。
	 */
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
