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
	 * @param targetBlock
	 * 		目标Block节点。
	 * @param isLimited
	 * 		是否限制本Spider的检查合法Block的数量，<br>
	 * 		如果isLimited为true，
	 * 		则本Spider检查结果为正确的情况只有targetBlock之后
	 * 		有且 只 有 一  个  CLASSNAME类型的Block；<br>
	 * 		如果isLimited为false，
	 * 		则本Spider检查结果为正确的情况只有targetBlock之后
	 * 		有且 只 有 一  种  CLASSNAME类型的Block；<br>
	 * @param spiderDescription
	 * 		描述本Spider的具体信息，
	 * 		这个信息将会被添加进错误信息的头部，
	 * 		帮助用户确定错误发生在哪一个检查的过程中。
	 */
	public ClassNameGrammarSpider(ScriptBlock targetBlock, 
			boolean isLimited, String spiderDescription) {
		super(targetBlock, true, spiderDescription);
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
				//发生语法错误。
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
