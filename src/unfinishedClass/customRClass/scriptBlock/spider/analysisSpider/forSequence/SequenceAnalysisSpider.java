package unfinishedClass.customRClass.scriptBlock.spider.analysisSpider.forSequence;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;
import unfinishedClass.customRClass.scriptBlock.spider.analysisSpider.ScriptAnalysisSpider;

/**
 * 为加载序列中每个脚本中的脚本信息设置声明信息的枚举成员，
 * 方便后续的语法检查来区分不同的声明。
 */
public class SequenceAnalysisSpider extends AbstractBCSpider {

	public SequenceAnalysisSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	protected void dealWithTargetBlock() {
		ScriptBlock subBlock = targetBlock.getSub();
		
		if (subBlock != null){
			new ScriptAnalysisSpider(subBlock)
				.workUntilEnd();
		}
	}

}
