package unfinishedClass.customRClass.scriptBlock;

import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.FunAbstractGrammarSpider;

/**
 * 检查一个正常Function的信息，
 * 这个Spider可以用来检查普通Function以及StaticFunction。
 */
public class FunGrammarSpider extends FunAbstractGrammarSpider {

	/**
	 * 默认发生错误。
	 * @param targetBlock
	 * 		目标Block。
	 * @param spiderDescription
	 * 		描述当前Spider，这个描述将会被添加进ErrorReason当中，
	 * 		使得输出错误原因的时候能够知道错误是在什么Spider的检查下发生的。
	 */
	public FunGrammarSpider(ScriptBlock targetBlock, String spiderDescription) {
		super(targetBlock, spiderDescription);
	}

	@Override
	protected void dealWithTargetBlock() {
		switch(targetBlock.getInformation().getType()){
		case EXCUTEE:
			dealWith_SingleType(0, InformationType.EXCUTEE, "Excutee组件检查");
			break;
		case PARAMETER:
			dealWith_SingleType(1, InformationType.PARAMETER, "Parameter组件检查");
			break;
		case EXCUTER:
			dealWith_SingleType(2, InformationType.EXCUTER, "Excuter组件检查");
			break;
		case RETURNVAL:
			dealWith_SingleType(3, InformationType.RETURNVAL, "Returnval组件检查");
			break;
		case LOCALVAR:
			dealWith_LOCALVAR();
			break;
		case SUBFUN:
			dealWith_SingleType(5, InformationType.SUBFUN, "SubFun组件检查");
			break;
		case ARC:
			dealWith_SingleType(6, InformationType.ARC, "Arc组件检查");
			break;
		case COMMENT:
			dealWith_COMMENT();
			break;
		case VOID:
			dealWith_VOID();
			break;
		default:
			dealWith_Unexpected();
			break;
		}
	}

}
