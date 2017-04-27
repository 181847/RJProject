package unfinishedClass.customRClass.scriptBlock.spider.infoSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;
import unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct.SubFunSetStruct;

/**
 * 收集Block链上面的SubFun信息，
 * 并整合成一个SubFunSetStruct对象。
 */
public class SubFunSetInfoSpider extends AbstractBCSpider {
	protected SubFunSetStruct subFunSetStruct;
	
	public SubFunSetInfoSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		subFunSetStruct = new SubFunSetStruct();
	}

	@Override
	protected void dealWithTargetBlock() {
		Information information = targetBlock.getInformation();
		switch(information.getType()){
		case SUBFUN:
			subFunSetStruct
				.addSubFun(information.getOriginalString());
			break;
		default:
			break;
		}
	}
	
	public SubFunSetStruct getSubFunSetStruct(){
		return subFunSetStruct;
	}

}
