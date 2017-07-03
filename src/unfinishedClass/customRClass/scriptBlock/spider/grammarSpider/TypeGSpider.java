package unfinishedClass.customRClass.scriptBlock.spider.grammarSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

/**
 * 对具体的类型声明进行检查，
 * 要求只能由一个Block，
 * 这个Block的InfoType必须是这三种中的某一种：
 * INTERFACE, ABSTRACT_RCLASS, RCLASS。
 */
public class TypeGSpider extends GrammarSpider {

	public TypeGSpider(ScriptBlock targetBlock) {
		super(targetBlock, "类型语法检查");
	}

	@Override
	protected void grammarWork() {
		switch(count){
		case 1:
			switch(infoType){
			case INTERFACE:
			case ABSTRACT_RCLASS:
			case RCLASS:
				//正确情况，没有发生错误。
				break;
			default:
				dealWith_Unexpected();
				break;
			}//switch
			break;
			
		default://超过一个Block，在这样一个Block链上应该只有一个具体的类型声明
			//记录错误信息。
			dealWith_Unexpected();
			break;
		}
	}
}
