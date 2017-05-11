package unfinishedClass.customRClass.scriptBlock.spider.infoSpider.spider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;
import unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct.struct.ImplementSet;

/**
 * 提取Block链上的原始信息作为父类接口的名称。
 */
public class ImplementSetInfoSpider extends AbstractBCSpider {
	protected ImplementSet implementSet;

	public ImplementSetInfoSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		implementSet = new ImplementSet();
	}

	@Override
	protected void dealWithTargetBlock() {
		implementSet.addInterface(
				targetBlock
				.getInformation()
				.getOriginalString());
	}

	public ImplementSet getImplementSet(){
		return implementSet;
	}
}
