package unfinishedClass.customRClass.scriptChecker;

import java.util.ArrayList;

import basicTool.RLogger;
import unfinishedClass.customRClass.script.RClassScriptStruct;


/**
 * 检查CustomRClass的名字声明是否正确。
 */
public class RClassNameScriptChecker extends ScriptChecker {
	public RClassNameScriptChecker() {
		super("CustomRClass类型声明");
	}

	/**
	 * 检查本行是否正确声明了CustomRClass的名字，不能包含一些非法字符串。
	 * @param scriptLines
	 * 		脚本文件信息，
	 * 		每一个数组的元素都是脚本的一行字符串。
	 * @param checkLine
	 * 		从指定行数开始检查脚本信息。
	 * @param checkResult
	 * 		存储的检查结果。
	 * @return
	 * 		checkLine + 1。
	 */
	@Override
	protected int checkDetail(ArrayList<String> scriptLines, int checkLine, ScriptCheckResult checkResult) {
		//获取本行信息
		String scriptLine = scriptLines.get(checkLine);
		
		//本行是否声明名字
		if (scriptLine.startsWith(RClassScriptStruct.rClassNameDeclaration)){
			
			//获取RClass名字声明
			scriptLine = 
					scriptLine.substring(RClassScriptStruct.rClassNameDeclaration.length());
			
			if (RNameChecker.check(scriptLine)){
				//TODO
			} else {
				RLogger.logError("脚本：" + checkType + " 出错，第" + checkLine
						+ "行 名字声明出错，"
						+ "声明的RClass名字中包含如下非法字符串："
						+ RNameChecker.getErrorStrings());
				checkResult.setResult(false);
			}
			
		} else {
			RLogger.logError("脚本：" + checkType + " 出错，第" + checkLine
					+ "行  名字声明出错，"
					+ "没有在此行中声明RClass的名字。");
			checkResult.setResult(false);
		}//if 本行是否声明名字
		
		return checkLine + 1;
	}
}
