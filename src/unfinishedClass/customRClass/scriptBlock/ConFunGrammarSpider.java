package unfinishedClass.customRClass.scriptBlock;

import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * 检查一个ConstructionFunction的语法错误，
 * 要求必须包括Arc声明，其他的可以不包含。
 */
public class ConFunGrammarSpider extends GrammarSpider {
	protected boolean foundOne;
	
	/**
	 * 0 - excuteeGS；<br>
	 * 1 - parameterGS；<br>
	 * 2 - excuterGS；<br>
	 * 3 - returnvalGS；<br>
	 * 4 - localVarGS；<br>
	 * 5 - subFunGS；<br>
	 * 6 - arcGS；<br>
	 * 7 - commentGS；<br>
	 */
	protected GrammarSpider[] grammarSpiders= new GrammarSpider[8];

	/**
	 * 默认没有发生错误。
	 */
	public ConFunGrammarSpider(ScriptBlock targetBlock) {
		super(targetBlock, true);
	}

	@Override
	protected void dealWithTargetBlock() {
		Information information = targetBlock.getInformation();

		switch(information.getType()){
		case EXCUTEE:
			dealWith_SingleType(0, InformationType.EXCUTEE, "Excutee组件");
			break;
		case PARAMETER:
			dealWith_SingleType(1, InformationType.PARAMETER, "Parameter组件");
			break;
		case EXCUTER:
			dealWith_SingleType(2, InformationType.EXCUTER, "Excuter组件");
			break;
		case RETURNVAL:
			dealWith_SingleType(3, InformationType.RETURNVAL, "Returnval组件");
			break;
		case LOCALVAR:
			dealWith_SingleType(4, InformationType.LOCALVAR, "LocalVar组件");
			break;
		case SUBFUN:
			dealWith_SingleType(5, InformationType.SUBFUN, "SubFun组件");
			break;
		case ARC:
			dealWith_SingleType(6, InformationType.ARC, "Arc组件");
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
	
	protected void dealWith_SingleType(int spiderIndex,
			InformationType type, 
			String typeDescription){
		foundOneTaggle();
		if (grammarSpiders[spiderIndex] != null){
			//arcGS不为null，
			//说明先前检查过一次ARC，
			//这个脚本中有两个ARC声明，语法错误
			appendReason("Function的定义中发现多个" + typeDescription, false);
			error = true;
			return;
		}
		
		ScriptBlock subBlock = targetBlock.getSub();
		if (subBlock == null){
			appendReason("Function声明中" 
					+ typeDescription + "没有具体的声明。", false);
			error = true;
			return;
		}
		
		grammarSpiders[spiderIndex] = new SingleTypeGrammarSpider(subBlock, 
				type, 
				typeDescription);
		grammarSpiders[spiderIndex].workUntilEnd();
		
		if (grammarSpiders[spiderIndex].occurredError()){
			//SubFun组件的具体声明中发现错误
			appendReason(grammarSpiders[spiderIndex].getErrorReason());
			error = true;
			return;
		}
	}
}
