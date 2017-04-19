package unfinishedClass.customRClass.scriptBlock.spider.grammarSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

public class VarGrammarSpider extends GrammarSpider {
	/**
	 * 默认发生错误。
	 * @param targetBlock
	 * 		目标Block。
	 * @param spiderDescription
	 * 		对本Spider的具体描述，
	 * 		这个描述将会被添加进错误信息的头部，
	 * 		用来帮助用户确定错误是发生在哪个检查过程中的。
	 */
	public VarGrammarSpider(ScriptBlock targetBlock, String spiderDescription) {
		super(targetBlock, spiderDescription);
	}

	@Override
	protected void dealWithTargetBlock() {
		switch(targetBlock.getInformation().getType()){
		case VAR:
			dealWith_VAR();
			break;
		case VOID:
			dealWith_VOID();
			break;
		default:
			dealWith_Unexpected();
			break;
		}
	}

	protected void dealWith_VAR() {
		foundOneTaggle();
	}
}
