package unfinishedClass.customRClass.scriptBlock.spider.structSpider;

import unfinishedClass.customRClass.rStruct.RStruct;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.spider.CountableSpider;

/**
 * 所有提取信息之后为返回类型为Rtruct的Spider的接口，
 * 这种Spider用于遍历Block来生成一个RStruct结构，
 * 并且返回这个RStruct结构，
 * 然而RStruct的结构也有很多不同的类型，
 * 这里为了让返回的类型适合不同的子类RStruct，
 * 使用一个泛参来定义返回类型，
 * 并且将返回类型限制为RStruct的子类。
 */
public interface IRStructSpider<R_STRUCT_RETURN extends RStruct> {
	
	/**
	 * @return
	 * 		返回内部的提取信息之后生成的RStruct结构，
	 *		 返回的类型由泛参决定。
	 */
	public R_STRUCT_RETURN getRStruct();

}
