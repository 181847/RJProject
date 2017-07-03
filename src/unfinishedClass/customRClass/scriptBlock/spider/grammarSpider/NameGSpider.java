package unfinishedClass.customRClass.scriptBlock.spider.grammarSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;

public class NameGSpider extends GrammarSpider {

	public NameGSpider(ScriptBlock targetBlock) {
		super(targetBlock, "类名语法检查");
	}

	@Override
	protected void grammarWork() {
		switch(count){
		case 1:
			if (infoType != InformationType.CLASS_REF_CL){
				dealWith_Unexpected();
			}
			break;
			
		default:
			dealWith_Unexpected();
			break;
		}
	}
}
