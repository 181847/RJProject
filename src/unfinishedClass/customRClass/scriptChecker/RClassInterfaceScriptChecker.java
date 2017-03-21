package unfinishedClass.customRClass.scriptChecker;

import java.util.ArrayList;

import basicTool.RLogger;
import unfinishedClass.customRClass.script.RClassScriptStruct;

/**
 * 检查检查CustomRClass的接口声明是否正确。
 */
public class RClassInterfaceScriptChecker extends ScriptChecker {

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
	 * 		发生任何错误，返回checkLine + 1。
	 */
	@Override
	protected int checkDetail(ArrayList<String> scriptLines, int checkLine, ScriptCheckResult checkResult) {
		int interfaceNum;
		//获取本行信息
		String scriptLine = scriptLines.get(checkLine);
		
		//本行是否声明接口
		if (scriptLine.startsWith(RClassScriptStruct.rClassInterfaceDeclaration)){
			
			scriptLine = 
					scriptLine.substring(RClassScriptStruct.rClassInterfaceDeclaration.length());
			
			//检查接口数量声明是否符合规范
			try{
				interfaceNum = Integer.parseInt(scriptLine);
			} catch (NumberFormatException e){
				RLogger.logError("脚本：" + checkType + " 出错，第" + checkLine
						+ "行  接口声明出错，"
						+ "声明的接口数量无法从字符串转换成整形数字。");
				RLogger.logException(e);
				checkResult.setResult(false);
				interfaceNum = 0;
			}
			
			//检查接口数量声明是否为正整数
			if (interfaceNum < 0){
				RLogger.logError("脚本：" + checkType + " 出错，第" + checkLine
						+ "行  接口声明出错，"
						+ "声明的接口数量不能为负数。");
				checkResult.setResult(false);
				interfaceNum = 0;
			}
			
			//检查固定数量的接口名字的正确性
			for (int i = 1; i <= interfaceNum && checkResult.isRight(); ++i){
				
				//检查是否到了脚本文件的结尾
				if(checkLine + i < scriptLines.size()){
					scriptLine = scriptLines.get(checkLine + i);
					
					//检查接口声明是否以'$'开头
					if (scriptLine.startsWith("$")){
						
						//检查接口名字是否包含非法字符
						if (RNameChecker.check(scriptLine.substring(1))){
							//TODO
						} else {
							RLogger.logError("脚本：" + checkType + " 出错，第" + checkLine + i
									+ "行  接口声明出错，"
									+ "该行声明的接口名字包含以下非法字符："
									+ RNameChecker.getErrorStrings());
							checkResult.setResult(false);
							interfaceNum = 1;
						}//if 检查接口名字是否包含非法字符
						
					} else {
						RLogger.logError("脚本：" + checkType + " 出错，第" + checkLine + i
								+ "行  接口声明出错，"
								+ "该行的信息没有以'$'字符开始。");
						checkResult.setResult(false);
						interfaceNum = 1;
					}//if 检查接口声明是否以'$'开头
					
				} else {
					RLogger.logError("CustomRClass脚本中接口声明出错，"
							+ "该脚本声明了" + interfaceNum
							+ "个接口，但是程序只检查到第" + i
							+ "个接口的时候到达了脚本文件的末尾，"
							+ "接口声明缺失。");
					checkResult.setResult(false);
					interfaceNum = 1;
				}//if 检查是否到了脚本文件结尾
			}//for 检查各个接口名称的正确性
			
		} else {
			return checkLine;
		}//if 本行是否声明接口
		
		return checkLine + interfaceNum;
	}
}
