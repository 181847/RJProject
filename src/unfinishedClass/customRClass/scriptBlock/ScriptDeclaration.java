package unfinishedClass.customRClass.scriptBlock;

/**
 * 存储脚本文件中的关键声明字段。
 */
public class ScriptDeclaration {
	public static char hierarchy = '\t';
	public static String nameHierarchy = ".";
	public static String fence = " ";
	
	//顶层信息声明
	public static String type = "Type:";
	public static String name = "Name:";
	public static String genericParams = "GenericsParams:";		//泛参声明
	public static String extendsDec = "Extends:";
	public static String implementsDec = "Implements:";
	public static String members = "Members:";
	public static String staticDec = "Static:";
	public static String conFun = "ConstructFunction:";
	public static String staticFun = "StaticFunction: ";
	public static String function = "Function: ";
	public static String abstractFun = "AbstractFunction: ";
	
	//Function内部可能的信息声明
	public static String parameters = "Parameters:";
	public static String excutees = "Excutees:";
	public static String returnvals = "Returnvals:";
	public static String excuters = "Excuters:";
		//两种Excuter的类型声明
		//普通Exucter，正常线路上的执行出口
		public static String normalExcuter = "NormalE:";
		//异常Excuter，发生异常时的执行出口
		public static String exceptionExcuter = "ExcepE:";
	public static String localVars = "LocalVars:";
	public static String subFunctions = "SubFunctions:";
	public static String arcs = "Arcs:";
	public static String comments = "Comments:";
	
	//三种RClass类型的声明
	public static String interfaceType = "Interface";
	public static String abstractType = "AbstractClass";
	public static String classType = "Class";

	//子Fun的位置声明的开始和结束的标志符
	public static String locationStart = "(";
	public static String locationEnd = ")";

	//子Fun的Modify声明的开始和结束的标志符
	public static String modifyStart = "{";
	public static String modifyEnd = "}";
	
	//子Fun中的弧线声明字段
	public static String EArc = "EtoE: ";
	public static String PArc = "RtoP: ";

	//特殊的Function组件名字，
	//这些组件名字不允许用户手动声明在他们的自定义Function中，
	//只能由程序在运行时自行创建，
	//或者在生成的脚本文件中，
	//在Function内部的Arc声明中涉及这些特殊的包含非法字符的名称。
	public static String[] specialExcuters = {"@EXCEPTION"};
	public static String[] specialParameters = {"@THIS"};
	public static String arrow = " -> ";
	
	//数字序号和声明部分的分割符
	public static String indexSplit = ".";
	//数字之间的分割符
	public static String numberSplit = ",";
	//普通的分割符，用于分割声明的各个部分，
	//比如说变量信息的类型和变量名之间用分割符分割开来
	public static String generalSplit = " ";
	
	//暂时的尖括号，还没有任何作用
	public static String angleBracketStart = "<";
	public static String angleBracketEnd = ">";
	public static String colon = ":";
	public static String at = "@";
}
