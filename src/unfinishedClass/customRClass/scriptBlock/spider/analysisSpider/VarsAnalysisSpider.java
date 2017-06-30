package unfinishedClass.customRClass.scriptBlock.spider.analysisSpider;

import unfinishedClass.customRClass.RStringChecker;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.Information;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.CountableSpider;

/**
 * 分析变量的Spider，
 * 本Spider由于可以分析两个字段的变量，
 * 即“类型 + 名字”这种形式的声明，
 * 所以这个Spider还会用来检查Function的Parameter和Returnval组件声明，
 * 但是由于Paramter和Returnval组件的InformationType不是VAR，
 * 所以这里用一个成员变量 assignedType 来
 * 记录对合法Information所赋予的类型枚举变量，
 * 默认为VAR，
 * 如果有特殊要去可以调用另外一个构造方法来修改赋予的类型。
 */
public class VarsAnalysisSpider extends CountableSpider {
	public VarsAnalysisSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}
	
	@Override
	public void countWork() {
		//检查变量名是否符合组件命名规范。
		if (RStringChecker.checkComponentName(targetInfoString)){
			//查看是否有子信息来声明变量的具体信息。
			if (hasSubBlock){
				setInfo(InformationType.VAR);
				new SingalVarAnalysisSpider(subBlock)
					.workUntilEnd();
			} else {
				setInfo_VOID();
				descriptInfo("变量声明缺少详细声明。");
			}
		} else {
			setInfo_VOID();
			descriptInfo("变量名不符合组件命名规范。");
		}
	}

}
