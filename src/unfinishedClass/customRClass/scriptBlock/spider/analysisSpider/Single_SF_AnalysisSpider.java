package unfinishedClass.customRClass.scriptBlock.spider.analysisSpider;

import unfinishedClass.customRClass.RStringChecker;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.ScriptDeclaration;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.CountableSpider;

/**
 * 对一个subFun中的详细信息进行分析。
 */
public class Single_SF_AnalysisSpider extends CountableSpider {

	public Single_SF_AnalysisSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	public void countWork() {
		switch(count) {
		case 1:
			//第一个要求必须是类型信息，
			//这个AnalysisSpider只对当前Block进行一次分析。
			new RClassRefAnalysisSpider(targetBlock)
				.work();
			break;
			
		case 2:
			//第二个要求必须是一个二维坐标信息
			if (RStringChecker.checkLocation(targetInfoString)) {
				//坐标信息正确，设置标签。
				setInfo(InformationType.RECT);
			} else {
				//不是一个二维坐标。
				setInfo_VOID();
				descriptInfo("SubFun没有坐标信息。");
			}
			break;
		
		default:
			//在其他情况下处理泛参指定和修改信息
			if (targetInfoString
					.equals(ScriptDeclaration.declar_GP_assign)) {
				setInfo(InformationType.DECLAR_GP_ASSIGN);
				//泛参指定声明。
				if (hasSubBlock) {
					//分析具体的泛参指定。
					new GenericsAssignAnalysisSpider(subBlock)
						.workUntilEnd();
				}
				
			} else if (targetInfoString
					.equals(ScriptDeclaration.declar_modify)) {
				//修改信息声明。
				setInfo(InformationType.DECLAR_MODIFY);
				
				if (hasSubBlock) {
					//分析具体的修改信息，
					//然而由于对于修改信息没有什么限制，
					//所以这里直接使用单一的标签设置将所有的子Block
					//标记为INFO_MODIFY。
					new SingleTypeAnalysisSpider(subBlock, InformationType.INFO_MODIFY)
						.workUntilEnd();
				}
			} else {
				setInfo_VOID();
				descriptInfo("子Fun中无用信息声明。");
			}
			
		}
	}

}
