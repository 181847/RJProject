package unfinishedClass.customRClass.scriptBlock.spider.infoSpider.spider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;
import unfinishedClass.customRClass.set.ReturnvalSet;
import unfinishedClass.customRClass.struct.ReturnvalStruct;

public class ReturnvalSetInfoSpider extends AbstractBCSpider {
	protected ReturnvalSet returnvalSet;
	
	public ReturnvalSetInfoSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		returnvalSet = new ReturnvalSet();
	}

	@Override
	protected void dealWithTargetBlock() {
		Information information = targetBlock.getInformation();
		switch(information.getType()){
		case RETURNVAL:
			returnvalSet.appendReturnval(
					//创建一个新结构并添加
					new ReturnvalStruct(
							information.getOriginalString()));
			break;
		default:
			break;
		}
	}
	
	public ReturnvalSet getReturnvalSet(){
		return returnvalSet;
	}
}
