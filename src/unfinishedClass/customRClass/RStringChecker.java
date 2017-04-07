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
		if (vars.length == 2 || vars.length == 3){
			return true;
		}
		
		return false;
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

	/**
	 * 检查弧线的声明是否正确，
	 * 被检查的内容形如"1.funEnd -> 4.construct"。
	 * @param informationString
	 * 		被检查的弧线具体声明，
	 * 		不包括前面的弧线类型声明，
	 * 		形如“1.funEnd -> 4.construct”，
	 * 		而不是“EtoE: 1.funEnd -> 4.construct”。
	 * @return
	 */
	public static boolean checkArc(String informationString) {
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
		
		//检查弧线的第一个端点是否包含非法字符
		if ( ! check(foreVtx[1])){
			//如果包含非法字符是否为特殊组件名称
			if ( ! checkSpecial(foreVtx[1])){
				return false;
			}
		}
		
		//检查弧线的第二个端点是否包含非法字符
		if ( ! check(lateVtx[1])){
			//如果包含非法字符是否为特殊组件名称
			if ( ! checkSpecial(lateVtx[1])){
				return false;
			}
		}
		
		return true;
	}

	/**
	 * 检查字符串是否是特殊的Function组件名称，
	 * 这些组件的名称包含一些非法字符，
	 * 只能由系统来生成这些包含非法字符的组件。
	 * @param strings
	 * 		被检查的Function组件名字。
	 * @return
	 * 		属于特殊组件名字的话返回true，
	 * 		不是特殊组件名字的话返回false。
	 */
	public static boolean checkSpecial(String string) {
		//检查特殊参数组件名称
		for (String special : ScriptDeclaration.specialParameters){
			if (special.equals(string)){
				return true;
			}
		}
		//检查特殊执行出口组件名称
		for (String special : ScriptDeclaration.specialExcuters){
			if (special.equals(string)){
				return true;
			}
		}
		return false;
	}

	/**
	 * 检查字符串能否变成整形数字。
	 * @param string
	 * 		被检查的字符串。
	 * @return
	 * 		能转换成整形数字返回true；
	 * 		不能返回false。
	 */
	public static boolean checkIntNumber(String string) {
		try{
			Integer.parseInt(string);
		} catch (NumberFormatException e){
			return false;
		}
				
		return true;
	}

	/**
	 * 检查是否正确声明了一个方形区域位置，
	 * 用两个坐标来表示方形区域的左上角和右下角，
	 * 形如“(55.1, 22.1) -> (21.0, 50.1)”。
	 * @param informationString
	 * 		被检查的字符串。
	 * @return
	 * 		满足方形区域声明返回true，
	 * 		不满足返回false。
	 */
	public static boolean checkRect(String informationString) {
		String[] vetxs = informationString.split(ScriptDeclaration.arrow);
		if (vetxs.length != 2){
			return false;
		}
		
		//检查两个端点是否正确声明了位置信息
		if ( ! checkLocation(vetxs[0]) 
				|| ! checkLocation(vetxs[1])){
			return false;
		}
		return true;
	}

	/**
	 * 检查是否正确声明了一个坐标信息，
	 * 坐标的XY轴数值要求能够转换成Double类型，
	 * 前后用括号括起来，
	 * 形如“(21.0, 50.1)”。
	 * @param string
	 * 		被检查的位置声明。
	 * @return
	 * 		满足要求返回true，
	 * 		不满足要求返回false。
	 */
	public static boolean checkLocation(String string) {
		//检查位置信息是否用相应的位置开始和结束符号包裹
		if ( ! string.startsWith(ScriptDeclaration.locationStart)
				|| ! string.endsWith(ScriptDeclaration.locationEnd)){
			return false;
		}
		
		//剥去位置声明前后的包裹符号
		string = string.substring(ScriptDeclaration.locationStart.length(), 
				string.length() - ScriptDeclaration.locationEnd.length());
		
		//将两个坐标数字从中间分开
		String[] XYs = string.split(ScriptDeclaration.numberSplit);
		if (XYs.length != 2){
			return false;
		}
		
		//分别检查两个数字能否转为Double类型
		if ( ! checkDoubleNumber(XYs[0])
				|| ! checkDoubleNumber(XYs[1])){
			return false;
		}
		
		return true;
	}

	/**
	 * 检查字符串能否转换成Double型数据。
	 * @param string
	 * 		被检查的字符串。
	 * @return
	 * 		如果不能转换就返回false，
	 * 		如果能转换成Double型数据就返回true。
	 */
	public static boolean checkDoubleNumber(String string) {
		try{
			Double.parseDouble(string);
		} catch (NumberFormatException e){
			return false;
		}
		return true;
	}
}
