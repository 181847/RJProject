package unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct;
/**
 * 收集Function的子Funtion信息。
 */
public class SubFunSet {

	/**
	 * 添加子Function信息，
	 * 例如“(-4,-7)basic.Integer.addInteger{3}”。
	 * @param originalString
	 * 		子Function信息。
	 */
	public void addSubFun(String subFunInfo) {
		addSubFun(new SubFunStruct(subFunInfo));
	}

	public void addSubFun(SubFunStruct subFunStruct) {
		// TODO Auto-generated method stub
		
	}

}
