package unfinishedClass.customRClass.scriptChecker;

import java.util.ArrayList;

import basicTool.RLogger;
import unfinishedClass.customRClass.script.RClassScriptStruct;


/**
 * 检查CustomRClass的名字声明是否正确。
 */
public class RClassNameScriptChecker extends ScriptForceChecker {
	public RClassNameScriptChecker() {
		super("CustomRClass名字声明");
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
	 * 		无错误，返回checkLine + 1；
	 * 		发生任何错误，返回-1。
	 */
	@@Override
	protected int checkDetail(ArrayList<String> scriptLines, int checkLine) {
		String scriptLine = scriptLines.get(checkLine);
		if (scriptLine.startsWith(
				RClassScriptStruct.rClassNameDeclaration)){
			scriptLine = scriptLine.substring(
					RClassScriptStruct.rClassTypeDeclaration.length());
			
			if ( RNameChecker.check(scriptLine) ){
				return checkLine + 1;
			} else {
				showGrammarErrorMessage(checkLine, 
						"脚本中定义的RClass的名字是：" + scriptLine
						+ "，其中可能包含以下非法字符：" + RNameChecker.getErrorString());
				return -1;
			}
		} else {
			showGrammarErrorMessage(checkLine, ""没有声明CustomRClass的名字"");
			return -1;
		}
	}
}
