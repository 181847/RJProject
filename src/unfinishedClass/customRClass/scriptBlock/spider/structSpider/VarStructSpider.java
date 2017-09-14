package unfinishedClass.customRClass.scriptBlock.spider.structSpider;

import unfinishedClass.customRClass.rStruct.VarStruct;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

/**
 * 一个用于收集除名字之外其他变量详细信息的Spider。
 */
public class VarStructSpider extends UtilsRStructSpider_with_RStruct<VarStruct> {

	public VarStructSpider() {
		finalRStruct = new VarStruct();
	}
	
	public VarStructSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		finalRStruct = new VarStruct();
	}

	@Override
	public void countWork() {
		switch(infoType) {
		case CLASS_REF_CL:
			//实类型引用。
		case CLASS_REF_GP:
			//泛参类型引用。
			finalRStruct
			.defineClassRef(
					//获取类型引用结构。
					getRClassRefStruct());
			break;
			
		case DECLAR_INIT:
			//变量的初始化信息。
			finalRStruct
			.defineInitInfo(
					//从子链中获取所有初始化信息。
					getRStruct_fromSub_use(
							//纯文本结构。
							new TextStructSpider()));
			break;
			
		default:
			//什么也不做。
			break;
		}
	}

}
