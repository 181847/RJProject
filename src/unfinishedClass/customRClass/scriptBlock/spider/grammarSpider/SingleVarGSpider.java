package unfinishedClass.customRClass.scriptBlock.spider.grammarSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * 对某个变量声明内部的具体信息进行语法检查。
 */
public class SingleVarGSpider extends GrammarSpider {

	public SingleVarGSpider(ScriptBlock targetBlock) {
		//三个记录槽。
		super(targetBlock, "变量详细语法检查", 3);
	}

	@Override
	protected void grammarWork() {
		switch(infoType){
		case CLASS_REF_CL:
			//放出类引用GSpider分析类引用语法，
			//借用类引用Spider对当前Block单独再执行一次语法检查，
			//此次检查只会涉及targetBlock和其子链。
			borrowSpider(new RClassRefGSpider(targetBlock));
		case CLASS_REF_GP:
			//这种情况下无需进一步检查语法，
			//因为在分析阶段就已经保证泛参引用类型不能有任何子信息，
			//如果有子信息，那么Block就会被设置为VOID，
			//这里这个Block不是VOID类型，
			//表明这里一定是一个不含subBlock的Block，
			//其语法一定是正确的。
			break;
			
		case DECLAR_INIT:
			if (hasSubBlock){
				//如果有subBlock，
				//检查子信息，
				//这里检查的主要目的是防止子信息为空，
				//如果有声明，就必须包含详细的信息。
				sendSpider(new SingleTypeGSpider(subBlock, InformationType.INFO_INIT));
			} else {
				//没有子信息
				dealWith_Unexpected();
				//记录无意义Block数量。
				recordNonesense();
			}
			break;
		
		default:
			dealWith_Unexpected();
			break;
		}
	}

	/**
	 * 增加警告缺少或者过多变量类型声明、
	 * 过多的初始化信息。
	 */
	@Override
	public boolean occurredError(){
		return super.occurredError()
				//缺少或者过多变量类型声明。
				|| 1 != getRecordOf(InformationType.CLASS_REF_CL)
				//过多的初始化信息
				|| 1 < getRecordOf(InformationType.DECLAR_INIT);
	}
	
	/**
	 * 增加警告缺少变量类型声明。
	 */
	@Override
	public String getRawReport(){
		StringBuffer appendReport = new StringBuffer();
		
		//弧线声明区域。
		switch(getRecordOf(InformationType.CLASS_REF_CL)){
		case 0:
			appendReport.append("\n缺少变量类型声明。");
			break;
			
		case 1:
			//正确情况。
			break;
			
		default:
			appendReport.append("\n过多的变量类型声明。");
			break;
		}
		
		//初始化信息。
		if (1 < getRecordOf(InformationType.DECLAR_INIT)){
			appendReport.append("\n过多的初始化信息区域。");
		}
		
		return super.getRawReport()
				+ appendReport.toString();
	}

	/**
	 * 只分析CLASS_REF_CL/
	 * CLASS_REF_GP/
	 * DECLAR_INIT，
	 * 其他的映射到0。
	 */
	protected int map_infoType_to_logIndex(InformationType infoType){
		switch(infoType){
		case CLASS_REF_CL:
		case CLASS_REF_GP:
			return 1;
			
		case DECLAR_INIT:
			return 2;
			
		default:
			return 0;
		}
	}
}
