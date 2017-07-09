package unfinishedClass.customRClass.scriptBlock.spider.grammarSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * 对一个SubFun中的声明信息进行语法检查。
 */
public class SingleSubFunGSpider extends GrammarSpider {

	public SingleSubFunGSpider(ScriptBlock targetBlock) {
		//5个记录槽。
		super(targetBlock, "单个SubFun语法检查", 5);
	}

	@Override
	protected void grammarWork() {
		switch(infoType){
		case CLASS_REF_CL:
			//借用RClassRefGSpider对当前Block及其子链进行进一步检查。
			borrowSpider(new RClassRefGSpider(targetBlock));
			break;
			
		case CLASS_REF_GP:
			//引用类型为泛参则无需检查子链，
			//因为AnalysisSpider阶段就已经保证引用泛参的类型不能有子链。
			break;
			
		case LOCATION:
			//坐标信息是否有子信息不要紧，
			//只要当前Block是LOCATION类型就行，
			//不对子链进行进一步检查。
			break;
			
		case DECLAR_GP_ASSIGN:
			if (hasSubBlock){
				//检查泛参指定。
				sendSpider(new GenericsAssignGSpider(subBlock));	
			} else {
				//没有子信息，处理意外情况。
				dealWith_Unexpected();
				//记录无意义Block数量。
				recordNonesense();
			}
			break;
			
		case DECLAR_MODIFY:
			if (hasSubBlock){
				//检查单一的修改信息类型。
				sendSpider(new SingleTypeGSpider(subBlock, InformationType.INFO_MODIFY));	
			} else {
				//没有子信息，处理意外情况。
				dealWith_Unexpected();
				//记录无意义Block数量。
				recordNonesense();
			}
			break;
			
		default:
			dealWith_Unexpected();
		}
	}
	
	/**
	 * 防止过多类型引用、
	 * 坐标、
	 * 泛参指定声明、
	 * 修改信息声明。
	 */
	@Override
	public boolean occurredError(){
		return super.occurredError()
				//类型引用只能有一个。
				|| 1 != getRecordOf(InformationType.CLASS_REF_CL)
				//坐标信息只能由一个。
				|| 1 != getRecordOf(InformationType.LOCATION)
				//泛参指定不能有多个。
				|| 1 < getRecordOf(InformationType.DECLAR_GP_ASSIGN)
				//修改信息不能有多个。
				|| 1 < getRecordOf(InformationType.DECLAR_MODIFY);
	}
	
	/**
	 * 防止过多类型引用、
	 * 坐标、
	 * 泛参指定声明、
	 * 修改信息声明。
	 */
	@Override
	public String getRawReport(){
		StringBuffer appendReport = new StringBuffer();
		
		switch(getRecordOf(InformationType.CLASS_REF_CL)){
		case 0:
			appendReport.append("\n缺少类型引用。");
			break;
			
		case 1:
			//正确情况。
			break;
			
		default:
			appendReport.append("\n过多的类型引用。");
			break;
		}
		
		switch(getRecordOf(InformationType.LOCATION)){
		case 0:
			appendReport.append("\n缺少坐标信息。");
			break;
			
		case 1:
			//正确情况。
			break;
			
		default:
			appendReport.append("\n过多的坐标信息。");
			break;
		}

		if (1 < getRecordOf(InformationType.DECLAR_GP_ASSIGN)){
			appendReport.append("\n过多的泛参指定区域。");
		}
		
		if (1 < getRecordOf(InformationType.DECLAR_MODIFY)){
			appendReport.append("\n过多的修改信息区域。");
		}
		
		return super.occurredError()
				+ appendReport.toString();
	}
	
	/**
	 * 映射
	 * DECLAR_EXCUTEES/
	 * DECLAR_PARAMETERS/
	 * DECLAR_EXCUTERS/
	 * DECLAR_RETURNVAL/
	 * DECLAR_LOCALVARS/
	 * DECLAR_ARCS/
	 * DECLAR_COMMENTS，
	 * 其他的映射到0。
	 */
	protected int map_infoType_to_logIndex(InformationType infoType){
		switch(infoType){
		case CLASS_REF_CL:
		case CLASS_REF_GP:
			return 1;
			
		case LOCATION:
			return 2;
			
		case DECLAR_GP_ASSIGN:
			return 3;
			
		case DECLAR_MODIFY:
			return 4;
			
		default:
			return 0;
		}
	}

}
