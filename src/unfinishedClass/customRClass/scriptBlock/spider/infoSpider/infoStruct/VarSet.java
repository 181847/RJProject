package unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct;
/**
 * 纯粹收集多个变量的集合，
 * 这个类可以用于VarFieldStruct中作为静态变量的单独区域。
 */
public class VarSet {
	/**
	 * 添加一个变量信息，
	 * 例如“basic.Integer member3 -12”，
	 * 表示成员的名字为“member3”，<br>
	 * 类型为“basic.Integer”的整数类型，<br>
	 * 初始化为“-12”。
	 * 这个信息可以包括一些初始化的数据。
	 * @param varInfo
	 * 		变量信息。
	 */
	public void addVar(String varInfo) {
		addVar(new VarStruct(varInfo));
	}

	/**
	 * 添加一个变量信息结构。
	 * @param varStruct
	 * 		变量信息结构。
	 */
	public void addVar(VarStruct varStruct) {
		// TODO Auto-generated method stub
		
	}
}
