package unfinishedClass.customRClass.scriptBlock.spider.structSpider;

import unfinishedClass.customRClass.rStruct.ExcuterStruct;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

/**
 * 收集单纯的执行出口的Spider。
 */
public class ExcuterSetSpider extends UtilsRSetSpider_with_RSet<ExcuterStruct> {

	/**
	 * 用这个来构造的Spider请一定要调用placeSpider()方法，
	 * 来将Spider放置到准确的位置。
	 */
	public ExcuterSetSpider() {
		//什么也不做。
	}

	public ExcuterSetSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	public void countWork() {
		switch(infoType){
		case EXCUTER:
			//添加执行出口。
			finalRSet.add(
					getExcuterStruct());
			break;
		
		default:
			//什么也不做。
			break;
		}
	}

}
