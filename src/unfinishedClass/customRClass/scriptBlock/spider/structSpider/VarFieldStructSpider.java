package unfinishedClass.customRClass.scriptBlock.spider.structSpider;

import unfinishedClass.customRClass.rStruct.VarFieldStruct;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

/**
 * 收集静态变量和非静态变量定义的Spider。
 */
public class VarFieldStructSpider extends UtilsRStructSpider_with_RStruct<VarFieldStruct> {

	public VarFieldStructSpider() {
		finalRStruct = new VarFieldStruct();
	}
	
	public VarFieldStructSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		finalRStruct = new VarFieldStruct();
	}

	@Override
	public void countWork() {
		switch(infoType){
		case DECLAR_STATIC:
			//静态变量部分。
			finalRStruct
			.defineStaticVars_by_RSet(
					//获取 子链中的变量定义集合。
					getRSet_fromSub_use(
							new VarSetSpider()));
			break;
			
		case VAR:
			//非静态变量部分。
			finalRStruct
			.defineVar(
					//获取当前targetBlock以及其子链所共同定义的变量结构。
					getVarStruct());
			break;
			
		default:
			//什么也不做。
			break;
		}
	}

}
