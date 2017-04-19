package unfinishedClass.customRClass.scriptBlock.spider.grammarSpider.forSequence;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.spider.basicToolSpider.DeleteSpider;
import unfinishedClass.customRClass.scriptBlock.spider.basicToolSpider.ReasonedErrorSpider;
import unfinishedClass.customRClass.scriptBlock.spider.grammarSpider.ScriptGrammarSpider;

/**
 * 检查加载序列中的所有脚本的语法。
 */
public class SequenceGrammarSpider extends DeleteSpider {

	public SequenceGrammarSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	protected void dealWithTargetBlock() {
		ScriptBlock subBlock = targetBlock.getSub();
		
		if (subBlock == null){
			deleteTarget = true;
			appendReason("脚本--" 
					+ targetBlock.getInformation().toString()
					+ "脚本内容为空");
		} else {
			ReasonedErrorSpider scriptGrammarSpider = 
					new ScriptGrammarSpider(subBlock);
			
			scriptGrammarSpider.workUntilEnd();
			
			if (scriptGrammarSpider.occurredError()){
				appendReason("脚本--" 
						+ targetBlock.getInformation().getOriginalString()
						+ "当中发生以下错误：\n" + scriptGrammarSpider.getErrorReason());
				deleteTarget = true;
			}
		}

	}

}
