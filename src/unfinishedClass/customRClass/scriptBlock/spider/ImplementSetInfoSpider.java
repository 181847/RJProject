package unfinishedClass.customRClass.scriptBlock.spider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

/**
 * 提取Block链上的原始信息作为父类接口的名称。
 */
public class ImplementSetInfoSpider extends AbstractBCSpider {
	protected ImplementSetStruct implementSetStruct;

	public ImplementSetInfoSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		implementSetStruct = new ImplementSetStruct();
	}

	@Override
	protected void dealWithTargetBlock() {
		implementSetStruct.addInterface(
				targetBlock
				.getInformation()
				.getOriginalString());
	}

	public ImplementSetStruct getImplementSetStruct(){
		return implementSetStruct;
	}
}
