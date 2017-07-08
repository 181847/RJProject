package unfinishedClass.customRClass.scriptBlock.spider.grammarSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * 检查一连串的RClass引用，
 * 可指定被检查的RClass引用的数量，
 * 超过这个数量的Block将会被视为Unexpected，
 * 无意义Block数量加一。
 */
public class RClassRefGSpider extends GrammarSpider {
	/**
	 * 对引用数量的要求，
	 * 用来记录正确的RClass引用数量，
	 * 当超过此引用数量的时候，
	 * 认为被检查的Block是VOID的类型。
	 */
	protected int limit;

	/**
	 * @param targetBlock
	 * 		目标Block。
	 * @param limit
	 * 		对可检查的Block数量进行检查，
	 * 		超出此数量的Block将会被强制认为是VOID类型。
	 */
	public RClassRefGSpider(ScriptBlock targetBlock, int limit) {
		//两个记录槽。
		super(targetBlock, "RClass引用", 2);
		this.limit = limit;
	}
	
	/**
	 * 不对Block的检查数量做限制。
	 */
	public RClassRefGSpider(ScriptBlock targetBlock) {
		//两个记录槽。
		super(targetBlock, "RClass引用", 2);
		this.limit = Integer.MAX_VALUE;
	}

	@Override
	protected void grammarWork() {
		if (count > limit){
			//超出检查数量限制。
			//当做意外情况处理。
			dealWith_Unexpected();
		} else {
			switch(infoType){
			case CLASS_REF_CL:
				if (hasSubBlock){
					//引用实类型的情况下，
					//如果有子链，
					//对子链中的泛参指定进行语法检查。
					sendSpider(new GenericsAssignGSpider(subBlock));
				}
				break;
				
			case CLASS_REF_GP:
				//引用泛参的情况下不会对子链进行检查，
				//因为在AnalysisSpider阶段就已经有这样的规定，
				//如果Block被确定为CLASS_REF_GP，
				//同时拥有子链，
				//则这种Block的infoType会重新被设置为VOID，
				//因为泛参引用是不能拥有泛参指配的，
				//不需要任何子链。
				break;
				
			default:
				//意外发生。
				dealWith_Unexpected();
				break;
			}
		}
	}
	
	@Override
	public boolean occurredError(){
		int refNum = getRecordOf(InformationType.CLASS_REF_CL);
		return super.occurredError()
				//没有类引用。
				|| 0 == refNum 
				//类引用数量超出范围。
				|| refNum > limit;
	}
	
	/**
	 * 检查类引用的数量是否符合要求。
	 */
	@Override
	public String getRawReport(){
		StringBuffer appendReport = new StringBuffer();
		
		switch(getRecordOf(InformationType.CLASS_REF_CL)){
		case 0:
			appendReport.append("\n缺少类引用。");
			break;
			
		case 1:
			//正确情况
			break;
			
		default:
			//多个具体的类型声明。
			appendReport.append("\n过多的类引用。");
		}
		
		return super.getRawReport()
				+ appendReport.toString();
	}
	
	/**
	 * 只识别CLASS_REF_CL/CLASS_REF_GP，
	 * 其他的映射到0。
	 */
	protected int map_infoType_to_logIndex(InformationType infoType){
		switch(infoType){
		case CLASS_REF_CL:
		case CLASS_REF_GP:
			return 1;
			
		default:
			return 0;
		}
	}
}
