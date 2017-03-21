package unfinishedClass.customRClass.scriptChecker;

import java.util.ArrayList;

import basicTool.RLogger;
import unfinishedClass.customRClass.script.RClassScriptStruct;

/**
 * 检查CustomRClass的类型声明是否正确。
 */
public class RClassTypeScriptChecker extends ScriptChecker {

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
	 * 		checkLine + 1。
	 */
	@Override
	protected int checkDetail(ArrayList<String> scriptLines, int checkLine, ScriptCheckResult checkResult) {
		//获取本行信息
		String scriptLine = scriptLines.get(checkLine);
		
		//获取类型声明信息
		if (scriptLine.startsWith(RClassScriptStruct.rClassTypeDeclaration)){
			//获取RClass类型声明
			scriptLine = 
					scriptLine.substring(RClassScriptStruct.rClassTypeDeclaration.length());
			//检查三种类型声明
			if (scriptLine.equals(RClassScriptStruct.rClassInterfaceType)){
				checkResult.setRClassType(0);
			} else if (scriptLine.equals(RClassScriptStruct.rClassAbstractType)){
				checkResult.setRClassType(1);
			} else if (scriptLine.equals(RClassScriptStruct.rClassClassType)){
				checkResult.setRClassType(2);
			} else {
				RLogger.logError("脚本：" + checkType + " 出错，第" + checkLine
						+ "行，没有正确声明CustomRClass的类型，"
						+ "请确保脚本文件中的第一行“" + RClassScriptStruct.rClassTypeDeclaration 
						+ "”之后声明的CustomRClass类型为一下三种中的一种："
						+ RClassScriptStruct.rClassInterfaceType + "，"
						+ RClassScriptStruct.rClassAbstractType + "，"
						+ RClassScriptStruct.rClassClassType +  "。" );
				checkResult.setRClassType(-1);
				checkResult.setResult(false);
			}//if 检查三种类型声明
			
		} else {
			RLogger.logError("脚本：" + checkType + " 出错，第" + checkLine
					+ "行 ，没有声明CustomRClass的类型，"
					+ "请确保以一下三种方式在脚本文件的第一行声明CustomRClass的类型："
					+ RClassScriptStruct.rClassTypeDeclaration + RClassScriptStruct.rClassInterfaceType + "、或者"
					+ RClassScriptStruct.rClassTypeDeclaration + RClassScriptStruct.rClassAbstractType + "、或者"
					+ RClassScriptStruct.rClassTypeDeclaration + RClassScriptStruct.rClassClassType +  "。" );
			checkResult.setRClassType(-1);
			checkResult.setResult(false);
		}//if 获取本行检查信息
		
		return checkLine + 1;
	}

}
