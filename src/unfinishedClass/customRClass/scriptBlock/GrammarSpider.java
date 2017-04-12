package unfinishedClass.customRClass.scriptBlock;

import unfinishedClass.customRClass.scriptBlock.information.LineScriptInformation;
import unfinishedClass.customRClass.scriptBlock.spider.ReasonedErrorSpider;

/**
 * 增加一个添加错误信息的方法，
 * 添加信息之前加上targetBlock所包含的Information存储的行数信息，
 * 并且添加一个方法允许在结尾加上Information的Description。
 */
public abstract class GrammarSpider extends ReasonedErrorSpider {

	/**
	 * 默认Spider没有发生错误。
	 */
	public GrammarSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}
	
	/**
	 * @param targetBlock
	 * 		目标Block节点。
	 * @param initError
	 * 		手动初始化Spider是否发生错误。
	 */
	public GrammarSpider(ScriptBlock targetBlock, boolean initError){
		super(targetBlock, initError);
	}

	@Override
	protected abstract void dealWithTargetBlock();
	
	/**
	 * 添加错误信息的时候在错误信息的前面
	 * 加上targetBlock的information的行数信息，
	 * 并且通过一个布尔参数来判断是否添加Information的Description。
	 * @param anotherReason
	 * 		错误原因。
	 * @param appendDescription
	 * 		是否添加targetBlock的Information的Description。
	 */
	public void appendReason(String anotherReason, boolean appendDescription){
		LineScriptInformation information =
				(LineScriptInformation) targetBlock.getInformation();
		
		if (appendDescription){
			anotherReason += ("\n\t信息描述：" + information.getDescription());
		}
		
		super.appendReason("错误行数：" + information.getLine() 
				+ "\n\t" + anotherReason);
	}

}
