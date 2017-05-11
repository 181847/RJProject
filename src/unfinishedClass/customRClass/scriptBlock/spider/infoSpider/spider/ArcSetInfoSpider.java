package unfinishedClass.customRClass.scriptBlock.spider.infoSpider.spider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;
import unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct.struct.ArcSet;

/**
 * 收集Block链上面的弧线信息，
 * 并整合成一个ArcSet对象。
 */
public class ArcSetInfoSpider extends AbstractBCSpider {

	protected ArcSet arcSet;
	
	public ArcSetInfoSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		arcSet = new ArcSet();
	}

	@Override
	protected void dealWithTargetBlock() {
		Information information = targetBlock.getInformation();
		switch(information.getType()){
		case ARC:
			arcSet
				.addArc(information.getOriginalString());
			break;
		default:
			break;
		}
	}
	
	public ArcSet getArcSet(){
		return arcSet;
	}
}
