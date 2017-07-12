package unfinishedClass.customRClass.scriptBlock.spider.structSpider;

import unfinishedClass.customRClass.rStruct.ExcuterFieldStruct;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

public class ExucterFieldStructSpider 
extends UtilsRStructSpider_with_RStruct<ExcuterFieldStruct> {

	public ExucterFieldStructSpider() {
		finalRStruct = new ExcuterFieldStruct();
	}
	
	public ExucterFieldStructSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		finalRStruct = new ExcuterFieldStruct();
	}

	@Override
	public void countWork() {
		switch(infoType) {
		case DECLAR_EXCUTERS_EXCEPTION:
			//异常执行出口。
			finalStruct
			.defineExcepExcuters_by_RSet(
					getRSet_fromSub_use(
							new ExcuterSetSpider()));
			break;
			
		case DECLAR_EXCUTERS_NORMAL:
			//普通执行出口。
			finalStruct
			.defineNormlExcuters_by_RSet(
					getRSet_fromSub_use(
							new ExcuterSetSpider()));
			break;
			
		default:
			break;
		}
		
	}
}
