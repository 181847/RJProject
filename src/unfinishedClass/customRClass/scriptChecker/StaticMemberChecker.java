package unfinishedClass.customRClass.scriptChecker;

import java.util.ArrayList;

import unfinishedClass.customRClass.script.RClassScriptStruct;

public class StaticMemberChecker extends ScriptUnForceChecker {

	public StaticMemberChecker(String checkType) {
		super("静态成员声明");
	}

	@Override
	protected int checkDetail(ArrayList<String> scriptLines, int checkLine) {
		boolean hitEnd = false;
		int scriptSize = scriptLines.size();
		String scriptLine = scriptLines.get(checkLine);
		
		if (scriptLine.startsWith(RClassScriptStruct.rClassStaticMemberDeclaration)){
			++checkLine;
			
			for (; checkLine >= 0 && checkLine < scriptSize; ++ checkLine){
				scriptLine = scriptLines.get(checkLine);
				
				//第三层次的声明符号
				if (scriptLine.startsWith(RClassScriptStruct.hierarchy_2_Symbol)){
					//发现第三层次的声明符号
					//检查成员声明是否包含非法字符
					scriptLine = scriptLine.substring(
							RClassScriptStruct.hierarchy_2_Symbol.length());
					if ( ! RMemberChecker.check(scriptLine)){
						showGrammarErrorMessage(checkLine, "检查静态成员声明的过程中发现成员声明出错，"
								+ "可能包含非法字符，也可能声明字段数量不是2或者3，请检查本行："
								+ scriptLine);
						return -1;
					}
				} else if (scriptLine.startsWith(RClassScriptStruct.hierarchy_2_Symbol)){
					//发现第一层次的声明符号，
					//检查是否是成员声明结束的字符串。
					if ( scriptLine.startsWith(RClassScriptStruct.rClassStaticMemberDeclaration_End)){
						hitEnd = true;
						break;	
					} else {
						showGrammarErrorMessage(checkLine, "检查静态成员声明的过程中发现第二层次符号，"
								+ "但是这一行并不是静态成员声明结束的信息(" 
								+ RClassScriptStruct.rClassStaticMemberDeclaration_End + ")，层次错误。");
						return -1;
					}
				} else {
					showGrammarErrorMessage(checkLine, "检查静态成员声明的过程中没有以层次符号声明的信息，"
							+ "无法进行检查。");
					return -1;
				}
			}//for
			
			if (hitEnd){
				//到达了成员声明结束的行信息
				++ checkLine;
			} else {
				//没有到达成员声明结束的行信息
				showGrammarErrorMessage(checkLine, "检查成员声明的过程中没有发现接口声明结束的行信息。");
				return -1;
			}
		}
		// TODO Auto-generated method stub
		return checkLine;
	}

}
