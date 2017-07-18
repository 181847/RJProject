package unfinishedClass.customRClass.scriptBlock.spider.grammarSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * Function定义中的注释信息不能为空，
 * 至少为一个空格。
 * @author 75309
 *
 */
public class CommentGSpider extends DeclarGSpider {

	public CommentGSpider(ScriptBlock targetBlock) {
		//两个记录槽。
		super(targetBlock, "注释检查", 2);
	}
	
	@Override
	protected void declarGrammarWork() {
		switch(infoType){
		case RECT:
			//检查方形区域下面的具体
			sendSpider(
					new SingleTypeGSpider(
							subBlock, 
							InformationType.INFO_COMMENT));
			break;
		default:
			dealWith_Unexpected();
			break;
		}
	}
	
	/**
	 * 防止
	 * 缺少方形区域声明。
	 */
	@Override
	public boolean occurredError(){
		return super.occurredError()
				//缺少方形区域声明。
				|| 0 == getRecordOf(InformationType.RECT);
	}
	
	/**
	 * 增加以下相关警告：
	 * 缺少注释方形区域。
	 */
	@Override
	public String getRawReport(){
		String appendReport = "";

		if (0 == getRecordOf(InformationType.RECT)){
			appendReport = "\n缺少注释方形区域";
		}
		
		return super.getRawReport()
				+ appendReport.toString();
	}
	
	/**
	 * 映射
	 * RECT，
	 * 其他的映射到0。
	 */
	protected int map_infoType_to_logIndex(InformationType infoType){
		switch(infoType){
		case RECT:
			return 1;
			
		default:
			return 0;
		}
	}

}
