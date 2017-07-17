package unfinishedClass.customRClass.rStruct;

import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct;
import unfinishedClass.customRClass.rStruct.detailInterface.ITextStruct;

/**
 * 一个纯文本记录结构，
 * 这个结构用来存储例如注释、
 * 或者Function的修改信息等一些纯文字信息。
 */
public class TextStruct implements IRStruct, ITextStruct {
	/**
	 * 文本缓存区。
	 */
	protected StringBuffer textBuffer;
	
	public TextStruct() {
		textBuffer = new StringBuffer();
	}
	
	@Override
	public void appendText(String lineSting) {
		textBuffer.append(lineSting + '\n');
	}

	@Override
	public String getAllText() {
		return textBuffer.toString();
	}

}
