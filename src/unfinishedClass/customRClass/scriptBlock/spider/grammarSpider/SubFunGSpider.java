package unfinishedClass.customRClass.scriptBlock.spider.grammarSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * 检查子Fun声明。
 */
public class SubFunGSpider extends DeclarGSpider {

	public SubFunGSpider(ScriptBlock targetBlock) {
		//两个记录槽。
		super(targetBlock, "子Fun语法检查", 2);
	}

	@Override
	protected void declarGrammarWork() {
		if (infoType == InformationType.SUBFUN) {
			//检查子Fun的具体声明。
			sendSpider(new SingleSubFunGSpider(subBlock));
		} else {
			dealWith_Unexpected();
		}
	}
	
	/**
	 * 增加警告缺少SubFun声明。
	 */
	@Override
	public boolean occurredError(){
		return super.occurredError()
				//缺少具体的类型声明或者过多的类型声明。
				|| 0 == getRecordOf(InformationType.SUBFUN);
	}
	
	/**
	 * 增加警告缺少SubFun声明。
	 */
	@Override
	public String getRawReport(){
		String appendReport = "";
		
		if ( 0 ==  getRecordOf(InformationType.SUBFUN)){
			appendReport = "\n缺少SubFun声明。";
		}
		
		return super.getRawReport()
				+ appendReport.toString();
	}

	/**
	 * 将SUBFUN映射到1，
	 * 其他的映射到0。
	 */
	protected int map_infoType_to_logIndex(InformationType infoType){
		switch(infoType){
		case SUBFUN:
			return 1;
			
		default:
			return 0;
		}
	}
}
