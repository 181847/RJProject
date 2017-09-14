package unfinishedClass.customRClass.rStruct.detailInterface;

/**
 * 包含文本信息的Struct。
 */
public interface ITextStruct {
	/**
	 * 如果当前文本中没有信息，
	 * 就只是将当前信息添加到对象中，
	 * 如果文本中已有信息，就要先添加一个换行符，
	 * 然后再添加上参数中的字符串。
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
