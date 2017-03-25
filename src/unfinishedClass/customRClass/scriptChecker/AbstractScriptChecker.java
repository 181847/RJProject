package unfinishedClass.customRClass.scriptChecker;

import java.util.ArrayList;

import basicTool.RLogger;

public abstract class AbstractScriptChecker {
	/**
	 * 存储当前检查的类型，
	 * 比如用来检查名字的Checker就可以设置成“名字检查”，
	 * 这一项将会用于错误发生的时候向日志表明错误的原因。
	 */
	public String checkType;
	
	/**
	 * 设置Checker的检查类型名字，
	 * 用于向日志打印部分检查错误的信息。
	 * @param checkType
	 * 		检查类型的名字，
	 * 		比如“CustomRClass名称检查”。
	 */
	public AbstractScriptChecker(String checkType){
		if (checkType == null || checkType.isEmpty()){
			checkType = "**未知类型检查**";
		} else {
			this.checkType = checkType;
		}
	}
	
	/**
	 * 显示错误信息。
	 * @param errorLine
	 * 		错误的行数。
	 * @param errorReason
	 * 		解释错误的原因。
	 */
	public void showGrammarErrorMessage(int errorLine, String errorReason){
		RLogger.logError("第" + errorLine + "行，脚本语法检查出错！类型：" 
				+ checkType + "错误。错误原因：" + errorReason);
	}
	
	public abstract int check(ArrayList<String> scriptLines, int checkLine);
}
