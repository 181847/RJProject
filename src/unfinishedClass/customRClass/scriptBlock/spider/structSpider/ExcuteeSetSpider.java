package unfinishedClass.customRClass.scriptBlock.spider.structSpider;

import unfinishedClass.customRClass.rStruct.ExcuteeStruct;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

/**
 * 用于收集一系列Excutee定义的Spider。
 * @author 75309
 *
 */
public class ExcuteeSetSpider extends UtilsRSetSpider_with_RSet<ExcuteeStruct> {

	/**
	 * 用这个来构造的Spider请一定要调用placeSpider()方法，
	 * 来将Spider放置到准确的位置。
	 */
	public ExcuteeSetSpider() {
		//什么也不做。
	}
	
	public ExcuteeSetSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	public void countWork() {
		switch(infoType) {
		case EXCUTEE:
			finalRSet.add(getExcuteeStruct());
			break;
			
		default:
			//什么也不做。
			break;
		}
	}

}
