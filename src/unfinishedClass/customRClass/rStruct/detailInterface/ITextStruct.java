package unfinishedClass.customRClass.rStruct.detailInterface;

/**
 * 包含文本信息的Struct。
 */
public interface ITextStruct {
	/**
	 * 添加一行信息，添加的时候回强制在末尾增加一个换行符。
	 * @param lineSting
	 * 		一行文本信息。
	 */
	public void appendText(String lineSting);
	
	/**
	 * 获取所有信息。
	 * @return
	 * 		所有信息的集合。
	 */
	public String getAllText();
}
