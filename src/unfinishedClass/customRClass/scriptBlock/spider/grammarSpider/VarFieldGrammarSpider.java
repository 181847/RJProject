package unfinishedClass.customRClass.scriptBlock.spider.grammarSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.Information;

/**
 * 检查Block链上面是否正确声明了变量信息，
 * 允许Block链上面包含Static区域块，
 * 如果Static区域块出错的话，本Spider也算出错。
 */
public class VarFieldGrammarSpider extends VarGrammarSpider {
	protected boolean foundStatic;

	/**
	 * 默认发生错误。
	 * @param targetBlock
	 * 		目标Block。
	 * @param spiderDescription
	 * 		对本Spider的具体描述，
	 * 		这个描述将会被添加进错误信息的头部，
	 * 		用来帮助用户确定错误是发生在哪个检查过程中的。
	 */
	public VarFieldGrammarSpider(ScriptBlock targetBlock, String spiderDescription) {
		super(targetBlock, spiderDescription);
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
			foundOneTaggle();
			foundStatic = true;
			ScriptBlock subBlock = 
					targetBlock.getSub();
			
			if (subBlock == null){
				appendReason("静态成员声明块当中没有具体的成员声明", false);
				error = true;
				return;
			}
			
			GrammarSpider staticGS = new VarGrammarSpider(subBlock, "静态变量检查");
			staticGS.workUntilEnd();
			if (staticGS.occurredError()){
				appendReason(staticGS.getErrorReason());
				error = true;
			}
		}
	}

	@Override
	public String getErrorReason(){
		if ( ! foundOne){
			return super.getErrorReason() + "VarField中没有发现具体的Var声明。";
		} else {
			return super.getErrorReason();
		}
	}
}
