package unfinishedClass.customRClass.scriptBlock.spider.structSpider;

import unfinishedClass.customRClass.rStruct.ExcuterFieldStruct;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

/**
 * 收集两种执行出口，
 * 组成一个执行出口区域结构。
 */
public class ExcuterFieldStructSpider extends UtilsRStructSpider_with_RStruct<ExcuterFieldStruct> {

	public ExcuterFieldStructSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		finalRStruct = new ExcuterFieldStruct();
	}

	@Override
	public void countWork() {
		switch(infoType){
		case DECLAR_EXCUTERS_EXCEPTION:
			//异常执行入口收集。
			finalRStruct
			.defineExcepExcuter_by_RSet(
					getExcuterSet());
			break;
			
		case DECLAR_EXCUTERS_NORMAL:
			//普通执行入口收集。
			finalRStruct
			.defineNormlExcuter_by_RSet(
					getExcuterSet());
			break;
		}
	}

}
