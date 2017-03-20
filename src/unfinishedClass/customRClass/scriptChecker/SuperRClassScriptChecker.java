package unfinishedClass.customRClass.scriptChecker;

import java.util.ArrayList;

import basicTool.RLogger;
import unfinishedClass.customRClass.RClassScriptStruct;

/**
 * 检查CustomRClass的父类声明是否符合脚本的定义，
 * 不能出现脚本声明的是接口类型的RClass，
 * 但是它声明了父类这种情况。
 */
public class SuperRClassScriptChecker extends ScriptChecker {
	public SuperRClassScriptChecker() {
		super("CustomRClass父类声明");
	}

	/**
	 * 检查本行是否正确声明了CustomRClass的继承父类，
	 * 接口类型的RClass不能声明父类。
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
	protected int checkDetail(ArrayList<String> scriptLines, int checkLine, ScriptCheckResult checkResult) {
		//获取本行信息
		String scriptLine = scriptLines.get(checkLine);
		
		//本行是否声明父类
		if (scriptLine.startsWith(RClassScriptStruct.rClassExtendsDeclaration)){
			
			//声明了父类，查看先前的检查结果，
			//判断这个脚本信息能否声明父类，
			//要求，接口类型不能声明父类。
			if (checkResult.getRClassType() == 0){
				RLogger.logError("脚本：" + checkType + " 出错，第" + checkLine
						+ "行  父类声明出错，"
						+ "先前的检查结果表明这个脚本定义的是一个接口类型，"
						+ "但是这个脚本却声明了一个父类，"
						+ "接口不允许声明父类，如果接口要继承其他接口，"
						+ "请使用“" + RClassScriptStruct.rClassInterfaceDeclaration 
						+ "”加被继承接口数量，换行，相应数量的被继承接口的名字，"
						+ "这样的方式来声明要继承的接口。");
				checkResult.setResult(false);
			} else {
				
				//获取父类名字
				scriptLine = 
						scriptLine.substring(RClassScriptStruct.rClassExtendsDeclaration.length());
				if (RNameChecker.check(scriptLine)){
					//TODO
					//RLogger.log("CustomRClass");
				} else {
					RLogger.logError("脚本：" + checkType + " 出错，第" + checkLine
							+ "行  父类声明出错，"
							+ "父类的名字中包含如下非法字符串："
							+ RNameChecker.getErrorStrings());
				}
			}//if 父类是否为接口
			
			++ checkLine;
		}//if 本行是否声明父类
		
		return checkLine;
	}

}
