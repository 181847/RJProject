package unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct;

/**
 * 收集Function的Parameter组件信息。
 */
public class ParameterSet {

	/**
	 * 添加一个Parameter组件的信息，，
	 * 形如“basic.Integer parameter2”，<br>
	 * “basic.Integer” ---- 组件的类型，<br>
	 * “parameter2” ------- 组件的名称。
	 * @param parameterInfo
	 * 		一个Parameter组件的信息。
	 */
	public void addParameter(String parameterInfo) {
		addParameter(new ParameterStruct(parameterInfo));
	}

	public void addParameter(ParameterStruct parameterStruct) {
		// TODO Auto-generated method stub
		
	}
}
