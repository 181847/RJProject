package unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct;
/**
 * 收集Function的Returnval组件信息。
 */
public class ReturnvalSet extends Set{
	/**
	 * 添加一个Returnval组件的信息，，
	 * 形如“basic.Integer parameter2”，<br>
	 * “basic.Integer” ---- 组件的类型，<br>
	 * “parameter2” ------- 组件的名称。
	 * @param returnvalInfo
	 * 		一个Returnval组件的信息。
	 */
	public void addReturnval(String returnvalInfo) {
		append(new ReturnvalStruct(returnvalInfo));
	}
}
