package unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct;

/**
 * 可收集多个接口RClass名字的集合，
 * 用来在RClassStructClass中作为接口的记录。
 */
public class ImplementSet {

	/**
	 * 添加接口信息。
	 * @param interfaceName
	 * 		接口RClass的名字。
	 */
	public void addInterface(String interfaceName) {
		addRClass(new RClassRefStruct(interfaceName));
	}

	/**
	 * 向集合中插入一个RClass引用信息。
	 * @param rClassRefStruct
	 * 		一个RClass引用信息。
	 */
	public void addRClass(RClassRefStruct rClassRefStruct) {
		// TODO Auto-generated method stub
		
	}
	
}
