package unfinishedClass.customRClass.scriptBlock;

import unfinishedClass.customRClass.scriptBlock.spider.ReasonedErrorSpider;

/**
 * 检查一个ScriptBlock链表中中是否正确的声明了相关脚本的顶层信息，
 * 包括类型、名称、父类、接口父类，成员、构造Function、
 * 静态Function、成员Function、抽象Function。
 */
public class ScriptGrammarSpider extends ReasonedErrorSpider {
	protected ReasonedErrorSpider typeSpider;
	protected ReasonedErrorSpider nameSpider;
	protected ReasonedErrorSpider extendsSpider;
	protected ReasonedErrorSpider implementsSpider;
	protected ReasonedErrorSpider memberSpider;
	protected ReasonedErrorSpider conFunSpider;
	protected ReasonedErrorSpider staticFunSpider;
	protected ReasonedErrorSpider funSpider;
	protected ReasonedErrorSpider abstractSpider;

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
		String informationString = targetBlock.getInformation().toString();
		
		if (informationString.equals(ScriptBlockHelper.typeDeclaration)){
		//类型声明
			
		} else if (informationString.equals(ScriptBlockHelper.nameDeclaration)){
		//名字声明
			
		} else if (informationString.equals(ScriptBlockHelper.nameDeclaration)){
		//父类声明
			
		} else if (informationString.equals(ScriptBlockHelper.nameDeclaration)){
		//父类接口声明
			
		} else if (informationString.equals(ScriptBlockHelper.nameDeclaration)){
		//成员声明
				
		} else if (informationString.equals(ScriptBlockHelper.nameDeclaration)){
		//构造Function声明
				
		} else if (informationString.equals(ScriptBlockHelper.nameDeclaration)){
		//静态Function声明
				
		} else if (informationString.equals(ScriptBlockHelper.nameDeclaration)){
		//成员Function声明
				
		} else if (informationString.equals(ScriptBlockHelper.nameDeclaration)){
		//抽象Function声明
			
		} else if (informationString.equals(ScriptBlockHelper.nameDeclaration)){
		//非法信息声明
				
		}

	}
}
