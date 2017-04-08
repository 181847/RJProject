package unfinishedClass.customRClass.scriptBlock;

import java.util.ArrayList;

import basicTool.RLogger;
import unfinishedClass.customRClass.scriptBlock.information.RawScriptInformation;

/**
 * 从一个ScriptBlock开始，为其后部添加其他的Block，
 * 根据script当中的设定好的targetLine开始进行Block的添加。
 */
public class ScriptBlockChainGenerator {
	protected boolean failed;
	protected ArrayList<String> script;
	protected ScriptBlock targetBlock;
	protected int targetLine;
	protected int scriptSize;
	protected int topHierarchy;
	protected int currentHierarchy;
	
	/**
	 * @param targetBlock
	 * 		从哪个ScriptBlock的后面开始生成Chain。
	 * @param script
	 * 		需要的脚本文件。
	 * @param startLine
	 * 		从script的第几号元素开始生成Chain。
	 */
	public ScriptBlockChainGenerator(ScriptBlock targetBlock,
			ArrayList<String> script,
			int startLine){
		this.targetBlock = targetBlock;
		this.script = script;
		targetLine = startLine;
		scriptSize = script.size();
		currentHierarchy = 
				topHierarchy = 
					ScriptBlockHelper.calculateHierarchy(script.get(startLine));
		failed = false;
	}

	/**
	 * @return
	 * 		上次生成Chain是否成功。
	 */
	public boolean failed(){
		return failed;
	}
	
	/**
	 * 生成BlockChain。
	 */
	public void generate(){
		int nextHierarchy;
		//hierarchyDiff = this.currentHierarchy - nextHierarchy;
		int hierarchyDiff;
		String scriptLine;
		
		for (; targetLine < scriptSize; ++ targetLine){
			scriptLine = script.get(targetLine);
			nextHierarchy = ScriptBlockHelper.calculateHierarchy(scriptLine);
			hierarchyDiff = currentHierarchy - nextHierarchy;
			
			if (hierarchyDiff == -1){
			//被添加信息的层次比当前工作的层次低一层。
				turnDown();
				
			} else if (hierarchyDiff > 0){
			//被添加信息的层次比当前工作的层次高，
			//将工作空间向上转。
				while(hierarchyDiff-- > 0){
					if ( ! turnUp()){
						//工作空间上转失败
						RLogger.logError("ScriptBlockChainGenerator.generate()", 
								"工作空间层次上转失败",
								"可能是由于初次设置的ScriptBlock的层次较低，"
								+ "无法向上转太多次，"
								+ "或者当前层次中不存在头结点，"
								+ "没有找到上一层次的结点。",
								"最后一次上转所依靠的的ScriptBlock的Information：" 
								+ targetBlock.getInformation());
						
						//上转失败不会使得本次生成的结果为false，
						//只是会立马结束本次生成过程。
						return;
					}
				}
			} else if (hierarchyDiff < -1){
			//被添加信息的层次比当前工作的层次低至少两层，
			//这样子的跨层次信息是非法的，
			//设定本次生成的结果为false。
				this.failed = true;
				return;
			}//if
			
			appendBlock(scriptLine);
		}//for
	}

	/**
	 * 向targetBlock的后面添加包含scriptLine的ScriptBlock结点。
	 * @param scriptLine
	 * 		被添加进新的ScriptBlock中的信息。
	 */
	public void appendBlock(String scriptLine) {
		// TODO Auto-generated method stub
		ScriptBlock newBlock = 
				new ScriptBlock(
						new RawScriptInformation(
								ScriptBlockHelper.trimScriptLine(scriptLine)), 
						false);
		
		newBlock.follow(targetBlock);
		targetBlock = newBlock;
	}

	/**
	 * 工作空间从targetBlock抓到targetBlock的上一层的队尾。
	 * @return
	 * 		如果本次上转成功，返回true；
	 * 		上转失败，返回false。
	 */
	public boolean turnUp() {
		// TODO Auto-generated method stub
		
		ScriptBlock upBlock = targetBlock.getNext();
		
		while( ! upBlock.isHead() && upBlock != targetBlock){
			upBlock = upBlock.getNext();
		}
		
		//找到本层头结点，上转。
		if (upBlock.isHead()){
			targetBlock = upBlock.getSub();
			--currentHierarchy;
			return true;
		} else {
			//没有找到头结点，上转失败，
			//返回false。
			return false;
		}
	}

	/**
	 * 将工作空间从targetBlock转到targetBlock.sub，
	 * 如果targetBlock.sub == null，
	 * 就为其设定一个新的headBlock，
	 * 然后转到这个新的headBlock上，
	 * 如果targetBlock.sub != null，
	 * 就转到targetBlock.sub的前一个节点上。
	 * @return
	 * 		下转成功返回true;
	 * 		下转失败返回false。
	 */
	public void turnDown() {
		if (targetBlock.getSub() == null){
			targetBlock.include(ScriptBlockHelper.getNewContentHead());
		}

		targetBlock = targetBlock.getSub().getPrec();
		++currentHierarchy;
	}
}
