package unfinishedClass.customRClass.scriptBlock.spider.grammarSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

/**
 * 这种Spider所检查的信息全部是DECLAR_字段的声明信息，
 * 如果被检查的Block没有subBlock，
 * 则这种Block一定是不符合语法要求的，
 * 不需要再进行任何检查，
 * 此Spider在每次对Block进行检查之前都要先查看是否含有
 */
public abstract class DeclarGSpider extends GrammarSpider {

	public DeclarGSpider(ScriptBlock targetBlock, String spiderDesc) {
		super(targetBlock, spiderDesc);
	}

	@Override
	protected void grammarWork() {
		if ( ! hasSubBlock) {
			//记录缺少子信息的信息。
			dealWith_Lack_SubBlock();
		} else {
			declarGrammarWork();
		}
	}
	
	/**
	 * 处理包含subBlock的Declar声明Block。
	 */
	protected abstract void declarGrammarWork();
}
