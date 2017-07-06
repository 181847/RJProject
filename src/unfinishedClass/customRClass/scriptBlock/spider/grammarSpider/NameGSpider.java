package unfinishedClass.customRClass.scriptBlock.spider.grammarSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * 单个类名的检查，
 * 只是应用在RClass定义类名的时候使用，
 * 这个不同于RClassRefGSpider，
 * 不会向底层进一步检查。
 */
public class NameGSpider extends GrammarSpider {

	public NameGSpider(ScriptBlock targetBlock) {
		//两个记录槽。
		super(targetBlock, "类名语法检查", 2);
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
	
	@Override
	public boolean occurredError(){
		return super.occurredError()
				//缺少具体的类型声明或者过多的类型声明。
				|| 1 != getRecordOf(InformationType.CLASS_REF_CL);
	}

	@Override
	public String getRawReport(){
		StringBuffer appendReport = new StringBuffer();
		switch(getRecordOf(InformationType.CLASS_REF_CL)){
		case 0:
			appendReport.append("\n缺少具体的类名。");
		
		case 1:
			//正确情况。
			break;
			
		default:
			//多个类名声明。
			appendReport.append("\n过多的具体类名。");
		}
		
		return super.occurredError()
				+ appendReport.toString();
	}

	/**
	 * 将CLASS_REF_CL映射到1，
	 * 其他的映射到0。
	 */
	@Override
	protected int map_infoType_to_logIndex(InformationType infoType){
		switch(infoType){
		case CLASS_REF_CL:
			return 1;
			
		default:
			return 0;
		}
	}
}
