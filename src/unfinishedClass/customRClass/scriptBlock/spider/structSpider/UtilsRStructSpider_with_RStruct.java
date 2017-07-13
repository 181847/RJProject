package unfinishedClass.customRClass.scriptBlock.spider.structSpider;

import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

/**
 * 一个适用于所有最终返回结果为RStruct的Spider父类，
 * 相比于UtilsRStrucSpider值添加了一个finalRStruct类型成员变量，
 * 并实现获取RStruct的方法。
 * @author 75309
 *
 * @param <R_STRUCT_FOR_RETURN>
 * 		工作结果完成后的RStruct具体类型。
 */
public abstract class UtilsRStructSpider_with_RStruct
//泛参定义。
<R_STRUCT_FOR_RETURN extends IRStruct>
//继承父类，继承相关工具方法。
extends UtilsRStructSpider 
//实现RStructSpider接口。
implements IRStructSpider<R_STRUCT_FOR_RETURN>{
	
	protected R_STRUCT_FOR_RETURN finalRStruct;

	public UtilsRStructSpider_with_RStruct() {
		//什么也不做。
	}

	public UtilsRStructSpider_with_RStruct(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	public R_STRUCT_FOR_RETURN getRStruct() {
		return finalRStruct;
	}

}
