package unfinishedClass.customRClass.scriptBlock.spider.structSpider;

import unfinishedClass.customRClass.rStruct.FunStruct;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

/**
 * 用于生成FunStruct结构的Spider，
 * 适用于四种不同类型的Function。
 */
public class FunStructSpider extends RStructSpider<FunStruct> {

	public FunStructSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		finalRStruct = new FunStruct();
	}

	@Override
	public void countWork() {
		switch(infoType){
		case DECLAR_GEN_PARAMS:
			//调用方法来定义泛参。
			finalRStruct
			.defineGenParams_by_RSet(
					getGenParamRSet());
			break;
			
		case DECLAR_EXCUTEES:
			//定义执行入口。
			finalRStuct
			.defineExcutees_by_RSet(
					getExcuteesRSet());
			break;
			
		case DECLAR_PARAMETERS:
			//定义参数组件。
			define_parameters();
			break;
			
		case DECLAR_EXCUTERS:
			//定义执行出口。
			define_excuters();
			break;
			
		case DECLAR_RETURNVALS:
			//定义返回值组件。
			define_returnvals();
			break;
			
		case DECLAR_LOCALVARS:
			//定义本地变量。
			define_localVars();
			break;
			
		case DECLAR_SUBFUNS:
			//定义子Fun。
			define_excutees();
			break;
			
		case DECLAR_ARCS:
			//定义弧线。
			define_excutees();
			break;
			
		case DECLAR_COMMENTS:
			//定义注释。
			define_excutees();
			
		default:
			//什么都不做。
			break;
		}
	}

	/**
	 * 定义本地变量。
	 */
	private void define_localVars() {
		VarFieldStructSpider vss =
				new VarFieldStructSpider(subBlock);
		
		vss.workUntilEnd();
		
		finalRStruct
		.defineLocalVars(
				vss.getRStruct());
	}
	
	/**
	 * 定义返回值组件。
	 */
	private void define_returnvals() {
		
	}

	/**
	 * 定义执行出口。
	 */
	private void define_excuters() {
		ExcuterSetSpider ess =
				new ExcuterSetSpider(subBlock);
		
		ess.workUntilEnd();
		
		finalRStruct
		.defineExcuters_by_RSet(
				ess.getRSet());
		
	}

	/**
	 * 定义参数组件。
	 */
	private void define_parameters() {
		VarSetSpider vss =
				new VarSetSpider(subBlock);
		
		vss.workUntilEnd();
		
		finalRStruct
		.defineParameters_by_RSet(
				vss.getRSet());
	}

	/**
	 * 定义执行入口。
	 */
	private void define_excutees() {
		ExcuteeSetSpider ess = 
				new ExcuteeSetSpider(subBlock);
		
		//收集定义。
		ess.workUntilEnd();
		
		//定义执行入口集合。
		finalRStruct
		.defineExcutees_by_RSet(
				ess.getRSet());
	}

	/**
	 * 定义泛参。
	 * @param genParamSetSpider 
	 * 		传入
	 */
	private void define_gen_params() {
		//放置Spider。
		GenParamSetSpider genParamSS = 
				new GenParamSetSpider(subBlock);

		//收集定义。
		genParamSS.workUntilEnd();
		
		//定义泛参集合。
		finalRStruct
		.defineGenParams_by_RSet(		//使用一个集合来定义。
				genParamSS.getRSet());	//获取收集到的泛参集合。
	}
}
