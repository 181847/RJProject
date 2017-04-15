package unfinishedClass.customRClass.scriptBlock;

public class VarGrammarSpider extends GrammarSpider {

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
			foundOneTaggle();
			break;
		case VOID:
			dealWith_VOID();
			break;
		default:
			dealWith_Unexpected();
			break;
		}
	}
	
	@Override
	public String getErrorReason(){
		if ( ! foundOne){
			return super.getErrorReason() + "Var声明中没有发现具体的Var声明。";
		} else {
			return super.getErrorReason();
		}
	}
}
