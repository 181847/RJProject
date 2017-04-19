package unfinishedClass.customRClass.scriptBlock.spider.basicToolSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.LineScriptInformation;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;

/**
 * 为每一个ScriptBlock的Information设置行数，
 * 方便后续的检查输出错误的具体位置。
 */
public class ScriptLineAssignerSpider extends AbstractBCSpider {
	protected int nextLine;

	/**
	 * 行数计算自动从1开始。
	 * @param targetBlock
	 * 		目标Block。
	 */
	public ScriptLineAssignerSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		nextLine = 1;
	}
	
	/**
	 * 手动设定开始行数。
	 * @param targetBlock
	 * 		目标Block。
	 * @param startLine
	 * 		开始行数。
	 */
	public ScriptLineAssignerSpider(ScriptBlock targetBlock, int startLine) {
		super(targetBlock);
		nextLine = startLine;
	}

	@Override
	protected void dealWithTargetBlock() {
		//为information添加行数信息
		targetBlock
		.setInformation(
				new LineScriptInformation(
						targetBlock.getInformation(), nextLine));
		
		++nextLine;
		
		ScriptBlock subBlock = targetBlock.getSub();
		if (subBlock != null){
			ScriptLineAssignerSpider lineSpider = 
					new ScriptLineAssignerSpider(subBlock, nextLine);
			lineSpider.workUntilEnd();
			
			nextLine = lineSpider.getNextLine();
		}
	}
	
	public int getNextLine(){
		return nextLine;
	}

}
