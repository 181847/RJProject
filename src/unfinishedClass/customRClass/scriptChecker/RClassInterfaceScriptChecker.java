package unfinishedClass.customRClass.scriptChecker;

import java.util.ArrayList;

import basicTool.RLogger;
import unfinishedClass.customRClass.script.RClassScriptStruct;

/**
 * 检查检查CustomRClass的接口声明是否正确。
 */
public class RClassInterfaceScriptChecker extends ScriptUnForceChecker {

	public RClassInterfaceScriptChecker() {
		super("CustomRClass接口声明");
	}

	/**
	 * 检查从本行开始是否正确声明了CustomRClass的所有接口。
	 * @param scriptLines
	 * 		脚本文件信息，
	 * 		每一个数组的元素都是脚本的一行字符串。
	 * @param checkLine
	 * 		从指定行数开始检查脚本信息。
	 * @param checkResult
	 * 		存储的检查结果。
	 * @return
	 * 		没有声明接口，
	 * 		返回checkLine；
	 * 		声明接口，
	 * 		各个声明的接口名称符合规则，而且至少包含指定数量的接口声明，
	 * 		返回checkLine + 1 + 声明的接口数量；
	 * 		发生任何错误，返回-1。
	 */
	@Override
	protected int checkDetail(ArrayList<String> scriptLines, int checkLine) {
		boolean hitEnd = false;
		int scriptSize = scriptLines.size();
		String scriptLine = scriptLines.get(checkLine);
		if (scriptLine.startsWith(RClassScriptStruct.rClassInterfaceDeclaration)){
			++ checkLine;
			for (; checkLine > 0 && checkLine < scriptSize; ++checkLine){
				
				scriptLine = scriptLines.get(checkLine);
				
				//第二层次符号
				if (scriptLine.startsWith(RClassScriptStruct.hierarchy_2_Symbol)){
					//发现第二层次的声明符号
					//检查接口名是否包含非法字符
					scriptLine = scriptLine.substring(
							RClassScriptStruct.hierarchy_2_Symbol.length());
					if ( ! RNameChecker.check(scriptLine)){
						//接口的声明中发现非法字符
						showGrammarErrorMessage(checkLine, "检查接口声明中包含以下部分非法字符："
								+ RNameChecker.getErrorString());
						return -1;
					}
					
				//第一层次符号
				} else if (scriptLine.startsWith(RClassScriptStruct.hierarchy_1_Symbol)) {
					//发现第一层次的声明符号，
					//检查是否是接口声明结束的字符串。
					if ( scriptLine.startsWith(RClassScriptStruct.rClassInterfaceDeclaration_End)){
						hitEnd = true;
						break;	
					} else {
						showGrammarErrorMessage(checkLine, "检查接口声明的过程中发现第一层次符号，"
								+ "但是这一行并不是接口声明结束的信息，层次错误。");
						return -1;
					}
				
				//未知层次的信息声明
				} else {
					showGrammarErrorMessage(checkLine, "检查接口声明的过程中没有以层次符号声明的信息，"
							+ "无法进行检查。");
					return -1;
				}
			}//for
			
			if (hitEnd){
				//到达了接口声明结束的行信息
				++ checkLine;
			} else {
				//没有到达接口声明结束的行信息
				showGrammarErrorMessage(checkLine, "检查接口声明的过程中没有发现接口声明结束的行信息。");
				return -1;
			}
		}
		
		return checkLine;
	}
}
