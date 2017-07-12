package unfinishedClass.customRClass.scriptBlock.spider.structSpider;

import unfinishedClass.customRClass.rStruct.ArcFieldStruct;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

/**
 * 一种用于收集两种弧线定义的Spider，
 * 返回一个ArcField，内部分别用两个RSet存储两种弧线定义。
 * @author 75309
 *
 */
public class ArcFieldStructSpider 
extends UtilsRStructSpider_with_RStruct<ArcFieldStruct> {

	public ArcFieldStructSpider() {
		finalRStruct = new ArcFieldStruct();
	}
	
	public ArcFieldStructSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		finalRStruct = new ArcFieldStruct();
	}

	@Override
	public void countWork() {
		switch (infoType) {
		case DECLAR_ARCS_E_TO_E:
			//执行弧线定义区域。
			finalRStruct
			.defineEArcs_by_RSet(
					//从子链中获取弧线定义集合。
					getRSet_fromSub_use(
							new ArcSetSpider()));
			break;
			
		case DECLAR_ARCS_R_TO_P:
			//参数弧线定义区域。
			finalRStruct
			.definePArcs_by_RSet(
					//从子链中获取弧线定义集合。
					getRSet_fromSub_use(
							new ArcSetSpider()));
			break;
		}
	}

}
