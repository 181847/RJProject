package unfinishedClass.customRClass;

import unfinishedClass.customRClass.scriptBlock.ScriptDeclaration;

/**
 * 用于检查脚本内容中是否包含非法字符的检查机制。
 */
public class RStringChecker {
	//被检查的所有非法字符。
	public static String[] illegalStrings = {
			"@",
			"(",
			")",
			"{",
			"}"
			};
	
	public static String illegalStringCollection = null;
	
	public static boolean check(String scriptLine){
		for (String checker : illegalStrings){
			if (-1 != scriptLine.indexOf(checker)){
				//发现非法字符串。
				return false;
			}
		}
		return true;
	}
	
	/**
	 * @return
	 * 		所有非法字符串，
	 * 		每个字符串之间用 “；” 分隔。
	 */
	public static String getIllegalStrings(){
		if (illegalStringCollection == null){
			illegalStringCollection = "";
			for (String checker : illegalStrings){
				illegalStringCollection += "；" + checker;
			}
		}
		return illegalStringCollection;
	}

	public static boolean checkVar(String informationString) {
		if ( ! check(informationString)){
			return false;
		}
		
		//分割信息，查看是否按照空格分成两个或者三个部分
		String[] vars = informationString.split(" ");
		if (vars.length != 2 || vars.length != 3){
			return false;
		}
		
		return true;
	}

	public static boolean checkExcutee(String informationString) {
		// TODO Auto-generated method stub
		return check(informationString);
	}

	public static boolean checkFunctionName(String informationString) {
		if (informationString.indexOf(ScriptDeclaration.nameHierarchy) == -1){
			return false;
		}
		return check(informationString);
	}

	public static boolean checkArc(String informationString) {
		// TODO Auto-generated method stub
		
		String[] arcStrings = informationString.split(ScriptDeclaration.arrow);
		
		//检查弧线是否有两个端点
		if (arcStrings.length != 2){
			return false;
		}
		
		//将弧线的两个端点用序号分隔符分隔开来
		//相当于把“1.funEnd”变成“1”和“funEnd”
		String[] foreVtx = arcStrings[0].split(ScriptDeclaration.indexSplit);
		String[] lateVtx = arcStrings[0].split(ScriptDeclaration.indexSplit);
		
		//检查端点能够分成两个部分
		if (foreVtx.length != 2 ||
				lateVtx.length != 2){
			return false;
		}
		
		//检查端点的前一个部分是否包含的是数字
		if ( ! RStringChecker.checkIntNumber(foreVtx[0])
			|| ! RStringChecker.checkIntNumber(lateVtx[0])){
				return false;
		}
		
		//检查组件名是否包含非法字符，
		//如果发现非法字符就检查是否是特殊的组件名字，
		//如果不是特殊组件的名字才返回false。
		if (check(foreVtx[1]) && check(lateVtx[1])){
			//不包含非法字符，直接返回true
			return true;
		} else if (checkSpecial(
						new String[]{foreVtx, lateVtx})){
				return true;
		} else {
			return false;
		}
	}
}
