package unfinishedClass.customRClass.scriptBlock.spider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.Information;

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
				.addExcuteeStruct(information.getOriginalString());
			break;
		default:
			break;
		}
	}
	
	public ExcuteeSetStruct getExcuteeSetStruct(){
		return excuteeSetStruct;
	}

}
