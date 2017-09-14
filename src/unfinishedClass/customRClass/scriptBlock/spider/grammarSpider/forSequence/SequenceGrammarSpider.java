package unfinishedClass.customRClass.scriptBlock.spider.grammarSpider.forSequence;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.spider.basicToolSpider.DeleteSpider;
import unfinishedClass.customRClass.scriptBlock.spider.basicToolSpider.ErrorSpider;
import unfinishedClass.customRClass.scriptBlock.spider.grammarSpider.ScriptGrammarSpider;

/**
 * 检查加载序列中的所有脚本的语法。
 */
public class SequenceGrammarSpider extends DeleteSpider {
	
	protected boolean encounterError;

	public SequenceGrammarSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		//初始时假定没有发生过错误。
		encounterError = false;
	}

	@Override
	public void countWork() {
		if (hasSubBlock){
			ErrorSpider scriptGSpider = 
					new ScriptGrammarSpider(subBlock);
			
			scriptGSpider.workUntilEnd();
			
			if (scriptGSpider.occurredError()){
				describeError("脚本--" 
						+ targetBlock
							.getInformation()
							.getOriginalString()	//获取脚本的详细路径信息。
						+ "当中发生以下错误：\n" + scriptGSpider.report());	//语法检查中发现的所有错误。
				
				//标记要删除这个节点。
				deleteTarget = true;
				//记录曾经发生过错误。
				encounterError = true;
			}
		} else {
			//记录错误信息。
			describeError("脚本--" 
					+ targetBlock
						.getInformation()
						.toString()		//获取脚本的详细路径信息。
					+ "中的内容为空");

			//标记要删除这个节点。
			deleteTarget = true;
			//记录曾经发生过错误。
			encounterError = true;
		}
	}

	/**
	 * 检查过程中是否曾经发生过错误。
	 */
	@Override
	public boolean occurredError() {
		return encounterError;
	}
}
