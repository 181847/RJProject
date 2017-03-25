package unfinishedClass.customRClass.scriptChecker;

import java.util.ArrayList;

import basicTool.RLogger;
import unfinishedClass.customRClass.script.RClassScriptStruct;

/**
 * 检查父类的声明是否正确。
 */
public class SuperRClassScriptChecker extends ScriptUnForceChecker {
	public SuperRClassScriptChecker() {
		super("CustomRClass父类声明");
	}

	/**
	 * 检查本行是否正确声明了CustomRClass的继承父类，
	 * 本行可以不以RClassScriptStruct.rClassExtendsDeclaration开头，
	 * 但是如果以这个字符串开头，那么就必须保证没有非法字符。
	 * 但是入股
	 * @param scriptLines
	 * 		脚本文件信息，
	 * 		每一个数组的元素都是脚本的一行字符串。
	 * @param checkLine
	 * 		从指定行数开始检查脚本信息。
	 * @param checkResult
	 * 		存储的检查结果。
	 * @return
	 * 		checkResult显示为接口类型，
	 * 		本行没有声明父类，返回checkLine；
	 * 		checkResult显示为抽象类或者普通类，
	 * 		本行没有声明父类，返回 checkLine；
	 * 		checkResult显示为抽象类或者普通类，
	 * 		本行声明了父类，返回checkLine + 1；
	 * 		任何错误发生，
	 * 		比如checkResult显示为接口类型，
	 * 		但是声明了父类，
	 * 		返回checkLine + 1。
	 */
	@Override
	protected int checkDetail(ArrayList<String> scriptLines, int checkLine) {
		//获取本行信息
		String scriptLine = scriptLines.get(checkLine);
		
		//本行是否声明父类
		if (scriptLine.startsWith(RClassScriptStruct.rClassExtendsDeclaration)){
			
			//获取父类名字
			scriptLine = 
					scriptLine.substring(RClassScriptStruct.rClassExtendsDeclaration.length());
			if (RNameChecker.check(scriptLine)){
				return checkLine + 1;
			} else {
				showGrammarErrorMessage(checkLine, 
						"脚本中定义的父类的名字是：" + scriptLine
						+ "，其中可能包含以下非法字符：" + RNameChecker.getErrorString());
				return -1;
			}
		}//if 本行是否声明父类
		
		return checkLine;
	}

}
