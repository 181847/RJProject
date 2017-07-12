package unfinishedClass.customRClass.scriptBlock.spider.structSpider;

import unfinishedClass.customRClass.rStruct.ArcStruct;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

public class ArcStructSpider 
extends UtilsRStructSpider_with_RStruct<ArcStruct> {
	
	public ArcStructSpider() {
		finalRStruct = new ArcStruct();
	}
	
	public ArcStructSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		finalRStruct = new ArcStruct();
	}
	
	@Override
	public void countWork() {
		switch(infoType) {
		case ARC_START:
			finalStruct
			.defineArcStart(
					//获取弧线的一个端点结构。
					getArcPointStruct());
			break;
			

		case ARC_START:
			finalStruct
			.defineArcStart(
					//获取弧线的一个端点结构。
					getArcPointStruct());
			break;
			
		default:
			//什么也不做。
			break;
		}
	}

}
