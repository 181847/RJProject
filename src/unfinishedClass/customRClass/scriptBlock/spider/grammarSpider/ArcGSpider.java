package unfinishedClass.customRClass.scriptBlock.spider.grammarSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;

public class ArcGSpider extends DeclarGSpider {

	public ArcGSpider(ScriptBlock targetBlock) {
		//两个记录槽。
		super(targetBlock, "单一类型弧线区域语法检查", 2);
	}

	@Override
	protected void declarGrammarWork() {
		switch(infoType){
		case ARC:
			sendSpider(new SingleArcGSpider(subBlock));
			break;
			
		default:
			dealWith_Unexpected();
			break;
		}
	}

	/**
	 * 增加以下相关警告：
	 * 缺少弧线声明。
	 */
	@Override
	public boolean occurredError(){
		return super.occurredError()
				//缺少弧线声明。
				|| 0 == getRecordOf(InformationType.ARC);
	}
	
	/**
	 * 增加以下相关警告：
	 * 缺少弧线声明。
	 */
	@Override
	public String getRawReport(){
		String appendReport = "";
		
		if (0 == getRecordOf(InformationType.ARC)){
			appendReport = "\n缺少弧线声明。";
		}
		
		return super.occurredError()
				+ appendReport.toString();
	}
	
	/**
	 * 映射
	 * ARC，
	 * 其他的映射到0。
	 */
	protected int map_infoType_to_logIndex(InformationType infoType){
		switch(infoType){
		case ARC:
			return 1;
			
		default:
			return 0;
		}
	}
}
