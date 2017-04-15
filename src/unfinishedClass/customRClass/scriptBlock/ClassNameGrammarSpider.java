package unfinishedClass.customRClass.scriptBlock;

import unfinishedClass.customRClass.scriptBlock.information.Information;

/**
 * 此类的目的在于检查一个Block链，
 * 保证链上只有1个或者不限次数个 CLASSNAME型Information。
 */
public class ClassNameGrammarSpider extends GrammarSpider {
	protected boolean isLimited;
	
	/**
	 * 默认发生错误，
	 * 只有找到了一个合法的Block时才能转变成正常情况。
	 */
	public ClassNameGrammarSpider(ScriptBlock targetBlock, boolean isLimited) {
		super(targetBlock, true);
		foundOne = false;
		this.isLimited = isLimited;
	}

	@Override
	protected void dealWithTargetBlock() {
		Information information = targetBlock.getInformation();
		
		switch(information.getType()){
		case CLASSNAME:
			dealWith_CLASSNAME();
			break;
		case VOID:
			dealWith_VOID();
			break;
		default:
			dealWith_Unexpected();
			break;
		}
	}
	
	protected void dealWith_CLASSNAME() {
		if (foundOne){
			if (isLimited){
				//先前已经找到类名，
				//但是Spider已经被限制，
				//违反语法错误。
				error = true;
				appendReason("发现多个类名", false);
			}
		} else {
			foundOne = true;
			error = false;
		}
	}
	
	@Override
	public String getErrorReason(){
		if ( ! foundOne){
			return super.getErrorReason() + "没有发现具体的ClassName声明。";
		} else {
			return super.getErrorReason();
		}
	}
}
