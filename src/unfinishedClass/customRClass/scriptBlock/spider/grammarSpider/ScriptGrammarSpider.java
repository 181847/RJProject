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
	 * 本数组用来记录相关block是否存在。
	 * 初始时所有的记录都是0，
	 * 其中序号和InformationType对应的关系如下：<br>
	 * 0 ---- DECLAR_TYPE<br>
	 * 1 ---- DECLAR_NAME<br>
	 * 2 ---- DECLAR_EXTENDS<br>
	 * 3 ---- DECLAR_IMPLEMENTS<br>
	 * 4 ---- DECLAR_MEMBERS<br>
	 * 5 ---- DECLAR_FUN_CONFUN<br>
	 * 6 ---- DECLAR_FUN_STATIC<br>
	 * 7 ---- DECLAR_FUN<br>
	 * 8 ---- DECLAR_FUN_ABSTRACT<br>
	 */
	protected int[] blockLog = new int[9];
	
	/**
	 * 默认Spider发生错误。
	 * @param targetBlock
	 * 		目标Block节点。
	 */
	public ScriptGrammarSpider(ScriptBlock targetBlock) {
		super(targetBlock, "RClass脚本检查");
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
			this.dealWith_Unexpected();
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
				|| blockLog[0] == 0		//缺少类型声明。
				|| blockLog[1] == 0;	//缺少类名声明。
	}
}
