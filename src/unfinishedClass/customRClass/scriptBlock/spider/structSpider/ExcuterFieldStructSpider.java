package unfinishedClass.customRClass.scriptBlock.spider.structSpider;

import unfinishedClass.customRClass.rStruct.ExcuterFieldStruct;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

public class ExcuterFieldStructSpider 
extends UtilsRStructSpider_with_RStruct<ExcuterFieldStruct> {

	public ExcuterFieldStructSpider() {
		finalRStruct = new ExcuterFieldStruct();
	}
	
	public ExcuterFieldStructSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		finalRStruct = new ExcuterFieldStruct();
	}

	@Override
	public void countWork() {
		switch(infoType) {
		case DECLAR_EXCUTERS_EXCEPTION:
			//异常执行出口。
			finalRStruct
			.defineEExcuters_by_RSet(
					getRSet_fromSub_use(
							new ExcuterSetSpider()));
			break;
			
		case DECLAR_EXCUTERS_NORMAL:
			//普通执行出口。
			finalRStruct
			.defineNExcuters_by_RSet(
					getRSet_fromSub_use(
							new ExcuterSetSpider()));
			break;
			
		default:
			break;
		}
		
	}
}
