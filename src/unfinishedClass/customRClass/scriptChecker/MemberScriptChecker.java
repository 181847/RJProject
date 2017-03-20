package unfinishedClass.customRClass.scriptChecker;

import java.util.ArrayList;

import basicTool.RLogger;
import unfinishedClass.customRClass.RClassScriptStruct;

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
public class MemberScriptChecker extends ScriptChecker {
	protected String memberDeclaration;

	/**
	 * @param memberType
	 * 		发生错误时向日志标识出错误的信息。
	 * @param memberDeclaration
	 * 		用来具体检查成员的声明字段，
	 * 		这些字段一般是RClassScriptStruct或者FunctionScriptStruct中的静态成员字符串，
	 * 		在脚本文件中，
	 * 		成员的声明都会伴随这些字符串先声明相应的成员有多少个，
	 * 		然后在之后的几行内添加相应数量的成员的类型、名字、初始值。
	 */
	public MemberScriptChecker(String memberType, String memberDeclaration) {
		super(memberType + "-成员声明");
		if (memberDeclaration == null){
			this.memberDeclaration = "**未知成员申明字段**";
		} else {
			this.memberDeclaration = memberDeclaration;
		}
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
	 * 		如果没有声明成员，
	 * 		返回checkLine；
	 * 		如果声明了成员，且没有发生错误，
	 * 		返回checkLine + 1 + 声明的成员数量；
	 * 		如果发生了任何错误，
	 * 		返回checkLine + 1。
	 */
	@Override
	protected int checkDetail(ArrayList<String> scriptLines, int checkLine, ScriptCheckResult checkResult) {
		int memberNum;
		//获取脚本信息
		String scriptLine = scriptLines.get(checkLine);
		
		//本行是否声明接口
		if (scriptLine.startsWith(memberDeclaration)){
			
			scriptLine = 
					scriptLine.substring(memberDeclaration.length());
			
			//检查接口数量声明是否符合规范
			try{
				memberNum = Integer.parseInt(scriptLine);
			} catch (NumberFormatException e){
				RLogger.logError("脚本：" + checkType + " 出错，第" + checkLine
						+ "行  成员声明出错，"
						+ "声明的成员数量无法从字符串转换成整形数字。");
				RLogger.logException(e);
				checkResult.setResult(false);
				memberNum = 0;
			}
			
			//检查接口数量声明是否为正整数
			if (memberNum < 0){
				RLogger.logError("脚本：" + checkType + " 出错，第" + checkLine
						+ "行  成员声明出错，"
						+ "声明的成员数量不能为负数。");
				checkResult.setResult(false);
				memberNum = 0;
			}
			
			//检查固定数量的接口名字的正确性
			for (int i = 1; i < memberNum && checkResult.isRight(); ++i){
				
				//检查是否到了脚本文件的结尾
				if(checkLine + i < scriptLines.size()){
					scriptLine = scriptLines.get(checkLine + i);
					
					//检查接口声明是否以'$'开头
					if (scriptLine.startsWith("$")){
						scriptLine = 
								scriptLine.substring(1);
						//检查接口名字是否包含非法字符
						if (RMemberChecker.check(scriptLine.substring(1))){
							//TODO
						} else {
							RLogger.logError("脚本：" + checkType + " 出错，第" + checkLine + i
									+ "行  成员声明出错，"
									+ "该行声明的成员名字可能包含以下非法字符："
									+ RMemberChecker.getErrorStrings()
									+ "，或者该行的声明不符合规范，该行中的成员信息：" + scriptLine);
							checkResult.setResult(false);
						}//if 检查接口名字是否包含非法字符
						
					} else {
						RLogger.logError("脚本：" + checkType + " 出错，第" + checkLine + i
								+ "行  成员声明出错，"
								+ "该行的信息没有以'$'字符开始。");
						checkResult.setResult(false);
					}//if 检查接口声明是否以'$'开头
					
				} else {
					RLogger.logError("脚本：" + checkType + " 出错，第" + checkLine
							+ "行，该脚本声明了" + memberNum
							+ "个成员，但是程序只检查到第" + i
							+ "个成员的时候到达了脚本文件的末尾，"
							+ "接口声明缺失。");
					checkResult.setResult(false);
					
				}//if 检查是否到了脚本文件结尾
			}//for 检查各个接口名称的正确性
		}//if 本行是否声明接口
		return 0;
	}

}
