package unfinishedClass.customRClass.scriptBlock;

import basicTool.RLogger;

public class ScriptSpider extends AbstractBCSpider {
	protected int lineCounter;

	/**
	 * @param targetBlock
	 * 		开始遍历的Block节点。
	 * @param startLine
	 * 		打印脚本行数的初始值。
	 */
	public ScriptSpider(ScriptBlock targetBlock, int startLine) {
		super(targetBlock);
		lineCounter = startLine;
	}
	
	/**
	 * 打印脚本行数的初始值自动设为0。
	 * @param targetBlock
	 * 		开始遍历的Block节点。
	 */
	public ScriptSpider(ScriptBlock targetBlock){
		super(targetBlock);
		lineCounter = 0;
	}

	@Override
	protected void dealWithTargetBlock() {
		RLogger.log("Line " + lineCounter
				+ ": " + targetBlock.getInformation().toString());
		++lineCounter;
		
		if (targetBlock.getSub() != null){
			ScriptSpider newScriptSpider = 
					new ScriptSpider(targetBlock.getSub(), lineCounter);
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
