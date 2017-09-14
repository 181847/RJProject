package unfinishedClass.customRClass.scriptBlock.spider.structSpider;

import unfinishedClass.customRClass.rStruct.RSet;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

/**
 * 适用于RSet的通用Spider，
 * 用于获取统一类型的Struct的集合，
 * 这个类向对于UtilsRStructSpider只添加了一个RSet<>成员变量，
 * 以及实现了有关获取RSet的方法。
 */
public abstract class UtilsRSetSpider_with_RSet
<RSTRUCT_IN_RSET extends IRStruct>
extends UtilsRStructSpider
implements IRSetSpider<RSTRUCT_IN_RSET>{
	/**
	 * 存储同一类型RStruct的集合。
	 */
	protected RSet<RSTRUCT_IN_RSET> finalRSet;

	/**
	 * 用这个来构造的Spider请一定要调用placeSpider()方法，
	 * 来将Spider放置到准确的位置。
	 */
	public UtilsRSetSpider_with_RSet() {
		finalRSet = new RSet<RSTRUCT_IN_RSET>();
	}

	public UtilsRSetSpider_with_RSet(ScriptBlock targetBlock) {
		super(targetBlock);
		finalRSet = new RSet<RSTRUCT_IN_RSET>();
	}

	/**
	 * 获取工作完成之后的结构集合。
	 */
	@Override
	public RSet<RSTRUCT_IN_RSET> getRSet() {
		return finalRSet;
	}

}
