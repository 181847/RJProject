package unfinishedClass.customRClass.scriptChecker;

import java.util.ArrayList;

public abstract class ScriptUnForceChecker extends ScriptForceChecker {

	public ScriptUnForceChecker(String checkType) {
		super(checkType);
	}

	/**
	 * 从当前行数开始进行脚本检查，
	 * 本次检查的信息可以不存在
	 * （比如可以没有实现接口父类）。
	 * @param scriptLines
	 * 		包含脚本文件的数组列表，
	 * 		每一个数组的元素存储的都是脚本一行的字符串，
	 * 		第0号单元保存的应该是这个脚本的具体文件路径。
	 * @param checkLine
	 * 		从此行开始检查脚本信息。
	 * @return
	 * 		返回当前脚本检查结束后的下一个检查的行数，
	 * 		（比如检查完第1行的RClass名字，
	 * 		就返回2）；
	 * 		如果本次检查的结果失败，返回小于0的数字。
	 */
	@Override
	public int check(ArrayList<String> scriptLines, int checkLine) {
		//如果checkLine小于0，
		//表示之前的某次检查发现了语法错误，
		//本次就不用再检查了。
		if (checkLine < 0){
			return checkLine;
		}
		
		if (checkLine >= 0 && checkLine < scriptLines.size()){
			return checkDetail(scriptLines, checkLine);
		}
		return checkLine;
	}

}
