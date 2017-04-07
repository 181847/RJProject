package unfinishedClass.customRClass.scriptBlock.spider;

import basicTool.RLogger;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.ScriptDeclaration;

public class ScriptPrintSpider extends AbstractBCSpider {
	protected int hierarchy;
	protected int lineCounter;
	protected String printString;

	/**
	 * @param targetBlock
	 * 		开始遍历的Block节点。
	 * @param startLine
	 * 		打印脚本行数的初始值。
	 */
	public ScriptPrintSpider(ScriptBlock targetBlock, int startLine, int hierarchy) {
		super(targetBlock);
		lineCounter = startLine;
		this.hierarchy = hierarchy;
	}
	
	/**
	 * 打印脚本行数的初始值自动设为0。
	 * @param targetBlock
	 * 		开始遍历的Block节点。
	 */
	public ScriptPrintSpider(ScriptBlock targetBlock){
		super(targetBlock);
		lineCounter = 1;
		hierarchy = 0;
	}

	@Override
	protected void dealWithTargetBlock() {
		ScriptBlock subBlock = targetBlock.getSub();
		printString = "";
		
		//添加行数信息
		appendLineCounter();
		//添加层次信息
		appendHierarchy();
		//添加原脚本信息
		appendOriginalInformation();
		//其他的增加打印信息的过程，
		//这个方法主要用来方便子类扩展这个类能够打印的信息。
		appendSomeInformation();
		//向RLogger打印printString
		printString();
		
		++lineCounter;
		if (subBlock != null){
			dealWithSubBlock(subBlock);
		}
	}
	
	/**
	 * 处理下层的Spider。
	 * @param subBlock
	 * 		下一层的Spider，
	 * 		这个subBlock一定不为null，
	 * 		请放行使用。
	 */
	protected void dealWithSubBlock(ScriptBlock subBlock) {
		ScriptPrintSpider newScriptSpider = 
				new ScriptPrintSpider(subBlock, lineCounter, hierarchy + 1);
		newScriptSpider.workUntilEnd();
		lineCounter = newScriptSpider.getLineCounter();
	}

	/**
	 * 向printString的末尾添加targetBlock.information.getOriginalString()。
	 */
	protected void appendOriginalInformation() {
		printString += targetBlock.getInformation().getOriginalString();
	}

	/**
	 * 由子类来添加的其他要打印的信息。
	 */
	protected void appendSomeInformation() {
		//TODO let subClass extend this method
	}

	/**
	 * 向RLogger打印printString。
	 */
	protected void printString() {
		RLogger.log(printString);
	}

	/**
	 * 在printString的前面添加要打印的行数信息
	 */
	protected void appendLineCounter() {
		printString = "Line " + lineCounter + ": " + printString;
	}

	/**
	 * 在printString的前面添加要打印的层次符号
	 */
	protected void appendHierarchy() {
		for (int i = 0; i < hierarchy; ++i){
			printString += ScriptDeclaration.hierarchy;
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
