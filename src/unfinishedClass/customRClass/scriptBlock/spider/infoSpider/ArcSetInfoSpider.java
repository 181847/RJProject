package unfinishedClass.customRClass.scriptBlock.spider.infoSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;

/**
 * 收集Block链上面的弧线信息，
 * 并整合成一个ArcSetStruct对象。
 */
public class ArcSetInfoSpider extends AbstractBCSpider {

	protected ArcSetStruct arcSetStruct;
	
	public ArcSetInfoSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		arcSetStruct = new ArcSetStruct();
	}

	@Override
	protected void dealWithTargetBlock() {
		Information information = targetBlock.getInformation();
		switch(information.getType()){
		case ARC:
			arcSetStruct
				.addArcStruct(information.getOriginalString());
			break;
		default:
			break;
		}
	}
	
	public ArcSetStruct getArcSetStruct(){
		return arcSetStruct;
	}
}
