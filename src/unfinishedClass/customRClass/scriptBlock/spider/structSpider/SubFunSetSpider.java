package unfinishedClass.customRClass.scriptBlock.spider.structSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

/**
 * 收集所有SubFun定义的Spider，
 * 收集的结果放到一个RSet中。
 */
public class SubFunSetSpider extends UtilsRSetSpider_with_RSet<SubFun> {

	/**
	 * 用这个来构造的Spider请一定要调用placeSpider()方法，
	 * 来将Spider放置到准确的位置。
	 */
	public SubFunSetSpider() {
		//什么也不做。
	}

	public SubFunSetSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	public void countWork() {
		switch(infoType) {
		case SUBFUN:
			//获取当前Block及其子链所共同定义的SubFun结构。
			finalRSet.append(
					getSubFunStruct());
			break;
			
		default:
			break;
		}
	}

}
