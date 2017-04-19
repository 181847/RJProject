package unfinishedClass.customRClass.scriptBlock.spider.basicToolSpider.forSequence;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;
import unfinishedClass.customRClass.scriptBlock.spider.basicToolSpider.ScriptLineAssignerSpider;

/**
 * 为加载序列中的每一个ScriptBlock内部的存储的脚本信息，
 * 按照从前到后的顺序依次设置行数，
 * 方便后面的检查来输出脚本错误的具体位置。
 */
public class SequenceLineAssignerSpider extends AbstractBCSpider {

	public SequenceLineAssignerSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	protected void dealWithTargetBlock() {
		ScriptBlock subBlock = targetBlock.getSub();
		
		if (subBlock != null){
			new ScriptLineAssignerSpider(subBlock)
				.workUntilEnd();
		}
	}

}
