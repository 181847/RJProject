package unfinishedClass.customRClass.scriptBlock.spider.structSpider;

import unfinishedClass.customRClass.rStruct.GenParamStruct;
import unfinishedClass.customRClass.rStruct.RSet;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

/**
 * 收集泛参定义的Spider。
 */
public class GenParamSetSpider extends UtilsRSetSpider_with_RSet<GenParamStruct>{

	/**
	 * 用这个来构造的Spider请一定要调用placeSpider()方法，
	 * 来将Spider放置到准确的位置。
	 */
	public GenParamSetSpider() {
		//什么也不做。
	}
	
	public GenParamSetSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	public void countWork() {
		switch(infoType){
		case GEN_PARAM:
			finalRSet.add(
					//获取当前targetBlock以及其子链所形成的
					//完整的泛参定义结构。
					getGenParamStruct());
			break;
			
		default:
			//什么也不做。
			break;
		}
	}

}
