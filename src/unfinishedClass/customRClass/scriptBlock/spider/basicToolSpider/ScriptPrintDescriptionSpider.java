package unfinishedClass.customRClass.scriptBlock.spider.basicToolSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

public class ScriptPrintDescriptionSpider extends ScriptPrintSpider {

	public ScriptPrintDescriptionSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}
	
	public ScriptPrintDescriptionSpider(ScriptBlock targetBlock, int startLine, int hierarchy) {
		super(targetBlock, startLine, hierarchy);
	}

	/**
	 * 在每一行脚本信息的下一行都添加上这个Information的Description。
	 */
	@Override
	protected void appendSomeInformation(){
		printString = printString + 
				"\nDescription: " + targetBlock.getInformation().getDescription();
	}
	
	@Override
	protected void dealWithSubBlock(ScriptBlock subBlock){
		ScriptPrintSpider newScriptSpider = 
				new ScriptPrintDescriptionSpider(subBlock, lineCounter, hierarchy + 1);
		newScriptSpider.workUntilEnd();
		lineCounter = newScriptSpider.getLineCounter();
	}

}
