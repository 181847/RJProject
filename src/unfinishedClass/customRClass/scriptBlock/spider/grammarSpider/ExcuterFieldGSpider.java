package unfinishedClass.customRClass.scriptBlock.spider.grammarSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;

public class ExcuterFieldGSpider extends DeclarGSpider {

	public ExcuterFieldGSpider(ScriptBlock targetBlock) {
		//三个记录槽。
		super(targetBlock, "执行出口区域", 3);
	}

	@Override
	protected void declarGrammarWork() {
		switch(infoType) {
		case DECLAR_EXCUTERS_NORMAL:
			//普通执行出口区域。
		case DECLAR_EXCUTERS_EXCEPTION:
			//异常执行出口区域。
			//两种类型的执行出口区域使用相同的GSpider进行检查。
			sendSpider(new SingleTypeGSpider(subBlock, InformationType.EXCUTER));
			break;
			
		default:
			dealWith_Unexpected();
			break;
		}
	}
	
	/**
	 * 增加以下相关警告：
	 * 缺少普通执行出口区域，
	 * 过多普通执行出口区域，
	 * 过多异常执行出口区域。
	 */
	@Override
	public boolean occurredError(){
		return super.occurredError()
				//缺少普通执行入口区域或者普通执行入口区域过多。
				|| 1 != getRecordOf(InformationType.DECLAR_EXCUTERS_NORMAL)
				//异常执行出口区域声明过多。
				|| 1 < getRecordOf(InformationType.DECLAR_EXCUTERS_EXCEPTION);
	}
	
	/**
	 * 增加以下相关警告：
	 * 缺少普通执行出口区域，
	 * 过多普通执行出口区域，
	 * 过多异常执行出口区域。
	 */
	@Override
	public String getRawReport(){
		StringBuffer appendReport = new StringBuffer();
		

		//普通执行出口。
		switch(getRecordOf(InformationType.DECLAR_EXCUTERS_NORMAL)){
		case 0:
			appendReport.append("\n缺少普通执行出口区域。");
			break;
			
		case 1:
			//正确情况。
			break;
			
		default:
			appendReport.append("\n过多的普通执行出口区域。");
			break;
		}
		
		//异常执行出口。
		if (1 < getRecordOf(InformationType.DECLAR_EXCUTERS_EXCEPTION)){
			appendReport.append("\n过多的异常执行出口区域。");
		}
		
		return super.getRawReport()
				+ appendReport.toString();
	}
	
	/**
	 * 映射
	 * DECLAR_EXCUTERS_EXCEPTION/
	 * DECLAR_EXCUTERS_NORMAL，
	 * 其他的映射到0。
	 */
	protected int map_infoType_to_logIndex(InformationType infoType){
		switch(infoType){
		case DECLAR_EXCUTERS_EXCEPTION:
			return 1;
			
		case DECLAR_EXCUTERS_NORMAL:
			return 2;
			
		default:
			return 0;
		}
	}

}
