package unfinishedClass.customRClass.scriptBlock.spider.grammarSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * 检查弧线声明。
 */
public class ArcFieldGSpider extends DeclarGSpider {

	public ArcFieldGSpider(ScriptBlock targetBlock) {
		//3个记录槽。
		super(targetBlock, "弧线语法检查", 3);
	}

	@Override
	protected void declarGrammarWork() {
		switch(infoType){
		case DECLAR_ARCS_E_TO_E:
			//执行弧线区域。
		case DECLAR_ARCS_R_TO_P:
			//参数弧线区域。
			
			//两种情况都使用下面这个方法来检查具体的弧线声明。
			sendSpider(new ArcGSpider(subBlock));
			break;
			
		default:
			dealWith_Unexpected();
			break;
		}
	}
	
	/**
	 * 增加以下相关警告：
	 * 缺少执行弧线区域，
	 * 过多执行弧线区域，
	 * 过多参数弧线区域。
	 */
	@Override
	public boolean occurredError(){
		return super.occurredError()
				//缺少执行弧线区域或者执行弧线区域过多。
				|| 1 != getRecordOf(InformationType.DECLAR_ARCS_E_TO_E)
				//参数弧线区域声明过多。
				|| 1 < getRecordOf(InformationType.DECLAR_ARCS_R_TO_P);
	}
	
	/**
	 * 增加以下相关警告：
	 * 缺少执行弧线区域，
	 * 过多执行弧线区域，
	 * 过多参数弧线区域。
	 */
	@Override
	public String getRawReport(){
		StringBuffer appendReport = new StringBuffer();
		

		//普通执行出口。
		switch(getRecordOf(InformationType.DECLAR_ARCS_E_TO_E)){
		case 0:
			appendReport.append("\n缺少执行弧线区域。");
			break;
			
		case 1:
			//正确情况。
			break;
			
		default:
			appendReport.append("\n过多的执行弧线区域。");
			break;
		}
		
		//异常执行出口。
		if (1 < getRecordOf(InformationType.DECLAR_ARCS_R_TO_P)){
			appendReport.append("\n过多的参数弧线区域。");
		}
		
		return super.getRawReport()
				+ appendReport.toString();
	}
	
	/**
	 * 映射
	 * DECLAR_ARCS_E_TO_E/
	 * DECLAR_ARCS_R_TO_P，
	 * 其他的映射到0。
	 */
	protected int map_infoType_to_logIndex(InformationType infoType){
		switch(infoType){
		case DECLAR_ARCS_E_TO_E:
			return 1;
			
		case DECLAR_ARCS_R_TO_P:
			return 2;
			
		default:
			return 0;
		}
	}

}
