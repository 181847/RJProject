package unfinishedClass.customRClass.scriptBlock.spider.structSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

/**
 * 收集一系列注释方形区域的Spider。
 */
public class CommentSetSpider extends UtilsRSetSpider_with_RSet<CommentStruct> {

	/**
	 * 用这个来构造的Spider请一定要调用placeSpider()方法，
	 * 来将Spider放置到准确的位置。
	 */
	public CommentSetSpider() {
		//什么也不做。
	}

	public CommentSetSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	public void countWork() {
		switch(infoType) {
		case RECT:
			//方形区域注释。
			finalRSet.add(
					getCommentStruct());
			break;
			
		default:
			//什么也不做。
			break;
		}
	}

}
