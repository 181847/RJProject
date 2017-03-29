package unfinishedClass.customRClass.scriptChecker;

import java.util.ArrayList;

import basicTool.RLogger;
import unfinishedClass.customRClass.script.RClassScriptStruct;

/**
 * 检查CustomRClass的类型声明是否正确。
 */
public class RClassTypeScriptChecker extends ScriptForceChecker {

	public RClassTypeScriptChecker() {
		super("CustomRClass类型声明");
	}

	/**
	 * 检查本行是否正确声明了CustomRClass的类型，比如Interface等等。
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
	@Override
	protected int checkDetail(ArrayList<String> scriptLines, int checkLine) {
		String scriptLine = scriptLines.get(checkLine);
		if (scriptLine.startsWith(
				RClassScriptStruct.rClassTypeDeclaration)){
			scriptLine = scriptLine.substring(
					RClassScriptStruct.rClassTypeDeclaration.length());
			
			if (scriptLine.equals(RClassScriptStruct.rClassInterfaceType)
					|| scriptLine.equals(RClassScriptStruct.rClassAbstractType)
					|| scriptLine.equals(RClassScriptStruct.rClassClassType)){
				return checkLine + 1;
			} else {
				RLogger.logError("脚本语法检查出错！" + checkType
						+ "出错，没有声明正确的RClass类型："
						+ RClassScriptStruct.rClassInterfaceType + "、"
						+ RClassScriptStruct.rClassAbstractType + "、"
						+ RClassScriptStruct.rClassClassType + "。");
				return -1;
			}
		} else {
			RLogger.logError("脚本语法检查出错！" + checkType
					+ "出错，第" + checkLine + "没有" + checkType);
			return -1;
		}
	}

}
