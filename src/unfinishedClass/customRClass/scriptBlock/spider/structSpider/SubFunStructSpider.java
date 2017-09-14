package unfinishedClass.customRClass.scriptBlock.spider.structSpider;

import unfinishedClass.customRClass.rStruct.SubFunStruct;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;

/**
 * 获取一个SubFun的定义，不包括SubFun的名字。
 */
public class SubFunStructSpider 
extends UtilsRStructSpider_with_RStruct<SubFunStruct> {
	
	public SubFunStructSpider() {
		finalRStruct = new SubFunStruct();
	}
	
	public SubFunStructSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		finalRStruct = new SubFunStruct();
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
			
		case LOCATION:
			//二维坐标信息。
			finalRStruct
			.defineLocation(
					//获取坐标结构。
					getLocationStruct());
			break;
			
		case DECLAR_GP_ASSIGN:
			//SubFun的泛参指定。
			finalRStruct
			.defineGPAssigns_by_RSet(
					//获取子链中的泛参指定。
					getRSet_fromSub_use(
							new GPAssignSetSpider()));
			break;
			
		case DECLAR_MODIFY:
			//SubFun的修改信息
			finalRStruct
			.defineModifyInfo(
					//从子链中获取所有修改信息。
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
