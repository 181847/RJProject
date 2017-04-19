package unfinishedClass.customRClass.scriptBlock.spider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

/**
 * 已知Block链为一个脚本信息链，
 * 收集这些脚本信息，
 * 来生成一个RClassStruct。
 */
public class ScriptInfoSpider extends AbstractBCSpider {
	/**
	 * 存储着一个RClass定义的结构化变量，
	 * 内部包含RClass的类型、名字、成员Function……
	 */
	protected RClassStruct rClassStruct;

	public ScriptInfoSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		rClassStruct = new RClassStruct();
	}

	@Override
	protected void dealWithTargetBlock() {
		switch(targetBlock.getInformation().getType()){
		case TYPE:
			dealWith_TYPE();
			break;
		case 
		}
	}

}
