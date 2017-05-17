package unfinishedClass.customRClass.scriptBlock.spider.infoSpider.spider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;
import unfinishedClass.customRClass.set.ExcuteeSet;
import unfinishedClass.customRClass.struct.ExcuteeStruct;

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
			excuteeSet.appendExcutee(
					//创建一个新结构并添加
					new ExcuteeStruct(
							information.getOriginalString()));
			break;
		default:
			break;
		}
	}
	
	public ExcuteeSet getExcuteeSet(){
		return excuteeSet;
	}

}
