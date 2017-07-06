package unfinishedClass.customRClass.scriptBlock.spider.grammarSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.basicToolSpider.ReasonedErrorSpider;

/**
 * 检查必要的信息必须包含：
 * 顶层必须包含TYPE：
 * 以及不能包含标记为VOID额Block。
 */
public class ScriptGrammarSpider extends DeclarGSpider {
	
	/**
	 * 默认Spider发生错误。
	 * @param targetBlock
	 * 		目标Block节点。
	 */
	public ScriptGrammarSpider(ScriptBlock targetBlock) {
		//11个记录槽。
		super(targetBlock, "RClass脚本检查", 11);
	}
	
	/**
	 * 对Script内部的信息进行进一步的分析。
	 */
	protected void declarGrammarWork() {
		switch(infoType) {
		
		case DECLAR_TYPE:
			//检查类型声明。
			sendSpider(new TypeGSpider(subBlock));
			break;
			
		case DECLAR_NAME:
			//检查名称声明。
			sendSpider(new NameGSpider(subBlock));
			break;
			
		case DECLAR_GEN_PARAMS:
			//检查泛参声明。
			sendSpider(new GenParamGSpider(subBlock));
			break;
			
		case DECLAR_EXTENDS:
			//检查父类声明。
			sendSpider(new RClassRefGSpider(subBlock, 1));	//限制数量。
			break;
		
		case DECLAR_IMPLEMENTS:
			//检查接口声明。
			sendSpider(new RClassRefGSpider(subBlock));		//不限制数量。
			break;
		
		case DECLAR_MEMBERS:
			//检查成员变量声明。
			sendSpider(new VarFieldGSpider(subBlock));
			break;
			
		case DECLAR_FUN_CONFUN:
			//检查构造Function声明。
			sendSpider(new ConFunGSpider(subBlock));
			break;
			
		case DECLAR_FUN_STATIC:
			//检查静态Function声明。
			sendSpider(new StaticFunGSpider(subBlock));
			break;
			
		case DECLAR_FUN:
			//检查普通Function声明。
			sendSpider(new FunGSpider(subBlock));
			break;
			
		case DECLAR_FUN_ABSTRACT:
			//检查抽象Function声明。
			sendSpider(new AbstractFunGSpider(subBlock));
			break;
			
		default:
			//处理不在处理范围之内的InformationType。
			dealWith_Unexpected();
			break;
		
		}
	}
	
	@Override
	public boolean occurredError(){
		//注意：错误的情况有两种：
		//一是，声明了某个字段，但是这个字段发生了错误；
		//二是，缺少某些字段，如果没有声明过也就无法检查错误，
		//注意不要遗漏这种情况但是由于缺少。
		return super.occurredError()	//检查过程中层经发生错误。
				//缺少类型声明或者多个类型声明。
				|| 1 != getRecordOf(InformationType.DECLAR_TYPE)
				//缺少类名声明或者多个类名声明。
				|| 1 != getRecordOf(InformationType.DECLAR_NAME)
				//存在多个泛参声明。
				|| 1 < getRecordOf(InformationType.DECLAR_GEN_PARAMS) 
				//存在多个非接口父类。
				|| 1 < getRecordOf(InformationType.DECLAR_EXTENDS) 
				//存在多个变量区域声明。
				|| 1 < getRecordOf(InformationType.DECLAR_MEMBERS) 
				//存在多个构造Function。
				|| 1 < getRecordOf(InformationType.DECLAR_FUN_CONFUN);
	}
	
	@Override
	public String getRawReport() {
		StringBuffer appendReport = new StringBuffer();
		
		//检查类型声明。
		switch(getRecordOf(InformationType.DECLAR_TYPE)){
		case 0:
			//缺少类型声明。
			appendReport.append("\n缺少类型声明。");
			break;
		
		case 1:
			//正常情况，跳出switch。
			break;
			
		default:
			appendReport.append("\n存在多个类型声明。");
			break;
		}//end switch
		
		//检查类名声明。
		switch(getRecordOf(InformationType.DECLAR_NAME)){
		case 0:
			//缺少名称声明。
			appendReport.append("\n缺少名称声明。");
			break;
		
		case 1:
			//正常情况，跳出switch。
			break;
			
		default:
			appendReport.append("\n存在多个名称声明。");
			break;
		}//end switch
		
		if (1 < getRecordOf(InformationType.DECLAR_GEN_PARAMS)){
			appendReport.append("\n存在多个泛参声明。");
		}
		
		if (1 < getRecordOf(InformationType.DECLAR_EXTENDS)){
			appendReport.append("\n存在多个非接口父类声明。");
		}
		
		if (1 < getRecordOf(InformationType.DECLAR_IMPLEMENTS)){
			appendReport.append("\n蹲在多个接口声明。");
		}
		
		if (1 < getRecordOf(InformationType.DECLAR_MEMBERS)){
			appendReport.append("\n存在多个成员变量区域声明。");
		}
		
		if (1 < getRecordOf(InformationType.DECLAR_FUN_CONFUN)){
			appendReport.append("\n存在多个构造Function区域声明。");
		}
		
		return super.getRawReport()
				+ appendReport.toString();
	}
	
	/**
	 * 通过这个方法来建立infoType和blockLog中对应记录的连接，
	 * 其中序号和InformationType对应的关系如下：<br>
	 * 0 ---- VOID<br>
	 * 1 ---- DECLAR_TYPE<br>
	 * 2 ---- DECLAR_NAME<br>
	 * 3 ---- DECLAR_GEN_PARAMS<br>
	 * 4 ---- DECLAR_EXTENDS<br>
	 * 5 ---- DECLAR_IMPLEMENTS<br>
	 * 6 ---- DECLAR_MEMBERS<br>
	 * 7 ---- DECLAR_FUN_CONFUN<br>
	 * 8 ---- DECLAR_FUN_STATIC<br>
	 * 9 ---- DECLAR_FUN<br>
	 * 10 ---- DECLAR_FUN_ABSTRACT<br>。
	 * @param infoType
	 * 		infoType信息类型。
	 * @return
	 * 		对应记录的序号。
	 */
	protected int map_infoType_to_logIndex(InformationType infoType){
		switch(infoType){
		case DECLAR_TYPE:
			return 1;
			
		case DECLAR_NAME:
			return 2;
			
		case DECLAR_GEN_PARAMS:
			return 3;
			
		case DECLAR_EXTENDS:
			return 4;
			
		case DECLAR_IMPLEMENTS:
			return 5;
			
		case DECLAR_MEMBERS:
			return 6;
			
		case DECLAR_FUN_CONFUN:
			return 7;
			
		case DECLAR_FUN_STATIC:
			return 8;
			
		case DECLAR_FUN:
			return 9;
			
		case DECLAR_FUN_ABSTRACT:
			return 10;
			
		default:
			return 0;
		}
	}//map_infoType_to_logIndex()
	
}
