package unfinishedClass.customRClass.scriptBlock;

public class VarGrammarSpider extends GrammarSpider {
	protected boolean foundOne;

	/**
	 * 默认发生错误。
	 */
	public VarGrammarSpider(ScriptBlock targetBlock) {
		super(targetBlock, true);
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
		if ( ! foundOne){
			foundOne = true;
			error = false;
		}
	}

}
