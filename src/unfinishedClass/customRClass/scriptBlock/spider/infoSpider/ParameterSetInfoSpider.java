package unfinishedClass.customRClass.scriptBlock.spider.infoSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;
import unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct.ParameterSet;

/**
 * 收集Block链上面的Parameter组件信息，
 * 并整合成一个ParameterSet对象。
 */
public class ParameterSetInfoSpider extends AbstractBCSpider {
	protected ParameterSet parameterSet;
	
	public ParameterSetInfoSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		parameterSet = new ParameterSet();
	}

	@Override
	protected void dealWithTargetBlock() {
		Information information = targetBlock.getInformation();
		switch(information.getType()){
		case PARAMETER:
			parameterSet
				.addParameter(information.getOriginalString());
			break;
		default:
			break;
		}
	}
	
	public ParameterSet getParameterSet(){
		return parameterSet;
	}

}
