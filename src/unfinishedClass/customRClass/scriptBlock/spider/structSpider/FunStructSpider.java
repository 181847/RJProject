package unfinishedClass.customRClass.scriptBlock.spider.structSpider;

import unfinishedClass.customRClass.rStruct.FunStruct;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

/**
 * 用于生成FunStruct结构的Spider，
 * 适用于四种不同类型的Function。
 */
public class FunStructSpider 
extends UtilsRStructSpider_with_RStruct<FunStruct> {


	public FunStructSpider() {
		finalRStruct = new FunStruct();
	}
	
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
					getRSet_fromSub_use(
							new GenParamSetSpider()));
			break;
			
		case DECLAR_EXCUTEES:
			//定义执行入口。
			finalRStruct
			.defineExcutees_by_RSet(
					getRSet_fromSub_use(
							new ExcuteeSetSpider()));
			break;
			
		case DECLAR_PARAMETERS:
			//定义参数组件。
			finalRStruct
			.defineParameters_by_RSet(
					getRSet_fromSub_use(
							new VarSetSpider()));
			break;
			
		case DECLAR_EXCUTERS:
			//定义执行出口。
			finalRStruct
			.defineExcuters_by_RStruct(
					getRStruct_fromSub_use(
							new ExcuterFieldStructSpider()));
			break;
			
		case DECLAR_RETURNVALS:
			//定义返回值组件。
			finalRStruct
			.defineReturnvals_by_RSet(
					getRSet_fromSub_use(
							new VarSetSpider()));
			break;
			
		case DECLAR_LOCALVARS:
			//定义本地变量。
			finalRStruct
			.defineVars_by_RStruct(
					getRStruct_fromSub_use(
							new VarFieldStructSpider()));
			break;
			
		case DECLAR_SUBFUNS:
			//定义子Fun。
			finalRStruct
			.defineSubFuns_by_RSet(
					getRSet_fromSub_use(
							new SubFunSetSpider()));
			break;
			
		case DECLAR_ARCS:
			//定义弧线。
			finalRStruct
			.defineArcs_by_RStruct(
					getRStruct_fromSub_use(
							new ArcFieldStructSpider()));
			break;
			
		case DECLAR_COMMENTS:
			//定义注释。
			finalRStruct
			.defineComments_by_RSet(
					getRSet_fromSub_use(
							new CommentSetSpider()));
			
		default:
			//什么都不做。
			break;
		}
	}
}
