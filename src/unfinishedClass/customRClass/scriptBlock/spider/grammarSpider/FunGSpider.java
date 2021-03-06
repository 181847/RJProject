package unfinishedClass.customRClass.scriptBlock.spider.grammarSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * 普通Function语法检查。
 */
public class FunGSpider extends DeclarGSpider {

	public FunGSpider(ScriptBlock targetBlock) {
		//10个记录槽。
		super(targetBlock, "Function语法检查", 10);
	}

	@Override
	protected void declarGrammarWork() {
		switch(infoType){
		case DECLAR_GEN_PARAMS:
			//检查泛参声明。
			sendSpider(new GenParamGSpider(subBlock));
			break;
			
		case DECLAR_EXCUTEES:
			sendSpider(
					new SingleTypeGSpider(
							subBlock, 
							InformationType.EXCUTEE));
			break;
			
		case DECLAR_PARAMETERS:
		case DECLAR_RETURNVALS:
			//返回值和参数使用同样的逻辑进行组件判断，
			//VarGSpider只能分析一连串的变量声明，
			//可以兼容参数和返回值组件的声明。
			sendSpider(
					new VarGSpider(subBlock));
			break;
			
		case DECLAR_EXCUTERS:
			sendSpider(
					new ExcuterFieldGSpider(subBlock));
			break;
			
		case DECLAR_LOCALVARS:
			//分析本地变量的声明，
			//VarFieldGSpider相比VarGSpider多了一个检查静态变量声明的方法。
			sendSpider(
					new VarFieldGSpider(subBlock));
			break;
			
		case DECLAR_SUBFUNS:
			//子Fun声明。
			sendSpider(
					new SubFunGSpider(subBlock));
			break;
			
		case DECLAR_ARCS:
			//弧线声明。
			sendSpider(
					new ArcFieldGSpider(subBlock));
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
		return super.occurredError()
				//存在多个泛参声明。
				|| 1 < getRecordOf(InformationType.DECLAR_GEN_PARAMS) 
				//缺少执行入口区域或者执行入口区域过多。
				|| 1 != getRecordOf(InformationType.DECLAR_EXCUTEES)
				//参数组件声明过多。
				|| 1 < getRecordOf(InformationType.DECLAR_PARAMETERS)
				//缺少执行出口区域或者执行出口区域过多。
				|| 1 != getRecordOf(InformationType.DECLAR_EXCUTERS)
				//返回值组件声明过多。
				|| 1 < getRecordOf(InformationType.DECLAR_RETURNVALS)
				//本地变量区域声明过多。
				|| 1 < getRecordOf(InformationType.DECLAR_LOCALVARS)
				//子Fun区域声明过多。
				|| 1 < getRecordOf(InformationType.DECLAR_SUBFUNS)
				//弧线区域过多.
				|| 1 < getRecordOf(InformationType.DECLAR_ARCS)
				//注释声明区域过多。
				|| 1 < getRecordOf(InformationType.DECLAR_COMMENTS);
	}
	
	/**
	 * 增加以下相关警告：
	 * 过多执行入口、
	 * 参数组件声明、
	 * 执行出口、
	 * 返回值声明、
	 * 子Fun声明、
	 * 本地变量声明、
	 * 弧线声明、
	 * 注释声明。
	 */
	@Override
	public String getRawReport(){
		StringBuffer appendReport = new StringBuffer();

		//泛参声明检查。
		if (1 < getRecordOf(InformationType.DECLAR_GEN_PARAMS)){
			appendReport.append("\n存在多个泛参声明。");
		}

		//执行入口。
		switch(getRecordOf(InformationType.DECLAR_EXCUTEES)){
		case 0:
			appendReport.append("\n缺少执行入口区域。");
			break;
			
		case 1:
			//正确情况。
			break;
			
		default:
			appendReport.append("\n过多的执行入口区域。");
			break;
		}
		
		//参数组件。
		if (1 < getRecordOf(InformationType.DECLAR_PARAMETERS)){
			appendReport.append("\n过多的参数组件区域。");
		}

		//执行出口。
		switch(getRecordOf(InformationType.DECLAR_EXCUTERS)){
		case 0:
			appendReport.append("\n缺少执行出口区域。");
			break;
			
		case 1:
			//正确情况。
			break;
			
		default:
			appendReport.append("\n过多的执行出口区域。");
			break;
		}
		
		//返回值组件。
		if (1 < getRecordOf(InformationType.DECLAR_RETURNVALS)){
			appendReport.append("\n过多的返回值组件区域。");
		}
		
		//本地变量声明区域。
		if (1 < getRecordOf(InformationType.DECLAR_LOCALVARS)){
			appendReport.append("\n过多的本地变量区域。");
		}
		
		//子Fun声明区域。
		if (1 < getRecordOf(InformationType.DECLAR_SUBFUNS)){
			appendReport.append("\n过多的子Fun区域。");
		}
		
		//弧线声明区域。
		if (1 < getRecordOf(InformationType.DECLAR_ARCS)){
			appendReport.append("\n过多的弧线区域。");
		}
		
		//注释声明区域。
		if (1 < getRecordOf(InformationType.DECLAR_COMMENTS)){
			appendReport.append("\n过多的注释区域。");
		}
		
		return super.getRawReport()
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
		case DECLAR_EXCUTEES:
			return 1;
			
		case DECLAR_PARAMETERS:
			return 2;
			
		case DECLAR_EXCUTERS:
			return 3;
			
		case DECLAR_RETURNVALS:
			return 4;
			
		case DECLAR_LOCALVARS:
			return 5;

		case DECLAR_SUBFUNS:
			return 6;
			
		case DECLAR_ARCS:
			return 7;
			
		case DECLAR_COMMENTS:
			return 8;
			
		case DECLAR_GEN_PARAMS:
			return 9;
			
		default:
			return 0;
		}
	}


}
