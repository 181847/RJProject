package unfinishedClass.customRClass;

/**
 * 用于检查脚本内容中是否包含非法字符的检查机制。
 */
public class RStringChecker {
	//被检查的所有非法字符。
	public static String[] illegalStrings = {
			"@"};
	
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
}
