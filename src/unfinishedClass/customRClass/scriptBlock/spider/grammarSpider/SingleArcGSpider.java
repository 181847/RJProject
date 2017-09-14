package unfinishedClass.customRClass.scriptBlock.spider.grammarSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;

public class SingleArcGSpider extends GrammarSpider {

	public SingleArcGSpider(ScriptBlock targetBlock) {
		//3个记录槽。
		super(targetBlock, "弧线详细语法检查", 3);
	}

	@Override
	protected void grammarWork() {
		switch(infoType){
		case ARC_START:
			//弧线头。
			break;
			
		case ARC_END:
			//弧线尾。
			break;
			
		default:
			dealWith_Unexpected();
			break;
		}
	}
	
	/**
	 * 增加以下相关警告：
	 * 没有唯一的弧线头声明，
	 * 没有唯一的弧线尾声明。
	 */
	@Override
	public boolean occurredError(){
		return super.occurredError()
				//缺少执行弧线区域或者执行弧线区域过多。
				|| 1 != getRecordOf(InformationType.ARC_START)
				//参数弧线区域声明过多。
				|| 1 != getRecordOf(InformationType.ARC_END);
	}
	
	/**
	 * 增加以下相关警告：
	 * 没有唯一的弧线头声明，
	 * 没有唯一的弧线尾声明。
	 */
	@Override
	public String getRawReport(){
		StringBuffer appendReport = new StringBuffer();
		

		//弧线头声明。
		switch(getRecordOf(InformationType.ARC_START)){
		case 0:
			appendReport.append("缺少弧线头声明。");
			break;
			
		case 1:
			//正确情况。
			break;
			
		default:
			appendReport.append("过多的弧线头声明。");
			break;
		}
		
		//弧线尾声明。
		switch(getRecordOf(InformationType.ARC_END)){
		case 0:
			appendReport.append("缺少弧线尾端声明。");
			break;
			
		case 1:
			//正确情况。
			break;
			
		default:
			appendReport.append("过多的弧线尾端声明。");
			break;
		}
		
		return super.occurredError()
				+ appendReport.toString();
	}
	
	/**
	 * 映射
	 * ARC_START/
	 * ARC_END，
	 * 其他的映射到0。
	 */
	protected int map_infoType_to_logIndex(InformationType infoType){
		switch(infoType){
		case ARC_START:
			return 1;
			
		case ARC_END:
			return 2;
			
		default:
			return 0;
		}//end switch
	}
}
