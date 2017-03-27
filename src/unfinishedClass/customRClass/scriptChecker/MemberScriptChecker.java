package unfinishedClass.customRClass.scriptChecker;

import java.util.ArrayList;

import basicTool.RLogger;
import unfinishedClass.customRClass.script.RClassScriptStruct;

/**
 * 本Checker用来统一检查CustomRClass的静态成员，
 * CustomRClass的普通成员，
 * Function的静态成员，
 * Function的普通成员，
 * 注意本类不会检查成员初始化信息的正确性，
 * 比如RClass1的一个静态整形成员默认有一个初始值，
 * 比如像这样子声明：
 * "$basic.Integer staticMember = errorString" ，
 * 这个"errorString"明显不能转换成一个整形数组，
 * 但是对于这种错误本Checker不会报错，
 * 这个初始化的错误会在RClass第一次初始化这个成员的时候报错。
 */
public class MemberScriptChecker extends ScriptUnForceChecker {
	
	public MemberScriptChecker() {
		super("成员声明");
	}

	/**
	 * 检查从本行开始是否正确声明了成员信息。
	 * @param scriptLines
	 * 		脚本文件信息，
	 * 		每一个数组的元素都是脚本的一行字符串。
	 * @param checkLine
	 * 		从指定行数开始检查脚本信息。
	 * @param checkResult
	 * 		存储的检查结果。
	 * @return
	 * 		发生任何错误返回-1，
	 * 		正确返回RClassScriptStruct.rClassMemberDeclaration_End的下一行的行数。
	 */
	@Override
	protected int checkDetail(ArrayList<String> scriptLines, int checkLine) {
		boolean hitEnd = false;
		int scriptSize = scriptLines.size();
		String scriptLine = scriptLines.get(checkLine);
		if (scriptLine.startsWith(RClassScriptStruct.rClassMemberDeclaration)){
			++checkLine;
			
			//检查静态成员的声明
			checkLine = 
					new StaticMemberChecker().check(scriptLines, checkLine);
			
			if (checkLine < 0){
				return -1;
			}
			
			for (; checkLine >= 0 && checkLine < scriptSize; ++checkLine){
				scriptLine = scriptLines.get(checkLine);

				//第三层次的声明符号
				if (scriptLine.startsWith(RClassScriptStruct.hierarchy_3_Symbol)){
					//发现第三层次的声明符号
					//检查成员声明是否包含非法字符
					scriptLine = scriptLine.substring(
							RClassScriptStruct.hierarchy_3_Symbol.length());
					if ( ! RMemberChecker.check(scriptLine)){
						showGrammarErrorMessage(checkLine, "检查成员声明的过程中发现成员声明出错，"
								+ "可能包含非法字符，也可能声明字段数量不是2或者3，请检查本行："
								+ scriptLine);
						return -1;
					}
				} else if (scriptLine.startsWith(RClassScriptStruct.hierarchy_1_Symbol)){
					//发现第一层次的声明符号，
					//检查是否是成员声明结束的字符串。
					if ( scriptLine.startsWith(RClassScriptStruct.rClassMemberDeclaration_End)){
						hitEnd = true;
						break;	
					} else {
						showGrammarErrorMessage(checkLine, "检查成员声明的过程中发现第一层次符号，"
								+ "但是这一行并不是成员声明结束的信息(" 
								+ RClassScriptStruct.rClassMemberDeclaration_End + ")，层次错误。");
						return -1;
					}
				} else {
					showGrammarErrorMessage(checkLine, "检查成员声明的过程中没有以层次符号声明的信息，"
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
		
		return checkLine;
	}

}
