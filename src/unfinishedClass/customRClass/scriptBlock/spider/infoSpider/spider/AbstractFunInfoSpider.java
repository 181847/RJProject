package unfinishedClass.customRClass.scriptBlock.spider.infoSpider.spider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.ScriptDeclaration;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * 收集抽象Function的信息，
 * 只收集四个Function的外部组件信息：
 * Excutee组件、Parameter组件、Excuter组件、Returnval组件。
 * @author 75309
 *
 */
public class AbstractFunInfoSpider extends FunAbstractInfoSpider {

	/**
	 * 收集抽象Function信息的Spider。
	 * @param targetBlock
	 * 		目标Block。
	 * @param declaration
	 * 		关于抽象Function在脚本中的声明，
	 * 		将从这个声明中提取抽象Function的名字信息。
	 */
	public AbstractFunInfoSpider(ScriptBlock targetBlock, String declaration) {
		super(targetBlock);
		functionStruct.setType(InformationType.ABSTRACTFUN);
		functionStruct.setName(pickName(declaration));
	}
	
	/**
	 * 提取AbstractFun声明中的名称部分,
	 * 例如从“AbstractFunction: abstractFunctionName”提取出“abstractFunctionName”这一部分。
	 * @param declaration
	 * 		FunctionBlock中的information字符串。
	 * @return
	 * 		Function声明的名称部分。
	 */
	protected String pickName(String declaration) {
		return declaration = 
				declaration.substring(
						ScriptDeclaration.abstractFun.length());
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

}
