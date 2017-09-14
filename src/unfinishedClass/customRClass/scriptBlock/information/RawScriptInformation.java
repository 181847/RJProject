package unfinishedClass.customRClass.scriptBlock.information;

public class RawScriptInformation implements Information {
	protected String scriptSourse;
	protected InformationType infoType;
	protected StringBuffer description;
	
	public RawScriptInformation(String source){
		scriptSourse = source;
		infoType = InformationType.VOID;
		description = new StringBuffer();
	}
	
	@Override
	public String toString(){
		return scriptSourse;
	}

	@Override
	public InformationType getType() {
		return infoType;
	}

	@Override
	public void setType(InformationType type) {
		infoType = type;
	}

	@Override
	public String getOriginalString() {
		return scriptSourse;
	}

	/**
	 * 返回信息描述，
	 * 返回的详细格式如下：<br>
	 * (<br>
	 * 第一次添加的描述<br>
	 * 第二次添加的描述<br>
	 * )<br>
	 */
	@Override
	public String getDescription() {
		return description.toString();
	}

	/**
	 * 以行为单位添加描述，
	 * 被添加的字符串的末尾将会自动加上一个换行符。
	 */
	@Override
	public void appendDescription(String addition) {
		description.append(addition + '\n');
	}
}
