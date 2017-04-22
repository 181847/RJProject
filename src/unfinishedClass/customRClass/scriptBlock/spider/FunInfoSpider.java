package unfinishedClass.customRClass.scriptBlock.spider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * 收集普通Function的信息。
 */
public class FunInfoSpider extends FunAbstractInfoSpider {

	public FunInfoSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		functionStruct.setType(InformationType.FUN);
	}

	@Override
	protected void dealWithTargetBlock() {
		switch(targetBlock.getInformation().getType()){
		case EXCUTEE:
			dealWith_EXCUTEE();
			break;
		case PARAMETER:
			dealWith_PARAMETER();
			break;
		case EXCUTER:
			dealWith_EXCUTER();
			break;
		case RETURNVAL:
			dealWith_RETURNVAL();
			break;
		case LOCALVAR:
			dealWith_LOCALVAR();
			break;
		case SUBFUN:
			dealWith_SUBFUN();
			break;
		case ARC:
			dealWith_ARC();
			break;
		case COMMENT:
			dealWith_COMMENT();
			break;
		default:
			break;
		}
	}

}
