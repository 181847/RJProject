package unfinishedClass.customRClass.scriptBlock.spider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * 对当前已经处理的Block数量进行记录。
 */
public abstract class CountableSpider extends AbstractBCSpider {
	/**
	 * 已经被处理的Block数量，
	 * 或者可以看做dealWithTargetBlock被调用的次数。
	 */
	protected int count;
	
	/**
	 * targetBlock.Infomation，
	 * 每次执行countWork之前，这个值都会被更新。
	 */
	protected Information targetInformation;
	
	/**
	 * targetBlock.information.originalString，
	 * 每次执行countWork之前，这个值都会被更新。
	 */
	protected String targetInfoString;
	
	/**
	 * targetBlock是否含有子Block，
	 * 每次执行countWork之前，这个值都会被更新。
	 */
	protected boolean hasSubBlock;
	
	/**
	 * targetBlock.subBlock，
	 * 每次执行countWork之前，这个值都会被更新，
	 * 如果hasSubBlock == false，
	 * 则这个值为null。
	 */
	protected ScriptBlock subBlock;

	public CountableSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		count = 0;
		targetInformation = null;
		targetInfoString = null;
	}
	
	@Override
	public void reset(){
		super.reset();
		count = 0;
		targetInformation = null;
		targetInfoString = null;
	}

	@Override
	protected void dealWithTargetBlock() {
		//事先获取Information对象和对象中存储的原始脚本信息。
		targetInformation = targetBlock.getInformation();
		targetInfoString = targetInformation.getOriginalString();
		//获取子Block
		subBlock = targetBlock.getSub();
		//如果有子Block，就设为true，否则就设为false。
		hasSubBlock = subBlock == null ? false : true;

		count++;
		//调用具体的方法。
		countWork();
	}
	
	/**
	 * 子类通过实现这个方法来对Block的工作进行定制。
	 */
	public abstract void countWork();
	
	/**
	 * 对targetBlock中的Information对象设置类型标志。
	 * @param type
	 * 		类型标志。
	 */
	protected void setInfo(InformationType type){
		targetInformation.setType(type);
	}
	
	/**
	 * 将targetBlock中的Information的Type设为VOID。
	 */
	protected void setInfo_VOID(){
		targetInformation.setType(InformationType.VOID);
	}
	
	/**
	 * 对targetBlock中的Information对象添加描述信息。
	 * @param decription
	 * 		被添加的描述信息。
	 */
	protected void descriptInfo(String decription){
		targetInformation.appendDescription(decription);
	}

}
