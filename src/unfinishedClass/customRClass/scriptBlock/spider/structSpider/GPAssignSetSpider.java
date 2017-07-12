package unfinishedClass.customRClass.scriptBlock.spider.structSpider;

import unfinishedClass.customRClass.rStruct.RSet;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

/**
 * 用于获取多个泛参指定的RSetSpider。
 */
public class GPAssignSetSpider extends UtilsRSetSpider_with_RSet<GPAssignStruct> {

	/**
	 * 用这个来构造的Spider请一定要调用placeSpider()方法，
	 * 来将Spider放置到准确的位置。
	 */
	public GPAssignSetSpider() {
		//什么也不做。
	}

	public GPAssignSetSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	public void countWork() {
		switch(infoType){
		case GP_ASSIGN_CL:
			//泛参被指定为另一个泛参。
		case GP_ASSIGN_GP:
			//泛参被指定为另一个泛参。
			finalRSet.append(getGPAssignStruct());
			break;
			
		default:
			//什么也不做。
			break;
		}
	}

}
