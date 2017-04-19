package unfinishedClass.customRClass.scriptBlock.spider.grammarSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * 检查一个ConstructionFunction的语法错误，
 * 要求必须包括Arc声明，
 * 不能包含Excutee组件、Excuter组件、Returnval组件。
 */
public class ConFunGrammarSpider extends FunAbstractGrammarSpider {
	/**
	 * 默认没有发生错误。
	 */
	public ConFunGrammarSpider(ScriptBlock targetBlock) {
		super(targetBlock, "构造Function检查");
	}

	@Override
	protected void dealWithTargetBlock() {
		Information information = targetBlock.getInformation();

		switch(information.getType()){
		case PARAMETER:
			dealWith_SingleType(1, InformationType.PARAMETER, "Parameter组件检查");
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
	
	@Override
	public boolean occurredError(){
		if ( ! foundType[6]){
			//没有发现弧线声明，语法错误
			return true;
		}
		
		return super.occurredError();
	}
	
	@Override
	public String getErrorReason(){
		if ( ! foundType[6]){
			return super.getErrorReason() 
					+ "没有发现弧线声明。";
		}
		
		return super.getErrorReason();
	}
}
