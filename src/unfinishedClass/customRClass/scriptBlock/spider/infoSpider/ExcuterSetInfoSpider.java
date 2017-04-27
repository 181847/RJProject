package unfinishedClass.customRClass.scriptBlock.spider.infoSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;
import unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct.ExcuterSetStruct;
import unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct.InfoStructHelper;

/**
 * 收集Block链上面的Excuter组件信息，
 * 并整合成一个ExcuterSetStruct对象。
 */
public class ExcuterSetInfoSpider extends AbstractBCSpider {

	protected ExcuterSetStruct excuterSetStruct;
	
	public ExcuterSetInfoSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		excuterSetStruct = new ExcuterSetStruct();
	}

	@Override
	protected void dealWithTargetBlock() {
		Information information = targetBlock.getInformation();
		switch(information.getType()){
		case EXCUTER:
			excuterSetStruct
			.addExcuter(
				information.getOriginalString());
			break;
		default:
			break;
		}
	}
	
	public ExcuterSetStruct getExcuterSetStruct(){
		return excuterSetStruct;
	}

	public ExcuterSetStruct getExcutersStruct() {
		return excuterSetStruct;
	}
}
