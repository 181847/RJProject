package unfinishedClass.customRClass.scriptBlock.spider.infoSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.ScriptDeclaration;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * 收集普通Function的信息。
 */
public class FunInfoSpider extends FunAbstractInfoSpider {

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
