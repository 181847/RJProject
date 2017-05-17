package unfinishedClass.customRClass.scriptBlock.spider.infoSpider.spider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;
import unfinishedClass.customRClass.set.SubFunSet;
import unfinishedClass.customRClass.struct.SubFunStruct;

/**
 * 收集Block链上面的SubFun信息，
 * 并整合成一个SubFunSet对象。
 */
public class SubFunSetInfoSpider extends AbstractBCSpider {
	protected SubFunSet subFunSet;
	
	public SubFunSetInfoSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		subFunSet = new SubFunSet();
	}

	@Override
	protected void dealWithTargetBlock() {
		Information information = targetBlock.getInformation();
		switch(information.getType()){
		case SUBFUN:
			subFunSet.appendSubFun(
					//创建一个新结构并添加
					new SubFunStruct(
							information.getOriginalString()));
			break;
		default:
			break;
		}
	}
	
	public SubFunSet getSubFunSet(){
		return subFunSet;
	}

}
