package unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct;
/**
 * 收集Function的子Funtion信息。
 */
public class SubFunSet extends Set{

	/**
	 * 添加子Function信息，
	 * 例如“(-4,-7)basic.Integer.addInteger{3}”。
	 * @param originalString
	 * 		子Function信息。
	 */
	public void addSubFun(String subFunInfo) {
		append(new SubFunStruct(subFunInfo));
	}
}
