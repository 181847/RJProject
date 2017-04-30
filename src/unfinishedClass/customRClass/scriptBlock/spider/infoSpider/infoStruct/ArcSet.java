package unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct;

/**
 * 弧线连接集合。
 */
public class ArcSet {

	/**
	 * 添加弧线信息。
	 * @param arcInfo
	 * 		弧线信息。
	 */
	public void addArc(String arcInfo) {
		append(new ArcStruct(arcInfo));
	}
}
