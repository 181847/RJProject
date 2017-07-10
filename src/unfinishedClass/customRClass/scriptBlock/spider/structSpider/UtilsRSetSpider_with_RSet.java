package unfinishedClass.customRClass.scriptBlock.spider.structSpider;

import unfinishedClass.customRClass.rStruct.RSet;
import unfinishedClass.customRClass.rStruct.RStruct;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

/**
 * 适用于RSet的通用Spider，
 * 用于获取统一类型的Struct的集合，
 * 这个类向对于UtilsRStructSpider只添加了一个RSet<>成员变量，
 * 以及实现了有关获取RSet的方法。
 */
public abstract class UtilsRSetSpider_with_RSet<RSTRUCT_IN_RSET extends RStruct>
extends UtilsRStructSpider
implements RSetSpider<RSTRUCT_IN_RSET>{
	/**
	 * 存储同一类型RStruct的集合。
	 */
	protected RSet<RSTRUCT_IN_RSET> finalRSet;

	public UtilsRSetSpider_with_RSet(ScriptBlock targetBlock) {
		super(targetBlock);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 获取工作完成之后的结构集合。
	 */
	@Override
	public RSet<RSTRUCT_IN_RSET> getRSet() {
		return finalRSet;
	}

}
