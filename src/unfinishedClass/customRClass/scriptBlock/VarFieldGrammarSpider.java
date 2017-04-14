package unfinishedClass.customRClass.scriptBlock;

import unfinishedClass.customRClass.scriptBlock.information.Information;

/**
 * 检查Block链上面是否正确声明了变量信息，
 * 允许Block链上面包含Static区域块，
 * 如果Static区域块出错的话，本Spider也算出错。
 */
public class VarFieldGrammarSpider extends VarGrammarSpider {
	protected boolean foundOne;
	protected boolean foundStatic;

	/**
	 * 默认发生错误。
	 */
	public VarFieldGrammarSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		foundStatic = false;
	}

	@Override
	protected void dealWithTargetBlock() {
		Information information = targetBlock.getInformation();
		
		switch(information.getType()){
		case STATIC:
			dealWith_STATIC();
			break;
		case VAR:
			//dealWith_VAR();
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
	 * 处理静态成员声明。
	 */
	protected void dealWith_STATIC() {
		if (foundStatic){
			//foundStatic为true表示之前已经发现了一个静态成员，
			//所以无论当前的information是什么，
			//都算是发生了错误。
			appendReason("发现多个静态成员声明块，"
					+ "一个成员声明空间当中，"
					+ "最多只能有一个Static声明。"
					, false);
			error = true;
		} else {
			if ( ! foundOne ){
				foundOne = true;
				error = false;
			}
			foundStatic = true;
			GrammarSpider staticGS = new VarGrammarSpider(targetBlock);
			staticGS.workUntilEnd();
			if (staticGS.occurredError()){
				appendReason(staticGS.getErrorReason());
				error = true;
			}
		}
	}

}
