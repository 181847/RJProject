package unfinishedClass.customRClass.scriptChecker;

import java.util.ArrayList;

import basicTool.RLogger;

public abstract class ScriptChecker {
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
	public ScriptChecker(String checkType){
		if (checkType == null || checkType.isEmpty()){
			checkType = "**未知类型检查**";
		} else {
			this.checkType = checkType;
		}
	}
	
	/**
	 * 从当前行数开始进行脚本检查，
	 * 检查的结果放在result当中。
	 * @param scriptLines
	 * 		包含脚本文件的数组列表，
	 * 		每一个数组的元素存储的都是脚本一行的字符串，
	 * 		第0号单元保存的应该是这个脚本的具体文件路径。
	 * @param checkLine
	 * 		从此行开始检查脚本信息。
	 * @param result
	 * 		检查的结果放在这个result中。
	 * @return
	 * 		返回当前脚本检查结束后的下一个检查的行数，
	 * 		比如检查完第1行的RClass名字，
	 * 		就返回2；
	 * 		如果本次检查的结果失败，返回checkLine。
	 */
	public int check(ArrayList<String> scriptLines, int checkLine, ScriptCheckResult checkResult){
		int nextLine = checkLine;
		//确保之前的检查结果为true
		if (checkResult.isRight()){
			
			//确保checkLine可提取脚本信息
			if (checkLine >= 0 && checkLine < scriptLines.size()){
				
				nextLine = checkDetail(scriptLines, checkLine, checkResult);
				
			} else {
				RLogger.logError(checkType + "检查出错，检查位置超出脚本文件的位置，没有可检查的脚本信息。");
				checkResult.setRClassType(-1);
				checkResult.setResult(false);
			}//if 确保checkLine可读取脚本信息
		} else {
			RLogger.logError(checkType + "检查取消，因为本次检查之前的检查结果false，不能进行本次检查。");
		}//if 本次检查前结果是否为true
		
		
		if (checkResult.isRight()){
			return nextLine;
		} else {
			return checkLine;
		}//if checkResult
	}

	/**
	 * 此方法用来检查脚本内部的详细情况，
	 * 由子类实现，从而达到不同的检查目的，
	 * 本方法保证肯定能够在ScriptLines的checkLine处获得脚本信息，
	 * 且checkResult一定是true。
	 * @param scriptLines
	 * 		脚本文件信息，
	 * 		每一个数组的元素都是脚本的一行字符串。
	 * @param checkLine
	 * 		从指定行数开始检查脚本信息。
	 * @param checkResult
	 * 		存储的检查结果。
	 * @return
	 * 		如果检查的结果成功，
	 * 		返回下一个检查的位置。
	 * 		如果失败的话只需要设置checkResult为false就可以了，
	 * 		返回值不需要特别指定，因为，
	 * 		调用checkDetail()的check()方法会在checkResult为false的时候自己设定返回值，
	 * 		而不依靠checkDetail()的返回值。
	 */
	protected abstract int checkDetail(ArrayList<String> scriptLines, int checkLine, ScriptCheckResult checkResult);
}
