package unfinishedClass.customRClass.scriptBlock.spider.grammarSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * 单一类型检查，
 * 在Block链当中要求有且只有一种InformationType的Block。
 */
public class SingleTypeGrammarSpider extends GrammarSpider {
	protected InformationType checkType;

	/**
	 * 默认发生错误。
	 * @param checkType
	 * 		指定的唯一的合法的信息类型
	 * @param spiderDescription
	 * 		描述当前Spider，这个描述将会被添加进ErrorReason当中，
	 * 		使得输出错误原因的时候能够知道错误是在什么Spider的检查下发生的。
	 */
	public SingleTypeGrammarSpider(ScriptBlock targetBlock, InformationType checkType,
			String spiderDescription) {
		super(targetBlock, spiderDescription);
		this.checkType = checkType;
	}

	@Override
	protected void dealWithTargetBlock() {
		Information information = targetBlock.getInformation();
		
		if (information.getType() == checkType){
			//处理指定类型的targetBlock
			dealWith_SingleType();
			return;
		}
		
		switch(information.getType()){
		case VOID:
			dealWith_VOID();
			break;
		default:
			dealWith_Unexpected();
			break;
		}
	}

	/**
	 * 处理指定信息类型的targetBlock。
	 */
	protected void dealWith_SingleType() {
		foundOneTaggle();
	}

}
