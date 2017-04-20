package unfinishedClass.customRClass.scriptBlock.spider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

/**
 * 用于收集Block链上的继承接口信息的Spider，
 * 这里不会进行任何检查，
 * 直接将每个Block上的Information的原始信息作为
 * 接口的名称提取到rClassStruct中。
 */
public abstract class InfoSpider extends AbstractBCSpider {
	/**
	 * 用于存储RClass内部信息的结构体。
	 */
	protected RClassStruct rClassStruct;
	
	public InfoSpider(RClassStruct rClassStructContainer, 
			ScriptBlock targetBlock) {
		super(targetBlock);
		this.rClassStruct = rClassStructContainer;
	}

	@Override
	protected abstract void dealWithTargetBlock();
	
	public RClassStruct getRClassStruct(){
		return rClassStruct;
	}

}
