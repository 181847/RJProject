package unfinishedClass.customRClass.scriptBlock.spider.forSequence;

import unfinishedClass.customRClass.scriptBlock.RClassStructHelper;
import unfinishedClass.customRClass.scriptBlock.RSTestResult;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.RClassStructInformation;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;
import unfinishedClass.customRClass.scriptBlock.spider.basicToolSpider.DeleteSpider;
import unfinishedClass.customRClass.struct.RClassStruct;

public class SequenceContentMatchSpider extends DeleteSpider {

	public SequenceContentMatchSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	protected void dealWithTargetBlock() {
		RClassStruct rClassStruct = 
				( (RClassStructInformation)targetBlock.getInformation() )
				.getRClassStruct();
		
		/**
		 * 分析RClassStruct内部的各种信息约束是否正确。
		 */
		RSTestResult result = 
				RClassStructHelper.testConstraintOn(rClassStruct);
		
		if (result.hasError()){
			this.deleteTarget = true;
			this.appendReason(result.getErrorReason());
		}
	}

}
