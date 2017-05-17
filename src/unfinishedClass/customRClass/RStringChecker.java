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
	
	/**
	 * 检查类名是否正确，
	 * 不能包含非法字符，
	 * 所有类都必须在包里面，
	 * 这表明类的全名中至少要包含一个ScripDeclaration.nameHierarchy，
	 * 来表明类所属的包，形如“defaultPackage.myRClass”。
	 * @param informationString
	 * 		被检查的类名。
	 * @return
	 * 		符合要求返回true，
	 * 		否则返回false。
	 */
	public static boolean checkRClassName(String informationString){
		if (-1 == informationString.indexOf(ScriptDeclaration.nameHierarchy)){
			return false;
		}
		return check(informationString);
	}

	/**
	 * 检查Function的名字是否正确，
	 * 要求Function的名字必须包括Function所属的类名，
	 * 类名中至少要包含一个分分割符，
	 * 而类名和Function名字中间也要要包括一个分割符，
	 * 所以informationString至少要两个分割符，
	 * 形如“basic.Integer.addInteger”。
	 * @param informationString
	 * 		被检查的Function全名称。
	 * @return
	 * 		符合要求返回true，
	 * 		否则返回false。
	 */
	public static boolean checkFunctionName(String informationString) {
		int lastHierarchy = 
				informationString.lastIndexOf(ScriptDeclaration.nameHierarchy);
		if (lastHierarchy == -1 
				|| lastHierarchy == informationString
									.indexOf(ScriptDeclaration.nameHierarchy)){
			return false;
		}//if
		
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
		String[] lateVtx = arcStrings[1].split(ScriptDeclaration.indexSplit);
		
		//检查端点能够分成两个部分
		if (foreVtx.length != 2 
				|| lateVtx.length != 2){
			return false;
		}
		
		//检查端点的前一个部分是否包含的是数字
		if ( ! RStringChecker.checkIntNumber(foreVtx[0])
			|| ! RStringChecker.checkIntNumber(lateVtx[0])){
				return false;
		}
		
		//检查端点的后一个部分是否是正确的function组件名称
		if ( ! checkFunComponent(foreVtx[1], true)
				|| ! checkFunComponent(lateVtx[1], true)){
			return false;
		}
		
		return true;
	}

	/**
	 * 检查字符串是否是合法的Function组件名字，
	 * 检查要分两种情况，
	 * 一种是用户定义的Function当中的组件名称，
	 * 另一种则是用户定义的Function连接弧线中的组件名字，
	 * 用户定义的Function的组件名不能包含非法字符；
	 * 弧线中的组件名字可以有非法字符，
	 * 但是要求这个名字必须是程序中固定的几个特殊名字，
	 * 这些特殊的名字在ScriptDeclaration有定义。
	 * @param string
	 * 		被检查的组件名字。
	 * @param inArc
	 * 		该组件的名字是否出现于弧线定义中。
	 * @return
	 * 		如果inArc为false，
	 * 		组件名字中没有非法字符时，返回true、
	 * 		否则返回false；
	 * 		如果inArc为true，
	 * 		组件名字中没有非法字符时，返回true、
	 * 		包含非法字符，但是为特殊的组件名称，返回true、
	 * 		其他情况返回false。
	 */
	private static boolean checkFunComponent(String string, boolean inArc) {
		if (inArc){
			//弧线声明中的情况
			if ( ! check(string)){
				if ( ! checkSpecial(string)){
					return false;
				}
			}
			
		} else {
			//Function组件定义中的情况
			if ( ! check(string)){
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

	/**
	 * 检查SubFunction的声明是否符合规范，
	 * 形如“(-4,-7)basic.Integer.addInteger{3}”，
	 * 前面的二维坐标必须包含，而且其数字必须能够转化成Double型；
	 * 中间的是Function的名字，这一部分不允许出现非法字符；
	 * 最后用大括号包含的是Function的Modify信息。
	 * Modify信息不会进行非法字符的检查，
	 * 程序将正数第一个“{”认为是Modify信息的开始，
	 * 将后数第一个“}”认为是Modify信息的结束，
	 * 中间的信息不会进行检查。
	 * @param informationString
	 * 		被检查的SubFun声明。
	 * @return
	 * 		正确返回true，
	 * 		错误返回false。
	 */
	public static boolean checkSubFun(String informationString) {
		//分割subFun信息
		String[] subFunDetail = splitSubFun(informationString);
		
		if (subFunDetail[0] == null 
				|| subFunDetail[1] == null
				|| subFunDetail[2] == null){
			return false;
		}
		
		if ( ! checkLocation(subFunDetail[0])
				|| ! checkFunctionName(subFunDetail[0])){
			return false;
		}
		return true;
	}

	/**
	 * 将informationString按照subFun的信息分割成，
	 * 坐标信息、subFun名称信息、Modify信息 
	 * 组成的一维数组当中，
	 * 分割的方式如下：
	 * <p>1. 坐标信息，从字符串的开头到第一个ScriptDescription.locationEnd；
	 * <p>2. subFun名称信息，从字符串的第一个ScriptDescription.locationEnd后面的字符到 
	 * 第一个ScriptDescription.modifyStart前面的字符（如果没有这个字符就到字符串的结尾）；
	 * <p>3. Modify信息，从字符串的第一个ScriptDescription.modifyStart 
	 * 到 最后一个ScriptDescription.modifyEnd。
	 * <p>处理结果预测：<br>
	 * “(-4,-7)basic.Integer.addInteger{3}”<br>
	 * 		-> {"(-4,-7)", "basic.Integer.addInteger", "{3}"}；<br><br>
	 * “(-4,-7basic.Integer.addInteger{3}”<br>
	 * 		-> {null, null, "{3}"}；<br><br>
	 * “(-4,-7gkjhg)basic.Integer.addInteger{3}”<br>
	 * 		-> {"(-4,-7gkjhg)", "basic.Integer.addInteger", "{3}"}；<br><br>
	 * “(-4,-7gkjhg)basic.Integer.addInteger3}”<br>
	 * 		-> {"(-4,-7gkjhg)", null, null}；<br><br>
	 * “(-4,-7gkjhg)basic.Integer.addInteger{3”<br>
	 * 		-> {"(-4,-7gkjhg)", basic.Integer.addInteger, null}；<br><br>
	 * @param informationString
	 * 		被处理的subFun信息。
	 * @return
	 * 		一个长度为3的一维数组，
	 * 		如果相关的信息没有找到的话就将相应的数组元素设为null。
	 */
	private static String[] splitSubFun(String informationString) {
		// TODO Auto-generated method stub
		String[] result = new String[3];
		
		int locationEnd = informationString.indexOf(ScriptDeclaration.locationEnd);
		int modifyStart = informationString.indexOf(ScriptDeclaration.modifyStart);
		int modifyEnd = informationString.indexOf(ScriptDeclaration.modifyEnd);
		
		//提取位置信息
		if (locationEnd != -1){
			result[0] = informationString.substring(0, locationEnd + 1);
		}
		
		//提取functionName信息
		if (locationEnd != -1
				&& modifyStart != -1
				&& locationEnd < modifyStart){
			result[1] = informationString.substring(locationEnd + 1, modifyStart);
		}
		
		//提取Modify信息
		if (modifyStart != -1 
				&& modifyEnd != -1 
				&& modifyStart < modifyEnd){
			result[2] = informationString
					.substring(modifyStart, modifyEnd + 1);
		}//if
		
		return result;
	}

	/**
	 * 检查Exucter的声明是否正确。
	 * @param informationString
	 * 		Excuter的声明，
	 * 		例如“NE finished”，
	 * 		或者"EE FILE_NOT_EXSIST"，
	 * 		前一个必须是“NE”或者“EE”。
	 * @return
	 * 		如果符合要求就返回true，
	 * 		否则返回false。
	 */
	public static boolean checkExcuter(String informationString) {
		String[] infoArray = informationString.split(ScriptDeclaration.fence);
		//如果分割之后的数组长度不为2
		//或者长度为2，但是第一个字符串不是
		//ScriptDeclaration.normalExcuter
		//或者 ScriptDeclaration.exceptionExcuter，
		//返回false。
		if (infoArray.length != 2
				|| ! ( infoArray[0].equals(ScriptDeclaration.normalExcuter)
				|| infoArray[1].equals(ScriptDeclaration.exceptionExcuter))){
			return false;
		}
		
		return true;
	}
}
