package unfinishedClass.customRClass.scriptBlock.spider.infoSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.ScriptDeclaration;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * 收集静态Function的信息，
 * 由于在父类抽象类中实现的收集全部Function组件的方法，
 * 在这里无需覆盖那个方法，
 * 直接使用就可以了，
 * 在这里只需要设置一下functionStruct的类型就可以了。
 */
public class StaticFunInfoSpider extends FunAbstractInfoSpider {

	public StaticFunInfoSpider(ScriptBlock targetBlock, String declaration) {
		super(targetBlock);
		functionStruct.setType(InformationType.STATICFUN);
		functionStruct.setName(pickName(declaration));
	}
	
	/**
	 * 提取StaticFun声明中的名称部分,
	 * 例如从“StaticFunction: staticFunctionName”提取出“staticFunctionName”这一部分。
	 * @param declaration
	 * 		FunctionBlock中的information字符串。
	 * @return
	 * 		Function声明的名称部分。
	 */
	protected String pickName(String declaration) {
		return declaration = 
				declaration.substring(
						ScriptDeclaration.staticFun.length());
	}
}
