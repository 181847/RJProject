package unfinishedClass.customRClass.scriptBlock;

import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * 此类的目的在于检查一个Block链，
 * 保证链上有1 ~ 指定数目个 CLASSNAME型Information，
 * 这个数目在构造方法中定义，必须大于等于1，
 * 如果小于等于1表示没有最大限制，
 * 但是至少要有一个，
 * 检查过程中error状态和记录合法Block的数量是分开来的，
 * 通过调用occurredError()方法来查看是否确实发生了错误。
 */
public class ClassNameGrammarSpider extends GrammarSpider {
	/**
	 * 这一项用来记录最大的合法Block数目，
	 * 一旦大于这个数目就算发生语法错误，
	 * 本项正常情况下只取 1 ~ 正无穷，
	 * 0表示没有限制。
	 */
	protected int maxLimit;
	
	/**
	 * 已经检查了的合法的Block数量。
	 */
	protected int legalCounter;

	/**
	 * 默认正常状态，没有发生错误。
	 * @param maxLimit
	 * 		最大的合法检查数量。
	 */
	public ClassNameGrammarSpider(ScriptBlock targetBlock, int maxLimit) {
		super(targetBlock, false);
		this.maxLimit = maxLimit > 0 ? maxLimit : 0;
		legalCounter = 0;
	}

	@Override
	protected void dealWithTargetBlock() {
		// TODO Auto-generated method stub
		Information information = targetBlock.getinformation();
		
		if (information.getType() == InformationType.CLASSNAME){
			++ legalCounter;
		} else {
			//发现CLASSNAME以外的无用信息
			error = true;
			appendReason("类名检查过程中发现无用的多余信息", true);
		}
	}

	@Override
	public boolean occurredError(){
		if ( ! error){
			if (legalCounter < 1){
				//合法Block数量不足一个，语法错误
				return false;
			} else if (maxLimit == 0){
				//没有数量限制，语法正确
				return true;
			} else if (legalCounter > maxLimit){
				//超出限制数量，语法错误
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * 由于合法block的计数和无用信息的检查是分开的，
	 * 在这里获取错误信息的时候临时添加上数量是否满足要求。
	 */
	@Override
	public String getErrorReason(){
		String errorReason = super.getErrorReason();
		if (legalCounter < 1){
			return errorReason + "\n\t具体的类名声明数量不足一，"
					+ "缺少类名声明。";
		} else if (legalCounter > maxLimit){
			return errorReason + "\n\t具体的类名声明数量超过限制：" 
					+ maxLimit + "。";
		}
		return errorReason;
	}

}
