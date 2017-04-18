package unfinishedClass.customRClass.scriptBlock;

import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.FunAbstractGrammarSpider;

/**
 * 检查抽象Function的声明，
 * 抽象Function的声明只允许四个Function外部的基本组件声明：
 * EXCUTEE、PARAMETER、EXCUTER、RETURNVAL。
 */
public class AbstractFunGrammarSpider extends FunAbstractGrammarSpider {

	public AbstractFunGrammarSpider(ScriptBlock targetBlock) {
		super(targetBlock, "抽象Function检查");
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
		case VOID:
			dealWith_VOID();
			break;
		default:
			dealWith_Unexpected();
			break;
		}
	}

}