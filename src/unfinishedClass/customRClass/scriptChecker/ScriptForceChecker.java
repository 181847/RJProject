package unfinishedClass.customRClass.scriptChecker;

import java.util.ArrayList;

import basicTool.RLogger;

/**
 * 这个脚本检查类型要求内部的被检查信息必须存在，
 * 不存在的话就算出错。
 */
public abstract class ScriptForceChecker extends AbstractScriptChecker{
	
	public ScriptForceChecker(String checkType) {
		super(checkType);
	}

	/**
	 * 从当前行数开始进行脚本检查，
	 * 而且本次检查的信息必须存在，
	 * 如果不存在就算作语法错误。
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
	public int check(ArrayList<String> scriptLines, int checkLine){
		//如果checkLine小于0，
		//表示之前的某次检查发现了语法错误，
		//本次就不用再检查了。
		if (checkLine < 0){
			return checkLine;
		}
		
		//确保checkLine可提取脚本信息
		if (checkLine >= 0 && checkLine < scriptLines.size()){
			return checkDetail(scriptLines, checkLine);
		} else {
			//超出了脚本的范围
			return -1;
		}
	}

	/**
	 * 此方法用来检查脚本内部的详细情况，
	 * 由子类实现，从而达到不同的检查目的，
	 * 本方法保证肯定能够在ScriptLines的checkLine处获得脚本信息，
	 * 且checkResult一定是true。
	 * @param scriptLines
	 * 		脚本文件信息，
	 * 		每一个数组的元素都是脚本的一行字符串。
	 * @param checkLine
	 * 		从指定行数开始检查脚本信息。
	 * @param checkResult
	 * 		存储的检查结果。
	 * @return
	 * 		如果检查的结果成功，
	 * 		返回下一个检查的位置。
	 * 		如果失败的话只需要设置checkResult为false就可以了，
	 * 		返回值不需要特别指定，因为，
	 * 		调用checkDetail()的check()方法会在checkResult为false的时候自己设定返回值，
	 * 		而不依靠checkDetail()的返回值。
	 */
	protected abstract int checkDetail(ArrayList<String> scriptLines, int checkLine);
}
