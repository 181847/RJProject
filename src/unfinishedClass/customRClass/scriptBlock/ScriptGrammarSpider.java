package unfinishedClass.customRClass.scriptBlock;

import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.ReasonedErrorSpider;

/**
 * 假设脚本定义的是AbstractRClass，
 * 检查必要信息是否包含，
 * 以及是否包含了一些无用信息。
 */
public class ScriptGrammarSpider extends ReasonedErrorSpider {
	protected ReasonedErrorSpider typeGS;
	protected ReasonedErrorSpider nameGS;
	protected ReasonedErrorSpider extendsGS;
	protected ReasonedErrorSpider implementsGS;
	protected ReasonedErrorSpider memberGS;
	protected ReasonedErrorSpider conFunGS;
	protected ReasonedErrorSpider staticFunGS;
	protected ReasonedErrorSpider funGS;
	protected ReasonedErrorSpider abstractGS;

	/**
	 * 默认Spider没有发生错误。
	 * @param targetBlock
	 * 		目标Block节点。
	 */
	public ScriptGrammarSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}
	
	public ScriptGrammarSpider(ScriptBlock targetBlock, boolean initError){
		super(targetBlock, initError);
	}

	@Override
	protected void dealWithTargetBlock() {
		InformationType infoType = targetBlock.getInformation().getType();
		
		switch(infoType){
		case TYPE:
			dealWith_TYPE();
			break;
		case NAME:
			dealWith_NAME();
			break;
		case EXTENDS:
			dealWith_EXTENDS();
			break;
		case IMPLEMENTS:
			dealWith_IMPLEMENTS();
			break;
		case MEMBER:
			dealWith_MEMBER();
			break;
		case CONFUN:
			dealWith_CONFUN();
			break;
		case STATICFUN:
			dealWith_STATICFUN();
			break;
		case FUN:
			dealWith_FUN();
			break;
		case ABSTRACTFUN:
			dealWith_ABSTRACTFUN();
			break;
		case VOID:
			dealWith_VOID();
			break;
		default:
			break;
		}

	}

	/**
	 * 已知targetBlock的InformationType是NAME类型，
	 * 进行相应的语法检查，
	 * 要求同一个脚本中不得出现两个NAME声明。
	 */
	private void dealWith_NAME() {
		if (nameGS != null){
			//nameGS不为null，
			//说明先前检查过一次NAME，
			//这个脚本中有两个NAME声明，语法错误
			appendReason("定义中多次发现NAME型的信息。")
			error = true;
			return;
		}
		
		ScriptBlock subBlock = targetBlock.getSub();
		if (subBlock == null){
			//TYPE声明的下面没有具体的类型定义，语法错误
			appendReason("NAME型的信息下面没有具体的名称定义。")
			error = true;
			return;
		}
		
		nameGS = new NameGrammarSpider(subBlock);
		nameGS.workUntilEnd();
		
		if (nameGS.occurredError()){
			//具体的类型定义检查中发现错误，语法错误
			appendReason(nameGS.getErrorReason());
			error = true;
			return;
		}
	}

	/**
	 * 已知targetBlock的InformationType是TYPE类型，
	 * 进行相应的语法检查，
	 * 要求同一个脚本中不得出现两个TYPE声明。
	 */
	private void dealWith_TYPE() {
		if (typeGS != null){
			//typeGS不为null，
			//说明先前检查过一次TYPE，
			//这个脚本中有两个TYPE声明，语法错误
			appendReason("定义中多次发现TYPE型的信息。")
			error = true;
			return;
		}
		
		ScriptBlock subBlock = targetBlock.getSub();
		if (subBlock == null){
			//TYPE声明的下面没有具体的类型定义，语法错误
			appendReason("TYPE型的信息下面没有具体的类型定义。")
			error = true;
			return;
		}
		
		typeGS = new TypeGrammarSpider(subBlock);
		typeGS.workUntilEnd();
		
		if (typeGS.occurredError()){
			//具体的类型定义检查中发现错误，语法错误
			appendReason(typeGS.getErrorReason());
			error = true;
			return;
		}
	}
}
