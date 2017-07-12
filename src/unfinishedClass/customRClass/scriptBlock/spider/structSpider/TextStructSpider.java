package unfinishedClass.customRClass.scriptBlock.spider.structSpider;

import unfinishedClass.customRClass.rStruct.IRStruct;
import unfinishedClass.customRClass.rStruct.TextStruct;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

/**
 * 纯粹用于收集文本信息的Spider。
 */
public class TextStructSpider extends UtilsRStructSpider_with_RStruct<TextStruct> {

	public TextStructSpider() {
		finalRStruct = new TextStruct();
	}
	
	public TextStructSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		finalRStruct = new TextStruct();
	}
	
	@Override
	public void countWork() {
		finalRStruct.appendText(targetInfoString);
	}

}
