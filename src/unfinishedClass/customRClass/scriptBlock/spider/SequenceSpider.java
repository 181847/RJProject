package unfinishedClass.customRClass.scriptBlock.spider;

import basicTool.RLogger;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

public class SequenceSpider extends AbstractBCSpider {

	public SequenceSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	protected void dealWithTargetBlock() {
		RLogger.log("CustomRClass脚本文件信息：" + targetBlock.getInformation().toString());
		if (targetBlock.getSub() != null){
			new ScriptSpider(targetBlock.getSub())
				.workUntilEnd();
		}
	}

}
