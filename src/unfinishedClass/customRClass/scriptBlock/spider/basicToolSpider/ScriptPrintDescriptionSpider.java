package unfinishedClass.customRClass.scriptBlock.spider.basicToolSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

/**
 * 专门打印一个ScriptBlock子链上的所有信息。
 */
public class ScriptPrintDescriptionSpider extends ScriptPrintSpider {

	public ScriptPrintDescriptionSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	/**
	 * 在这个方法中对要打印的信息进行自定义。
	 */
	protected void modifyPrintString() {
		//先调用父类的信息来构造打印字符串。
		super.modifyPrintString();
		
		newLine();
		
		//添加标签信息。
		appendInfoType();
		
		//添加子类增加的信息。
		//换行。
		newLine();
		
		//添加Information的描述。
		appendDescription();
	}
	
	/**
	 * 添加Block中info的标签信息。
	 */
	protected void appendInfoType() {
		printString += 
				"InfoType: " + infoType.toString();
	}

	/**
	 * 在打印信息的末尾添加Information的描述。
	 */
	protected void appendDescription() {
		printString += 
				"Desc: " + targetInformation.getDescription();
	}

	@Override
	protected void printSubBlock(){
		sendSpider(
				new ScriptPrintDescriptionSpider(subBlock));
	}

}
