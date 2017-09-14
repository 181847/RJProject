package unfinishedClass.customRClass.scriptBlock.spider.grammarSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * 只对单一指定信息类型进行检查，
 * 一，防止子链中存在除指定信息之外的其他类型信息；
 * 二，防止子链中没有任何类型的信息。
 */
public class SingleTypeGSpider extends GrammarSpider {
	InformationType checkInfoType;

	public SingleTypeGSpider(ScriptBlock targetBlock, InformationType targetInfoType) {
		//两个记录槽。
		super(targetBlock, "单一类型语法检查", 2);
		checkInfoType = targetInfoType;
	}

	@Override
	protected void grammarWork() {
		if (infoType == checkInfoType){
			//正确情况。
		} else {
			dealWith_Unexpected();
		}
	}
	
	/**
	 * 增加警告缺少指定信息。
	 */
	@Override
	public boolean occurredError(){
		return super.occurredError()
				//缺少具体的类型声明或者过多的类型声明。
				|| 0 == getRecordOf(checkInfoType);
	}
	
	/**
	 * 增加警告缺少指定信息。
	 */
	@Override
	public String getRawReport(){
		String appendReport = "";
		
		if ( 0 == getRecordOf(checkInfoType)){
			appendReport = "\n缺少指定信息。";
		}
		
		return super.occurredError()
				+ appendReport.toString();
	}
	
	/**
	 * 只分析指定信息，
	 * 其他的映射到0。
	 */
	protected int map_infoType_to_logIndex(InformationType infoType){
		if (infoType == checkInfoType){
			return 1;
		} else {
			return 0;
		}
	}
}
