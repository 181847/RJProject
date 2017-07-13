package unfinishedClass.customRClass.scriptBlock.spider.structSpider;

import unfinishedClass.customRClass.rStruct.RSet;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct;

/**
 * 所有工作结果是RSet类型的Spider的接口，
 * 统一包含一个返回方法。
 * RSet是一种同类型RStruct的集合。
 * @author 75309
 *
 * @param <R_STRUCT_IN_SET>
 * 		用于指定集合中RStruct的类型。
 */
public interface IRSetSpider<R_STRUCT_IN_SET extends IRStruct> {
	public RSet<R_STRUCT_IN_SET> getRSet();

}
