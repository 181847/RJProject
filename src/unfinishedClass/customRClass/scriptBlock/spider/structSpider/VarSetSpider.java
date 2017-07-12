package unfinishedClass.customRClass.scriptBlock.spider.structSpider;

import unfinishedClass.customRClass.rStruct.RSet;
import unfinishedClass.customRClass.rStruct.VarStruct;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

/**
 * 用于收集变量定义的Spider。
 */
public class VarSetSpider extends UtilsRSetSpider_with_RSet<VarStruct> {
	
	/**
	 * 用这个来构造的Spider请一定要调用placeSpider()方法，
	 * 来将Spider放置到准确的位置。
	 */
	public VarSetSpider() {
		//什么也不做。
	}

	public VarSetSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	public void countWork() {
		switch(infoType){
		case VAR:
			//收集的那个变量信息。
			finalRSet.append(getVarStruct());
			break;
			
		default:
			//什么也不做。
			break;
		}
	}

}
