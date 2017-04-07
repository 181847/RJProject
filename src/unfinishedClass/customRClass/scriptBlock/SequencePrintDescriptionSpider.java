package unfinishedClass.customRClass.scriptBlock;

import basicTool.RLogger;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;
import unfinishedClass.customRClass.scriptBlock.spider.ScriptPrintSpider;

public class SequencePrintDescriptionSpider extends AbstractBCSpider {

	public SequencePrintDescriptionSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	protected void dealWithTargetBlock() {
		RLogger.log("CustomRClass脚本文件信息：" + targetBlock.getInformation().toString());
		if (targetBlock.getSub() != null){
			new ScriptPrintDescriptionSpider(targetBlock.getSub())
				.workUntilEnd();
		}
	}

}
