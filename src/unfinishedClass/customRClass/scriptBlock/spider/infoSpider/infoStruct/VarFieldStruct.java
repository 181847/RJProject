package unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct;

/**
 * 收集多个变量的信息，
 * 同时划分出一个单独的区域用来存储静态变量，
 * 这个类用来在RClassStruct中存储成员变量，
 * 以及在FunctionStruct中存储本地变量。
 */
public class VarFieldStruct extends VarSet{
	protected VarSet staticSet;

	/**
	 * 添加静态成员变量集合。
	 * @param varSet
	 * 		静态成员变量集合。
	 */
	public void addStaticSet(VarSet varSet) {
		staticSet = varSet;
	}

}
