package unfinishedClass.customRClass.scriptBlock.spider.grammarSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * 对具体的类型声明进行检查，
 * 要求只能由一个Block，
 * 这个Block的InfoType必须是这三种中的某一种：
 * INTERFACE, ABSTRACT_RCLASS, RCLASS。
 */
public class TypeGSpider extends GrammarSpider {

	public TypeGSpider(ScriptBlock targetBlock) {
		//两个记录槽。
		super(targetBlock, "类型语法检查", 2);
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
			
		default:
			//超过一个Block，在这样一个Block链上应该只有一个具体的类型声明
			//记录错误信息。
			dealWith_Unexpected();
			break;
		}
	}
	
	/**
	 * 增加警告缺少类型声明或者类型声明过多。
	 */
	@Override
	public boolean occurredError(){
		return super.occurredError()
				//缺少具体的类型声明或者过多的类型声明。
				|| 1 != getRecordOf(InformationType.RCLASS);
	}
	
	/**
	 * 增加警告缺少类型声明或者类型声明过多。
	 */
	@Override
	public String getRawReport(){
		StringBuffer appendReport = new StringBuffer();
		
		switch(getRecordOf(InformationType.RCLASS)){
		case 0:
			appendReport.append("\n缺少具体的类型声明。");
			break;
			
		case 1:
			//正确情况
			break;
			
		default:
			//多个具体的类型声明。
			appendReport.append("\n过多的具体类型声明。");
		}
		
		return super.occurredError()
				+ appendReport.toString();
	}
	
	/**
	 * 将INTERFACE/ABSTRACT_RCLASS/RCLASS映射到1，
	 * 其他的映射到0。
	 */
	protected int map_infoType_to_logIndex(InformationType infoType){
		switch(infoType){
		case INTERFACE:
		case ABSTRACT_RCLASS:
		case RCLASS:
			return 1;
			
		default:
			return 0;
		}
	}
}
