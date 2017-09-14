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

	/**
	 * 检查单行的变量声明是否正确，
	 * 这里检查的时候要注意类型的相关，
	 * 变量的类型可以是RClass的一个名字，
	 * 也可以是一个一个单独的泛参，
	 * 这两种的命名规则不同，
	 * 两种类型的变量都是允许的。
	 * @param informationString
	 * 		单行的变量声明，
	 * 		形如“basic.Integer member3 -12”。
	 * @return
	 * 		符合要求返回true，
	 * 		不符合要求返回false。
	 */
	public static boolean checkVar(String varDeclaration) {
		boolean checkResult = false;
		//分割信息，查看是否按照空格分成两个或者三个部分
		String[] vars = varDeclaration.split(ScriptDeclaration.generalSplit);
		if (vars.length == 2 || vars.length == 3){
			checkResult = (checkRClassName(vars[0]) //类型部分满足RClass名称命名规范
					|| checkComponentName(vars[0]))	//类型部分满足泛参命名规范（即组件命名规范）
					&& checkComponentName(vars[1]);	//变量名部分满足组件命名规范
		}
		
		return checkResult;
	}

	/**
	 * 检查字符串是否符合组件命名规范，
	 * 组件名称不能包含以下字符，
	 * 空格<br>
	 * . （半角英文点号）<br>
	 * _@<br>
	 * :<br>
	 * <<br>
	 * ><br>
	 * (<br>
	 * )<br>
	 * {<br>
	 * }<br>
	 * @param conponentName
	 * 		被检查的组件名字。
	 * @return
	 * 		如果符合组件命名规范，就返回true；
	 * 		否则返回false。
	 */
	public static boolean checkComponentName(String componentName) {
		return 
			! (
				isEmpty(componentName)				//空串
			|| hasNewLine(componentName)			//换行符
			|| hasGeneralSplit(componentName)		//空格
			|| hasNameHierarchy(componentName)		//. （半角英文点号）
			|| hasAt(componentName)					//@
			|| hasAngleBrackets(componentName)		//<>
			|| hasLocationBrackets(componentName)	//()
			|| hasModifyBrackets(componentName)		//{}
			);
	}

	/**
	 * 检查字符串是否是空串。
	 * @param string
	 * @return
	 * 		如果string == null 或者 string == “”，
	 * 		返回true；否则返回false。
	 */
	public static boolean isEmpty(String string) {
		return string == null
				|| string.equals("");
	}

	/**
	 * 检查字符串中是否包含名称层次符，
	 * 这种符号用来将RClass的名字分层次管理，
	 * 相当于包的概念。
	 * @param information
	 * @return
	 * 		如果字符串中包含层次符号，返回true；
	 * 		否则返回false。
	 */
	private static boolean hasNameHierarchy(String information) {
		return -1 != 
				information
				.indexOf(ScriptDeclaration.nameHierarchy);
	}
	
	/**
	 * 检查类名是否正确，
	 * 不能包含非法字符，
	 * 所有类都必须在包里面，
	 * 这表明类的全名中至少要包含一个ScripDeclaration.nameHierarchy，
	 * 来表明类所属的包，形如“defaultPackage.myRClass”。
	 * 不能包含以下字符串：<br>
	 * 空格<br>
	 * :<br>
	 * _@<br>
	 * <<br>
	 * ><br>
	 * (<br>
	 * )<br>
	 * {<br>
	 * }<br>
	 * @param informationString
	 * 		被检查的类名。
	 * @return
	 * 		符合要求返回true，
	 * 		否则返回false。
	 */
	public static boolean checkRClassName(String rClassName){
		return
			splitByNameHeirarchy(rClassName)	//能被名称层次符完美分割
			&& ! (
				isEmpty(rClassName)				//空串
			|| hasNewLine(rClassName)			//换行符
			|| hasGeneralSplit(rClassName)		//空格
			|| hasAt(rClassName)				//@
			|| hasAngleBrackets(rClassName)		//<>
			|| hasLocationBrackets(rClassName)	//()
			|| hasModifyBrackets(rClassName)	//{}
			);	
	}
	
	/**
	 * 检查字符串是否能被名称层次符完美分割，
	 * 即至少有一个层次符，
	 * 且每个层次符之间的字符数量不少于1，
	 * 边界上不存在层次符。<br>
	 * 正确字符串：<br>
	 * “ff.bb”<br>
	 * “aa.b.c”<br>
	 * 错误字符串：<br>
	 * “ff..bb”<br>
	 * “aa.c.”<br>
	 * @param rClassName
	 * @return
	 * 		只有当{至少有一个名称层次符，
	 * 		被层次符分割的各个子字符串长度不小于1}，返回true；
	 * 		其他情况返回false。
	 */
	private static boolean splitByNameHeirarchy(String rClassName) {
		//在前后先加上两个边界符，
		//这就可以保证边界的层次符信息也会保留，
		//如果不加这两个边界符，
		//“.test1.test2..”的分解结果就是"test1'和"test2"，
		//这个错误的字符串可能会被判定为正确的，
		//然而实际上不能让层次符在边界上，
		//所以在分割前，现在字符串两边加上肯定能被分割的字符串，
		//这样一来边界上的层次符信息也能被保存下来，
		//边界字符串中的层次符能够和原本字符串中位于边界上的层次符合并，
		//而生成一个空串，
		//使得边界上的层次符能够被检测出来
		rClassName = "b." + rClassName + ".b";
		String[] names = 
				//由于这里使用的是正则表达式，
				//而名称分割符是特殊含义的字符串“.”，
				//要进行转义，
				//注意转义只需要在前面加上一个"\"字符，
				//然而在Java中“\”又是一个特殊字符，
				//还需要转义，
				//所以这里加上字符串串“\\”。
				rClassName.split("\\" + ScriptDeclaration.nameHierarchy);
		
		//由于加了边界符，
		//分割出来的部分至少有4个，
		//两个边界符各占一个，
		//中间的真实部分至少有2个。
		if (names.length <= 3){
			//分割出来的部分达不到4个表示分割失败。
			return false;
		}
		
		//通过下面的检查，
		//防止字符串中出现两个层次符紧靠在一起的情况，
		//由于添加的边界占了两个分段，
		//循环的时候跳过这两个分段（即第一个和最后一个）。
		for (int checker = 1; checker < names.length - 1; ++checker){
			if (names[checker].equals("")){
				//空串表示中间有两个层次符在一起，
				//不满足规范。
				return false;
			}
		}
		
		return true;
	}

	/**
	 * 检查是否含有尖括号。
	 * @param information
	 * @return
	 * 		如果包含尖括号（包含任意一个），返回true；
	 * 		否则返回false。
	 * 		
	 */
	private static boolean hasAngleBrackets(String information) {
		return 
				(
					-1 != information.indexOf		//找到尖括号开始符
					(ScriptDeclaration.angleBracketStart)
				) || (
					-1 != information.indexOf		//找到尖括号结束符
					(ScriptDeclaration.angleBracketEnd)
				);
	}

	/**
	 * 检查字符串中是否包含“@”符号。
	 * @param information
	 * @return
	 * 		如果包含“@”，返回true；
	 * 		否则返回false。
	 */
	private static boolean hasAt(String information) {
		return -1 != 
				information
				.indexOf(ScriptDeclaration.at);
	}

	/**
	 * 检查字符串是否包含Functio特有的Modifier信息标识符，
	 * 这种标识符有
	 * @param informationString
	 * @return
	 * 		如果包含Modifier标识符（包含任意一个），返回true；
	 * 		否则返回false。
	 */
	private static boolean hasModifyBrackets(String informationString) {
		return 
			(
				-1 != informationString.indexOf		//找到Modifier信息开始符
				(ScriptDeclaration.modify_start)
			) || (
				-1 != informationString.indexOf		//找到Modifier信息结束符
				(ScriptDeclaration.modify_end)
			);
	}

	/**
	 * 检查字符串是否包含代表二维坐标的括号，
	 * 无论是前括号还是后括号
	 * @param informationString
	 * @return
	 * 		如果包含坐标标识符（包含任意一个），返回true；
	 * 		否则返回false。
	 */
	private static boolean hasLocationBrackets(String informationString) {
		return 
			(
				-1 != informationString.indexOf		//找到位置开始符
				(ScriptDeclaration.location_start)
			) || (
				-1 != informationString.indexOf		//找到位置结束符
				(ScriptDeclaration.location_end)
			);
	}

	/**
	 * 检查字符串是否包含普通分割符，
	 * 在本脚本中指ScriptDeclaration.generalSplit所代表的字符串，
	 * 一般为一个空格。
	 * @param informationString
	 * @return
	 * 		如果包含普通分割符就返回true，
	 * 		否则返回false。
	 */
	private static boolean hasGeneralSplit(String informationString) {
		return -1 != informationString.indexOf(ScriptDeclaration.generalSplit);
	}

	/**
	 * 检查字符串是否有换行符
	 * @param informationString
	 * @return
	 * 		如果有"\n"，就返回true，
	 * 		否则返回false。
	 */
	private static boolean hasNewLine(String informationString) {
		return -1 != informationString.indexOf("\n");
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
	 * 形如：<br>
	 * (55.1, 22.1) -> (21.0, 50.1)”。
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
	 * 形如：<br>
	 * “(21.0, 50.1)”。
	 * @param string
	 * 		被检查的位置声明。
	 * @return
	 * 		满足要求返回true，
	 * 		不满足要求返回false。
	 */
	public static boolean checkLocation(String string) {
		//检查位置信息是否用相应的位置开始和结束符号包裹
		if ( ! string.startsWith(ScriptDeclaration.location_start)
				|| ! string.endsWith(ScriptDeclaration.location_end)){
			return false;
		}
		
		//剥去位置声明前后的包裹符号
		string = string.substring(ScriptDeclaration.location_start.length(), 
				string.length() - ScriptDeclaration.location_end.length());
		
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
		
		
		if ( checkLocation(subFunDetail[0])					//检查坐标信息
				&& checkRClassName(subFunDetail[1])			//检查类名信息
				&& checkComponentName(subFunDetail[2])){	//检查Function名字是否符合组件命名规范
			return true;
		}
		return false;
	}

	/**
	 * 将informationString按照subFun的信息分割成，
	 * 坐标信息、subFun名称信息、Modify信息 
	 * 组成的一维数组当中，
	 * 分割的方式如下：
	 * 查找Functtion修改信息的标志，找到两个前后标志后，
	 * 提取这两个前后标志之间的字符串作为数组的第4个元素
	 * （修改信息允许有空格），
	 * 然后将原信息从第一个修改信息标志之后的信息全部清除；
	 * 如果没有找到修改信息标志，
	 * 直接进入下一步。
	 * 将原信息按照分隔符来进行划分，
	 * 如果能分割成三个部分，
	 * 就将这三个部分放到返回结果数组的前三位中，
	 * 否则结果数组的前三位为null，然后返回。
	 * SubFun声明形如“(-5.1,-2.55) basic.Integer addInteger {3 5 4}”。
	 * 注意：本方法不对Function声明的规范性作保证，
	 * 不会检查命名是否符合规范或者坐标信息是否正确，
	 * 只是一个信息分拆的方法。
	 * @param informationString
	 * 		被处理的subFun信息。
	 * @return
	 * 		一个长度为4的一维数组，
	 * 		如果相关的信息没有找到的话就将相应的数组元素设为null。
	 */
	private static String[] splitSubFun(String informationString) {
		
		int mStartLoc, mEndLoc;
		mStartLoc = 	//修改信息开始位置
				informationString.indexOf(ScriptDeclaration.modify_start);
		mEndLoc = 		//修改信息结束位置
				informationString.indexOf(ScriptDeclaration.modify_end);
		
		
		//长度为4的数组，所有元素初始化为null。
		String[] result = 
				new String[]{null, null, null, null};
		
		//修改信息的开始和结束符都存在，
		//将这一部分包裹的信息提取出来放到result[3]中。
		if (mStartLoc >= 0 && mEndLoc > mStartLoc){
			result[3] = informationString.substring(mStartLoc, mEndLoc + 1);
			
			//将SubFun中的修改信息去掉
			informationString = informationString.substring(0, mStartLoc - 1);
		}

		//按照空格对信息进行分拆
		String[] subFunDetail = 
				informationString.split(ScriptDeclaration.generalSplit);
		
		if (subFunDetail.length != 3){
			//分拆出来的信息不足三个，
			//说明SubFun不符合声明规范，
			//在不初始化result[0 ~ 2]的情况下，
			//直接返回结果。
			return result;
		} else {
			//将分拆出来的三个字段复制到result中。
			for (int pointer = 0; pointer < 3; ++pointer){
				result[pointer] = subFunDetail[pointer];
			}
		}
		return result;
	}

	/**
	 * 检查一个变量声明的变量类型是否是泛参，
	 * 这里的泛参指的是变量类型直接使用了RClass或者Function定义的泛参，
	 * 比如一个RClass定义为MyClass<T,K>，
	 * 在这里面声明了一个成员变量T member;，
	 * 这个T就是直接使用了泛参，
	 * 在这个方法中对一行变量是否将直接将泛参作为变量类型进行判断，
	 * 注意：这个方法进行判断的依据是RClass的命名规范和泛参的命名规范是有区别的，
	 * 通过这种区别进行简单的判断，
	 * 不会考虑这个泛参是否真的在RClass中声明了？这种问题。
	 * @param informationString
	 * 		一个变量声明的一行。
	 * @return
	 * 		如果变量声明的类型是泛参，返回true；
	 * 		如果变量的类型不是泛参，返回false。
	 */
	public static boolean isVarType_a_GenericParam(String varDeclaration) {
		boolean checkResult = false;
		//分割信息，查看是否按照空格分成两个或者三个部分
		String[] vars = varDeclaration.split(ScriptDeclaration.generalSplit);
		if (vars.length == 2 || vars.length == 3){
			checkResult = 
					checkComponentName(vars[0])			//类型部分满足泛参命名规范（即组件命名规范）
					&& checkComponentName(vars[1]);		//变量名部分满足组件命名规范
		}
		
		return checkResult;
	}

	/**
	 * 检查一个泛参指定是否是另一个泛参，
	 * 即K: T，这样的T，K都是一个泛参，
	 * 此方法用于GenericParameterAnalysisSpider中对上述情况防止
	 * 出现T<int, int>这样子对泛参的泛参指定类型的情况。
	 * @param originalString
	 * 		一行用于泛参指定的语句，
	 * 		形如“K: String”。
	 * @return
	 * 		如果冒号后面的是一个泛型，就返回true；
	 * 		如果冒号后面的是以及RClass的名字，就返回false。
	 */
	public static boolean isGenParam_assigned_to_GenParam(String genParamAssign) {
		//将声明信息按照冒号和空格进行分割
		String[] gpAssigns = 
				genParamAssign.split(
						ScriptDeclaration.colon + ScriptDeclaration.generalSplit);
		
		//要求被分割出来的部分一定要是两个。
		if (gpAssigns.length != 2){
			return false;
		}
		
		//前一个表示被指配的泛参名，
		//后一个表示用于指配的类型（可以是泛参）。
		return checkComponentName(gpAssigns[0]) 			//前一个部分是泛参名，
				&&	checkComponentName(gpAssigns[1]);		//后面一个部分同样是泛参名。
	}

	/**
	 * 检查是否正确声明了一行泛参指定，
	 * 我们使用一个泛型的时候，
	 * 需要对其中的泛参指定一个具体的类型
	 * 比如MyClass<T,K>，当我们使用的时候，
	 * 可能会使用MyClass<int, String>这样具体的类型，
	 * 或MyClass<M,M>，这样子不具体的类型，
	 * 其中T和K的具体指派就是我们要检查的内容，
	 * 形如“T: int”或者“T: M”，
	 * 冒号后面既可以是一个具体的类型，
	 * 也可以是另一个泛参的引用。
	 * @param originalString
	 * 		泛参指定语句，
	 * 		形如“T: int”或者“T: M”。
	 * @return
	 * 		如果符合泛参指定语法，返回true；
	 * 		否则返回false；
	 */
	public static boolean checkGenParamAssign(String genParamAssign) {
		//将声明信息按照冒号和空格进行分割
		String[] gpAssigns = 
				genParamAssign.split(
						ScriptDeclaration.colon + ScriptDeclaration.generalSplit);
		
		//要求被分割出来的部分一定要是两个。
		if (gpAssigns.length != 2){
			return false;
		}
		
		//前一个表示被指配的泛参名，
		//后一个表示用于指配的类型（可以是泛参）。
		return checkComponentName(gpAssigns[0]) 			//前一个部分是泛参名，
				&& (checkRClassName(gpAssigns[1]) 			//后 一个部分是类名，
					|| checkComponentName(gpAssigns[1]));	//或者一个泛参名。
	}

	/**
	 * 检查一个泛参的声明是否合法，
	 * 主要是检查这个泛参的名字是否符合泛参的命名规范，
	 * 一行泛参的声明包括一个泛参名加一个箭头标志（参考ScriptDeclaration.arrow）。
	 * @param originalString
	 * 		一行泛参的声明字符串，
	 * 		形如“T -> ”。。
	 * @return
	 * 		如果参数符合泛参声明
	 * 		（包含一个箭头符号，
	 * 		包括箭头符号前后的空格，
	 * 		命名符合组件命名规范），
	 * 		则返回true；
	 * 		否则返回false。
	 */
	public static boolean checkGenParamDeclar(String genParamDeclar) {
		int arrowHead = 
				genParamDeclar.indexOf(ScriptDeclaration.arrow);
		if (arrowHead == -1){
			//不包含箭头符号，
			//返回失败
			return false;
		}
		
		//检查去除箭头符号的其他部分是否符合组件命名规范。
		return checkComponentName(genParamDeclar.substring(0, arrowHead));
	}

	/**
	 * 检查一个字符串是否能够被解释为一个弧线的端点，
	 * 形如：<br>
	 * “1.funEnd”<br>
	 * 要求满足：正整数（不包括0） + 分割字符 + 组件名称。<br>
	 * 提醒：
	 * 分割字符是指ScriptDeclaration.indexSplit定义的一个字符串，
	 * 通常为一个半角英文点号，进行分割的时候注意点号会与正则表达式冲突，
	 * 需要转义字符。
	 * @param targetInfoString
	 * 		被检查的字符串，
	 * 		形如“1.funEnd”。
	 * @return
	 * 		如果字符串满足弧线端点要求，返回true；
	 * 		否则返回false。
	 */
	public static boolean checkArcPoint(String targetInfoString) {
		//利用ScriptDeclaration中定义的序号分隔符 分割字符串。
		String[] pointDefs =		
				//由于这里使用的是正则表达式，
				//而名称分割符是特殊含义的字符串“.”，
				//要进行转义，
				//注意转义只需要在前面加上一个"\"字符，
				//然而在Java中“\”又是一个特殊字符，
				//还需要转义，
				//所以这里加上字符串串“\\”。
				targetInfoString.split("\\" + ScriptDeclaration.indexSplit);
		
		if (pointDefs.length != 2){
			//按照分割符分割后的部分不是准确的两个，
			//说明这个字符串一定不满足弧线端点的声明要求。
			return false;
		}
		
		return 
			(	//检查序号部分是否是一个正整数。
				RStringChecker.checkSignInt(pointDefs[0]) == 2
				&&	//同时满足。
				//检查组件名部分是否符合组件命名规范。
				RStringChecker.checkComponentName(pointDefs[1])
			);
	}

	/**
	 * 检查一个字符串对应整数的属性。
	 * @param string
	 * 		被检查的字符串，
	 * 		期望是一个能够转换为整数的字符串。
	 * @return
	 * 		0代表字符串不能转换为整数；<br>
	 * 		-1代表负数；<br>
	 * 		1代表0；<br>
	 * 		2代表大于等于1的正整数；<br>
	 * 		
	 */
	private static int checkSignInt(String string) {
		int parseResult;
		try {
			parseResult = Integer.parseInt(string);
		} catch (NumberFormatException e) {
			return 0;
		}
		
		if (parseResult < 0){
			//标志这个数为负数。
			return -1;
		} else if (parseResult == 0) {
			//标志这个数为0。
			return 0;
		} else {
			//标志这个数为大于等于1的正整数。
			return 2;
		}
	}
}














