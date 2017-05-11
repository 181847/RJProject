package unfinishedClass.customRClass.scriptBlock.spider.infoSpider.spider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;
import unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct.struct.ExcuteeSet;

/**
 * 收集Block链上面的Excutee组件信息，
 * 并整合成一个ExcuteeSet对象。
 */
public class ExcuteeSetInfoSpider extends AbstractBCSpider {
	protected ExcuteeSet excuteeSet;

	public ExcuteeSetInfoSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		excuteeSet = new ExcuteeSet();
	}

	@Override
	protected void dealWithTargetBlock() {
		Information information = targetBlock.getInformation();
		switch(information.getType()){
		case EXCUTEE:
			excuteeSet
				.addExcutee(information.getOriginalString());
			break;
		default:
			break;
		}
	}
	
	public ExcuteeSet getExcuteeSet(){
		return excuteeSet;
	}

}
