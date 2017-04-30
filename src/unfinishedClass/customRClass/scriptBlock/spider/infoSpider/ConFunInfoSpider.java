package unfinishedClass.customRClass.scriptBlock.spider.infoSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * 收集构造Function的信息，
 * 构造Function不会收集Excutee组件、Excuter组件、Returnval组件。
 */
public class ConFunInfoSpider extends FunAbstractInfoSpider {

	public ConFunInfoSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		functionStruct.setType(InformationType.CONFUN);
		functionStruct.setName("CONFUN");
	}
	
	@Override
	protected void dealWithTargetBlock(){
		switch(targetBlock.getInformation().getType()){
		case PARAMETER:
			dealWith_PARAMETER();
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
