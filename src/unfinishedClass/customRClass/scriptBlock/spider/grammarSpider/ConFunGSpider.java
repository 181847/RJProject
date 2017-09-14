package unfinishedClass.customRClass.scriptBlock.spider.grammarSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * 构造Function语法检查。
 */
public class ConFunGSpider extends DeclarGSpider {

	public ConFunGSpider(ScriptBlock targetBlock) {
		//七个记录槽。
		super(targetBlock, "构造Function语法检查", 7);
	}

	@Override
	protected void declarGrammarWork() {
		switch(infoType){
		case DECLAR_PARAMETERS:
		case DECLAR_RETURNVALS:
			//返回值和参数使用同样的逻辑进行组件判断，
			//VarGSpider只能分析一连串的变量声明，
			//可以兼容参数和返回值组件的声明。
			sendSpider(new VarGSpider(subBlock));
			break;
			
		case DECLAR_LOCALVARS:
			//分析本地变量的声明，
			//VarFieldGSpider相比VarGSpider多了一个检查静态变量声明的方法。
			sendSpider(new VarFieldGSpider(subBlock));
			break;
			
		case DECLAR_SUBFUNS:
			sendSpider(new SubFunGSpider(subBlock));
			
		case DECLAR_ARCS:
			sendSpider(new ArcGSpider(subBlock));
			break;
			
		case DECLAR_COMMENTS:
			sendSpider(new CommentGSpider(subBlock));
			break;
			
		default:
			dealWith_Unexpected();
			break;
		}
	}
	
	/**
	 * 防止过多参数组件声明、
	 * 返回值省声明、
	 * 本地变量声明、
	 * 弧线声明、
	 * 注释声明。
	 */
	@Override
	public boolean occurredError(){
		int arcFieldNum = getRecordOf(InformationType.DECLAR_ARCS);
		
		return super.occurredError()
				//参数组件声明过多。
				|| 1 < getRecordOf(InformationType.DECLAR_PARAMETERS)
				//返回值组件声明过多。
				|| 1 < getRecordOf(InformationType.DECLAR_RETURNVALS)
				//本地变量区域声明过多。
				|| 1 < getRecordOf(InformationType.DECLAR_LOCALVARS)
				//子Fun区域声明过多。
				|| 1 < getRecordOf(InformationType.DECLAR_SUBFUNS)
				//缺少弧线声明
				|| 0 == arcFieldNum
				//弧线声明区域过多
				|| 1 < arcFieldNum
				//注释声明区域过多。
				|| 1 < getRecordOf(InformationType.DECLAR_COMMENTS);
	}
	
	/**
	 * 增加以下相关警告：
	 * 过多参数组件声明、
	 * 返回值省声明、
	 * 本地变量声明、
	 * 子Fun声明、
	 * 弧线声明、
	 * 注释声明。
	 */
	@Override
	public String getRawReport(){
		StringBuffer appendReport = new StringBuffer();
		
		if (1 < getRecordOf(InformationType.DECLAR_PARAMETERS)){
			appendReport.append("过多的参数组件区域。");
		}
		
		if (1 < getRecordOf(InformationType.DECLAR_RETURNVALS)){
			appendReport.append("过多的返回值组件区域。");
		}
		
		if (1 < getRecordOf(InformationType.DECLAR_LOCALVARS)){
			appendReport.append("过多的本地变量区域。");
		}
		
		if (1 < getRecordOf(InformationType.DECLAR_SUBFUNS)){
			appendReport.append("过多的子Fun区域。");
		}
		
		switch(getRecordOf(InformationType.DECLAR_ARCS)){
		case 0:
			appendReport.append("缺少弧线区域。");
			break;
			
		case 1:
			//正确情况。
			break;
			
		default:
			appendReport.append("过多的弧线区域。");
			break;
		}
		
		if (1 < getRecordOf(InformationType.DECLAR_COMMENTS)){
			appendReport.append("过多的注释区域。");
		}
		
		return super.occurredError()
				+ appendReport.toString();
	}
	
	/**
	 * 映射DECLAR_PARAMETERS/
	 * DECLAR_RETURNVAL/
	 * DECLAR_LOCALVARS/
	 * DECLAR_SUBFUNS/
	 * DECLAR_ARCS/
	 * DECLAR_COMMENTS，
	 * 其他的映射到0。
	 */
	protected int map_infoType_to_logIndex(InformationType infoType){
		switch(infoType){
		case DECLAR_PARAMETERS:
			return 1;
			
		case DECLAR_RETURNVALS:
			return 2;

		case DECLAR_LOCALVARS:
			return 3;
			
		case DECLAR_SUBFUNS:
			return 4;
			
		case DECLAR_ARCS:
			return 5;
			
		case DECLAR_COMMENTS:
			return 6;
			
		default:
			return 0;
		}
	}

}
