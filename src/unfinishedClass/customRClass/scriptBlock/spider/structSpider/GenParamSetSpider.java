package unfinishedClass.customRClass.scriptBlock.spider.structSpider;

import unfinishedClass.customRClass.rStruct.GenParamStruct;
import unfinishedClass.customRClass.rStruct.RSet;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

/**
 * 收集泛参定义的Spider。
 */
public class GenParamSetSpider extends UtilsRSetSpider_with_RSet<GenParamStruct>{

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
					getTargetGenParamStruct());
			break;
			
		default:
			//什么也不做。
			break;
		}
	}

}
