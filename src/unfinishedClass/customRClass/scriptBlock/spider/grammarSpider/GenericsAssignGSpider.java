package unfinishedClass.customRClass.scriptBlock.spider.grammarSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * 检查一系列的泛参指定。
 */
public class GenericsAssignGSpider extends GrammarSpider {

	public GenericsAssignGSpider(ScriptBlock targetBlock) {
		//三个记录槽。
		super(targetBlock, "泛参指定语法检查", 3);
	}

	@Override
	protected void grammarWork() {
		switch(infoType){
		case GP_ASSIGN_CL:
			if (hasSubBlock){
				sendSpider(new GenericsAssignGSpider(subBlock));
			}
			break;
		case GP_ASSIGN_GP:
			//在AnalysisSpider阶段就已经保证
			//指定为泛参的泛参指定不会拥有子链，
			//这里无需对子链进行检查。
			break;
			
		default:
			dealWith_Unexpected();
			break;
		}
	}
	
	/**
	 * 增加警告缺少泛参 指定。
	 */
	@Override
	public boolean occurredError(){
		return super.occurredError()
				//缺少泛参指定。
				|| 0 == (getRecordOf(InformationType.GP_ASSIGN_CL));
	}
	
	/**
	 * 增加警告缺少泛参指定。
	 */
	@Override
	public String getRawReport(){
		String appendReport = "";
		
		if ( 0 == getRecordOf(InformationType.GP_ASSIGN_CL)){
			appendReport = "\n缺少具体的泛参指定。";
		}
		
		return super.occurredError()
				+ appendReport;
	}

	/**
	 * 映射
	 * GP_ASSIGN_CL/
	 * GP_ASSIGN_GP，
	 * 其他的映射到0。
	 */
	protected int map_infoType_to_logIndex(InformationType infoType){
		switch(infoType){
		case GP_ASSIGN_CL:
		case GP_ASSIGN_GP:
			return 1;
			
		default:
			return 0;
		}
	}
}
