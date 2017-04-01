package unfinishedClass.customRClass.scriptBlock.spider;

import basicTool.RLogger;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.ScriptBlockHelper;

public class ScriptSpider extends AbstractBCSpider {
	protected int hierarchy;
	protected int lineCounter;

	/**
	 * @param targetBlock
	 * 		开始遍历的Block节点。
	 * @param startLine
	 * 		打印脚本行数的初始值。
	 */
	public ScriptSpider(ScriptBlock targetBlock, int startLine, int hierarchy) {
		super(targetBlock);
		lineCounter = startLine;
		this.hierarchy = hierarchy;
	}
	
	/**
	 * 打印脚本行数的初始值自动设为0。
	 * @param targetBlock
	 * 		开始遍历的Block节点。
	 */
	public ScriptSpider(ScriptBlock targetBlock){
		super(targetBlock);
		lineCounter = 1;
		hierarchy = 0;
	}

	@Override
	protected void dealWithTargetBlock() {
		String hierarchyString = "";
		
		for (int i = 0; i < hierarchy; ++i){
			hierarchyString += ScriptBlockHelper.hierarchyCharacter;
		}
		RLogger.log("Line " + lineCounter
				+ ": "  + hierarchyString 
				+ targetBlock.getInformation().toString());
		++lineCounter;
		
		if (targetBlock.getSub() != null){
			ScriptSpider newScriptSpider = 
					new ScriptSpider(targetBlock.getSub(), lineCounter, hierarchy + 1);
			newScriptSpider.workUntilEnd();
			lineCounter = newScriptSpider.getLineCounter();
		}
	}

	@Override
	public void reset(){
		super.reset();
		lineCounter = 0;
	}

	public int getLineCounter() {
		return lineCounter;
	}
}
