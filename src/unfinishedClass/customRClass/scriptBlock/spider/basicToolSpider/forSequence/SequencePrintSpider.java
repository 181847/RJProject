package unfinishedClass.customRClass.scriptBlock.spider.basicToolSpider.forSequence;

import basicTool.RLogger;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;
import unfinishedClass.customRClass.scriptBlock.spider.basicToolSpider.ScriptPrintSpider;

public class SequencePrintSpider extends AbstractBCSpider {

	public SequencePrintSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	protected void dealWithTargetBlock() {
		RLogger.log("CustomRClass脚本文件信息：" + targetBlock.getInformation().toString());
		if (targetBlock.getSub() != null){
			new ScriptPrintSpider(targetBlock.getSub())
				.workUntilEnd();
		}
	}

}
