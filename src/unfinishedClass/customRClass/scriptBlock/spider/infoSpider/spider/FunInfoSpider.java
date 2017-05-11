package unfinishedClass.customRClass.scriptBlock.spider.infoSpider.spider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.ScriptDeclaration;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * 收集普通Function的信息。
 */
public class FunInfoSpider extends FunAbstractInfoSpider {

	/**
	 * 收集普通Function信息的Spider。
	 * @param targetBlock
	 * 		目标Block。
	 * @param declaration
	 * 		关于普通Function在脚本中的声明，
	 * 		将从这个声明中提取普通Function的名字信息。
	 */
	public FunInfoSpider(ScriptBlock targetBlock, String declaration) {
		super(targetBlock);
		functionStruct.setType(InformationType.FUN);
		functionStruct.setName(pickName(declaration));
	}

	/**
	 * 提取Function声明中的名称部分,
	 * 例如从“Function: functionName”提取出“functionName”这一部分。
	 * @param declaration
	 * 		FunctionBlock中的information字符串。
	 * @return
	 * 		Function声明的名称部分。
	 */
	protected String pickName(String declaration) {
		return declaration = 
				declaration.substring(
						ScriptDeclaration.function.length());
	}
}
