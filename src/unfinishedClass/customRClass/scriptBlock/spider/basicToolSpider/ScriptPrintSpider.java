package unfinishedClass.customRClass.scriptBlock.spider.basicToolSpider;

import basicTool.RLogger;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.ScriptDeclaration;
import unfinishedClass.customRClass.scriptBlock.spider.CountableSpider;

/**
 * 遍历所有的Block，
 * 打印block中存储的原生脚本信息。
 */
public class ScriptPrintSpider extends CountableSpider {
	/**
	 * 当前Block链上的Block的层次数。
	 */
	protected int hierarchy;
	
	/**
	 * 下一个被打印的Block所属的行数。
	 */
	protected int lineCounter;
	
	/**
	 * 单次Block被打印的所有信息的缓冲。
	 */
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
	
	/**
	 * @param hierarchy
	 * 		设置下一个被打印的层次数。
	 */
	public void setHierarchy(int hierarchy) {
		this.hierarchy = hierarchy;
	}

	/**
	 * @param lineCounter
	 * 		设置下一个被打印的行数。
	 */
	public void setLineCounter(int lineCounter) {
		this.lineCounter = lineCounter;
	}

	/**
	 * @return
	 * 		获取下一个被打印的信息的行数。
	 */
	public int getLineCounter() {
		return lineCounter;
	}

	/**
	 * 子类通过修改这个方法来实现对子链的打印，
	 * 在这个方法中调用sendSpider可以实现对子链的连续打印，
	 * 比如行数连续，
	 * 层次会进行划分，
	 * sendSpider会将传入的sps的行数和层次进行更新，
	 * 与本链的行数保持连贯，
	 * 层次进行区分。<br>
	 * 另外，这个方法只有在包含子链的时候才会调用，
	 * 子类无需担心调用这个方法是子链是否为空。
	 */
	protected void printSubBlock() {
		//向子链发出另一个ScriptPrintSpider，
		//继续打印子信息。
		sendSpider(
				new ScriptPrintSpider(subBlock));
	}
	
	/**
	 * 默认将子链和本链的层次进行区分，
	 * 行数连贯起来。
	 * @param sps
	 * 		已经放置到子链上的ScriptPrintSpider。
	 */
	protected void sendSpider(ScriptPrintSpider sps){
		//设置子链的层次加一。
		sps.setHierarchy(hierarchy + 1);
		
		//设置下一个子链的起始行数计数。
		sps.setLineCounter(lineCounter);
		sps.workUntilEnd();
		lineCounter = sps.getLineCounter();
	}

	@Override
	public void countWork() {

		//初始化打印字符串，
		//将printString置为空。
		printString = "";
		
		//子类可以覆盖这个方法来添加这个类需要的子信息。
		modifyPrintString();
		
		//向RLogger打印printString
		printString();
		
		//行数记录加一。
		++lineCounter;
		if (hasSubBlock){
			//打印子链中的信息。
			printSubBlock();
		}
	}

	/**
	 * 向RLogger打印printString。
	 */
	protected void printString() {
		RLogger.log(printString);
	}
	
	/**
	 * 在这个方法中对要打印的信息进行自定义。
	 */
	protected void modifyPrintString() {
		//在首部添加行数。
		appendLineCounter();
		
		//添加层次符号，
		//几个制表符。
		appendHierarchy();
		
		//添加原始脚本信息。
		appendOriginalString();
	}
	
	/**
	 * 打印信息换行。
	 */
	protected void newLine() {
		printString += '\n'; 
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

	/**
	 * 添加脚本原始信息。
	 */
	protected void appendOriginalString() {
		printString += 
				targetBlock
				.getInformation()
				.getOriginalString();
	}

}
