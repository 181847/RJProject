package unfinishedClass.customRClass.scriptBlock.spider.structSpider;

import unfinishedClass.customRClass.rStruct.RClassRefStruct;
import unfinishedClass.customRClass.rStruct.RSet;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

/**
 * 用于收集类型引用定义的spider。
 */
public class RClassRefSetSpider
extends UtilsRSetSpider_with_RSet<RClassRefStruct>{

	/**
	 * 用这个来构造的Spider请一定要调用placeSpider()方法，
	 * 来将Spider放置到准确的位置。
	 */
	public RClassRefSetSpider() {
		//什么也不做。
	}

	public RClassRefSetSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	public void countWork() {
		switch(infoType){
		case CLASS_REF_CL:
			//实类型引用。
		case CLASS_REF_GP:
			//泛参引用。
			
			finalRSet.add(
					//从targetBlock即其子链上获取类型引用定义。
					getRClassRefStruct());
			break;
			
		default:
			//什么也不做。
			break;
		}
		
	}
}
