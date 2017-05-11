package unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct.struct;

/**
 * 纯粹收集多个变量的集合，
 * 这个类可以用于VarFieldStruct中作为静态变量的单独区域。
 */
public class VarSet extends Set{
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
		append(new VarStruct(varInfo));
	}

	/**
	 * 获取集合内部包含的变量数量。
	 * @return
	 * 		包含的变量的数量。
	 */
	public int getNum() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 获取指定序号的变量结构。
	 * @param memberNum
	 * 		想要获取的变量的序号。
	 * @return
	 * 		包含变量信息的变量结构。
	 */
	public VarStruct getVar(int memberNum) {
		// TODO Auto-generated method stub
		return null;
	}
}
