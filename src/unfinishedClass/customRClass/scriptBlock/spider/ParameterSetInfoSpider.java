package unfinishedClass.customRClass.scriptBlock.spider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.Information;

/**
 * 收集Block链上面的Parameter组件信息，
 * 并整合成一个ParameterSetStruct对象。
 */
public class ParameterSetInfoSpider extends AbstractBCSpider {
	protected ParameterSetStruct parameterSetStruct;
	
	public ParameterSetInfoSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		parameterSetStruct = new ParameterSetStruct();
	}

	@Override
	protected void dealWithTargetBlock() {
		Information information = targetBlock.getInformation();
		switch(information.getType()){
		case PARAMETER:
			parameterSetStruct
				.addParameterStruct(information.getOriginalString());
			break;
		default:
			break;
		}
	}
	
	public ParameterSetStruct getParameterSetStruct(){
		return parameterSetStruct;
	}

}
