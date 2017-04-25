package unfinishedClass.customRClass.scriptBlock.spider.infoSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * 收集普通Function的信息。
 */
public class FunInfoSpider extends FunAbstractInfoSpider {

	public FunInfoSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		functionStruct.setType(InformationType.FUN);
	}
}
