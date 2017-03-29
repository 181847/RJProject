package unfinishedClass.customRClass.scriptChecker;

import java.util.ArrayList;

import basicTool.RLogger;

public class TotalFunctionScriptChecker extends ScriptChecker {

	public TotalFunctionScriptChecker(String checkType) {
		super("Function声明");
	}

	/**
	 * 检查从本行开始是否正确声明了Function，
	 * 出错情况包括以下：
	 * 接口RClass声明了构造Function、静态Function、非抽象成员Function；
	 * 抽象RClass没有声明构造Function、
	 * 在没有父类以及声明了成员Function的情况下，没有声明抽象成员Function；
	 * 普通RClass没有声明构造Function、
	 * 普通RClass声明了抽象成员Function
	 * （注意：本方法不会去检查这个RClass是否实现了所有父类中没有实现的Function，
	 * 本方法只会关注这些方法是否按照正确的语序来书写，
	 * 以及声明的Function类型是否和脚本声明的RClass类型一致。）
	 * @param scriptLines
	 * 		脚本文件信息，
	 * 		每一个数组的元素都是脚本的一行字符串。
	 * @param checkLine
	 * 		从指定行数开始检查脚本信息。
	 * @param checkResult
	 * 		存储的检查结果。
	 * @return
	 * 		声明的Function类型和checkResult中存储的脚本信息不一致，
	 * 		比如接口RClass声明了构造Function，
	 * 		返回checkLine；
	 * 		脚本声明正确，返回checkLine + 所有Function声明的总的行数。
	 */
	@Override
	protected int checkDetail(ArrayList<String> scriptLines, int checkLine, ScriptCheckResult checkResult) {
		int nextLine = checkLine;
		//先检查checkResult记录的RClass类型，入股
		nextLine = new ConFunScriptChecker()
				.check(scriptLines, checkLine, checkResult);
		nextLine = new StaticFunScriptChecker()
				.check(scriptLines, checkLine, checkResult);
		nextLine = new FunScriptChecker()
				.check(scriptLines, checkLine, checkResult);
		
		return nextLine;
	}
	
	@Override
	public int check(ArrayList<String> scriptLines, int checkLine, ScriptCheckResult checkResult){
		//TODO
		int nextLine = checkLine;
		//确保之前的检查结果为true
		if (checkResult.isRight()){
			
			//查看checkLine能否可提取脚本信息
			//如果不能提取，表示没有声明任何Function，
			//只有接口类型的RClass可以不声明任何Function
			if (checkLine >= 0 && checkLine < scriptLines.size()){
				
				nextLine = checkDetail(scriptLines, checkLine, checkResult);
				
			//检查是否是接口类型的RClass
			} else if (0 != checkResult.getRClassType()){
				RLogger.logError(checkType + "中出现错误，"
						+ "脚本定义的RClass类型是抽象或者普通类型，"
						+ "但是检查Function时已经到了脚本的末尾，"
						+ "没有发现Function的定义信息，"
						+ "抽象或者普通类型的RClass至少要声明构造Function。");
				checkResult.setResult(false);
			}//if 查看checkLine能否可提取脚本信息
		} else {
			RLogger.logError(checkType + "检查取消，因为本次检查之前的检查结果false，不能进行本次检查。");
		}
		
		if (checkResult.isRight()){
			return nextLine;
		} else {
			return checkLine;
		}
	}

}
