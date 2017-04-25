package unfinishedClass.customRClass.scriptBlock.spider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.spider.infoSpider.ScriptInfoSpider;

/**
 * 将加载序列中的每个脚本中的信息，
 * 单独用一个结构化的类型来存储，
 * 这个结构化的类型将会被用来直接生成CustomRClass，
 * 在这里的Block链必须保证其正确性，
 * 收集信息的过程不会检查任何语法错误。
 */
public class SequenceInformationSpider extends AbstractBCSpider {

	public SequenceInformationSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	protected void dealWithTargetBlock() {
		Information information = targetBlock.getInformation();
		
		ScriptInfoSpider infoSpider = 
				new ScriptInfoSpider(information.getOriginalString(),targetBlock.getSub());
		infoSpider.workUntilEnd();
		
		//根据ScriptInfoSpider的工作成果
		//生成新的Information来存储RClass的结构信息。
		information = 
				new RClassStructInformation(
						information.getOriginalString(),
						infoSpider.getRClassStruct());
		
		targetBlock.setInformation(information);
	}

}
