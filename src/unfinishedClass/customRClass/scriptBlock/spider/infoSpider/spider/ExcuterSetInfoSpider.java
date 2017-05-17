package unfinishedClass.customRClass.scriptBlock.spider.infoSpider.spider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;
import unfinishedClass.customRClass.set.ExcuterSet;
import unfinishedClass.customRClass.set.InfoStructHelper;
import unfinishedClass.customRClass.struct.ExcuterStruct;

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
			excuterSet.appendExcuter(
					//创建一个新结构并添加
					new ExcuterStruct(
							information.getOriginalString()));
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
