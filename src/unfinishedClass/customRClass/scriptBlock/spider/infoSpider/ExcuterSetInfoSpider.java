package unfinishedClass.customRClass.scriptBlock.spider.infoSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;
import unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct.ExcuterSet;
import unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct.InfoStructHelper;

/**
 * 收集Block链上面的Excuter组件信息，
 * 并整合成一个ExcuterSet对象。
 */
public class ExcuterSetInfoSpider extends AbstractBCSpider {

	protected ExcuterSet excuterSet;
	
	public ExcuterSetInfoSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		excuterSet = new ExcuterSet();
	}

	@Override
	protected void dealWithTargetBlock() {
		Information information = targetBlock.getInformation();
		switch(information.getType()){
		case EXCUTER:
			excuterSet
			.addExcuter(
				information.getOriginalString());
			break;
		default:
			break;
		}
	}
	
	public ExcuterSet getExcuterSet(){
		return excuterSet;
	}

	public ExcuterSet getExcutersStruct() {
		return excuterSet;
	}
}
