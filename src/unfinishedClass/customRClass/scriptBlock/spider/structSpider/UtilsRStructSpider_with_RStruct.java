package unfinishedClass.customRClass.scriptBlock.spider.structSpider;

import unfinishedClass.customRClass.rStruct.RStruct;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

/**
 * 一个适用于所有最终返回结果为RStruct的Spider父类，
 * 相比于UtilsRStrucSpider值添加了一个finalRStruct类型成员变量。
 * @author 75309
 *
 * @param <R_STRUCT_FOR_RETURN>
 * 		工作结果完成后的RStruct具体类型。
 */
public class UtilsRStructSpider_with_RStruct<R_STRUCT_FOR_RETURN extends RStruct> extends UtilsRStructSpider 
implements IRStructSpider<R_STRUCT_FOR_RETURN>{
	protected R_STRUCT_FOR_RETURN finalRStruct;

	public UtilsRStructSpider_with_RStruct(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	public void countWork() {
		// TODO Auto-generated method stub

	}

	@Override
	public R_STRUCT_FOR_RETURN getRStruct() {
		return finalRStruct;
	}

}
