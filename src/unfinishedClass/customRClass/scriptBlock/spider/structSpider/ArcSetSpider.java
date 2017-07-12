package unfinishedClass.customRClass.scriptBlock.spider.structSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

public class ArcSetSpider extends UtilsRSetSpider_with_RSet<ArcStruct> {

	/**
	 * 用这个来构造的Spider请一定要调用placeSpider()方法，
	 * 来将Spider放置到准确的位置。
	 */
	public ArcSetSpider() {
		//什么也不做。
	}

	public ArcSetSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}
	
	@Override
	public void countWork() {
		switch(infoType) {
		case ARC:
			//收集弧线定义。
			finalRSet.append(
					getRStruct_fromSub_use(
							new ArcStructSpider()));
			break;
			
		default:
			//什么也不做。
			break;
		}
	}

}
