package unfinishedClass.customRClass.scriptBlock;

/**
 * 检查Comment信息块中是否正确声明了方形区域。
 */
public class CommentRectGrammarSpider extends GrammarSpider {

	/**
	 * 默认发生错误。
	 */
	public CommentRectGrammarSpider(ScriptBlock targetBlock) {
		super(targetBlock, true, "Function中的注释信息检查。");
	}

	@Override
	protected void dealWithTargetBlock() {
		switch(targetBlock.getInformation().getType()){
		case RECT:
			dealWith_RECT();
			break;
		case VOID:
			dealWith_VOID();
			break;
		default:
			dealWith_Unexpected();
			break;
		}

	}

	/**
	 * 检查注释的方形区域，
	 * 注释可以没有任何文本，只是一个方形的区域，
	 * 也就是说targetBlock.subBlock的状况无需任何检查，
	 * subBlock包含的所有的原始信息都是这个方形区域的注释文本。
	 */
	protected void dealWith_RECT() {
		foundOneTaggle();
	}

}
