package unfinishedClass.customRClass.scriptBlock.spider.analysisSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.ScriptDeclaration;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * 对单个变量声明的子信息进行检查，
 * 这些子信息中包括变量的类型，
 * 变量的初始值，在这里用到
 */
public class SingalVarAnalysisSpider extends RClassRefAnalysisSpider {

	public SingalVarAnalysisSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	public void countWork() {
		switch(count){
		//要求单个变量声明的第一个子信息必须是类型信息。
		case 1:
			//只对当前Block进行单独的类型分析，
			//这里只对第一个Block进行检查，
			//要求必须是一个类型引用。
			new RClassRefAnalysisSpider(targetBlock)
				.work();
			break;
			
		default:
			//初始化信息不要求必须存在。
			if (targetInfoString.equals(ScriptDeclaration.declar_init)){
				setInfo(InformationType.DECLAR_INIT);
				if (hasSubBlock){
					//对初始化信息中的所有Block都统一标志为INFO_INIT类型，
					//这部分分析过程不会检查任何错误，
					//所有被遍历到的Block都会被设置为统一的InformationType。
					new SingleTypeAnalysisSpider(subBlock, InformationType.INFO_INIT)
						.workUntilEnd();
				}
			} else {
				setInfo_VOID();
				descriptInfo("变量声明中发现的其他非法信息。");
			}
			break;
		}
	}

}
