package unfinishedClass.customRClass.scriptBlock.spider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * 收集抽象Function的信息，
 * 只收集四个Function的外部组件信息：
 * Excutee组件、Parameter组件、Excuter组件、Returnval组件。
 * @author 75309
 *
 */
public class AbstractFunInfoSpider extends FunAbstractInfoSpider {

	public AbstractFunInfoSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		functionStruct.setType(InformationType.ABSTRACTFUN);
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
		default:
			break;
	}

}
