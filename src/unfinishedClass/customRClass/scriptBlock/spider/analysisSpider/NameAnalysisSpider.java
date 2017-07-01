package unfinishedClass.customRClass.scriptBlock.spider.analysisSpider;

import unfinishedClass.customRClass.RStringChecker;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.CountableSpider;

/**
 * 分析Block中的Information是否是正确的类名声明，
 * 只对第一个Block进行检查，其余的Block全部视作非法信息。
 */
public class NameAnalysisSpider extends CountableSpider {

	public NameAnalysisSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}
	
	@Override
	public void countWork() {
		switch(count){
		case 1:
			//检查是否符合RClass命名规范
			if (RStringChecker.checkRClassName(targetInfoString)){
				setInfo(InformationType.CLASS_REF_CL);
			} else {
				setInfo_VOID();
				descriptInfo("RClass名字格式非法。" );
			}
			break;
		default:
			//当前Spider只检查一个Block是否正确声明了一个RClass的名字，
			//其他的Block都忽略掉。
			setInfo_VOID();
			descriptInfo("在RClass声明中多余的信息。");
		}
		
		
	}

}
