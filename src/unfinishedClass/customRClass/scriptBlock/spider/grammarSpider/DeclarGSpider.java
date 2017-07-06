package unfinishedClass.customRClass.scriptBlock.spider.grammarSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

/**
 * 这种Spider所检查的信息全部是DECLAR_字段的声明信息，
 * 如果被检查的Block没有subBlock，
 * 则这种Block一定是不符合语法要求的，
 * 而且不允许对重复的声明信息进行检查，
 * 当发现已经有对先关Block的操作记录时，
 * 就不会对其他相同信息类型的Block进行操作，
 * 而是直接跳过，但是要注意，
 * 即便被跳过，这个Block还是会在记录槽中增加相应的记录，
 * 跳过的主要目的是减少性能损耗，
 * 不能拥有重复的声明，
 * 一旦发现有重复的Block，
 * 除了第一个之外的其他Block都不需要再进行检查了。
 */
public abstract class DeclarGSpider extends GrammarSpider {

	public DeclarGSpider(ScriptBlock targetBlock, String spiderDesc, int logLength) {
		super(targetBlock, spiderDesc, logLength);
	}

	@Override
	protected void grammarWork() {
		if ( ! hasSubBlock) {
			//记录缺少子信息的信息。
			dealWith_Lack_SubBlock();
			//记录无意义Block。
			recordNonesense();
		} else if ( 1 < getRecordOf(infoType)){
			//发现重复声明的Block信息类型。
			dealWith_Unexpected();
		} else {
			declarGrammarWork();
		}
	}
	
	/**
	 * 处理包含subBlock的Declar声明Block。
	 */
	protected abstract void declarGrammarWork();
}
