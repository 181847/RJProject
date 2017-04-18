package unfinishedClass.customRClass.scriptBlock;

import unfinishedClass.customRClass.scriptBlock.information.Information;

/**
 * 此类的目的在于检查一个Block链，
 * 保证把链上有且只有一个InformationType为
 * INTERFACE或 ABSTRACT或 CLASS的Block。
 */
public class TypeGrammarSpider extends GrammarSpider {

	/**
	 * 默认发生了错误，
	 * 只有被检查的Block链上有且只有
	 * 一个InformationType为CLASSNAME的Block，
	 * 才不算发生错误。
	 */
	public TypeGrammarSpider(ScriptBlock targetBlock) {
		super(targetBlock, true, "RClass类型检查");
		foundOne = false;
	}

	@Override
	protected void dealWithTargetBlock() {
		Information information = targetBlock.getInformation();
		
		switch(information.getType()){
		case INTERFACE:
		case ABSTRACT:
		case CLASS:
			dealWith_CLASS();
			break;
		case VOID:
			dealWith_VOID();
			break;
		default:
			dealWith_Unexpected();
			break;
		}
	}

	/**
	 * 处理targetBlock的InformationType是INTERFACE/ABSTRACT/CLASS三种的情况，
	 * 如果foundOne为true说明该Block链上面不止一个具体的类型声明，语法错误。
	 */
	protected void dealWith_CLASS() {
		if (foundOne){
			//foundOne为true表示之前已经发现了一个具体的类型声明，
			//所以无论当前的information是什么，
			//都算是发生了错误。
			error = true;
			appendReason("已经声明了类型，但是类型声明下面发现多余的无用信息。", false);
		} else {
			error = false;		//发现类型的具体声明，手动设置error为false。
			foundOne = true;	//记录已经找到一个具体的类型声明
		}
	}
	
	@Override
	public String getErrorReason(){
		if ( ! foundOne){
			return super.getErrorReason() + "没有发现具体的Type声明。";
		} else {
			return super.getErrorReason();
		}
	}

}
