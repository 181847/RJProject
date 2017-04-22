package unfinishedClass.customRClass.scriptBlock.spider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * 收集静态Function的信息，
 * 由于在父类抽象类中实现的收集全部Function组件的方法，
 * 在这里无需覆盖那个方法，
 * 直接使用就可以了，
 * 在这里只需要设置一下functionStruct的类型就可以了。
 */
public class StaticFunInfoSpider extends FunInfoSpider {

	public StaticFunInfoSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		functionStruct.setType(InformationType.STATICFUN);
	}
}
