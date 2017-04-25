package unfinishedClass.customRClass.scriptBlock.spider.infoSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;
import unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct.ExcuteeSetStruct;

/**
 * 收集Block链上面的Excutee组件信息，
 * 并整合成一个ExcuteeSetStruct对象。
 */
public class ExcuteeSetInfoSpider extends AbstractBCSpider {
	protected ExcuteeSetStruct excuteeSetStruct;

	public ExcuteeSetInfoSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		excuteeSetStruct = new ExcuteeSetStruct();
	}

	@Override
	protected void dealWithTargetBlock() {
		Information information = targetBlock.getInformation();
		switch(information.getType()){
		case EXCUTEE:
			excuteeSetStruct
				.addExcutee(information.getOriginalString());
			break;
		default:
			break;
		}
	}
	
	public ExcuteeSetStruct getExcuteeSetStruct(){
		return excuteeSetStruct;
	}

}
