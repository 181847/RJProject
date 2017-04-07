package unfinishedClass.customRClass.scriptBlock;

/**
 * 存储脚本文件中的关键声明字段。
 */
public class ScriptDeclaration {
	public static char hierarchy = '\t';
	public static char nameHierarchy = '.';
	
	//顶层信息声明
	public static String type = "Type:";
	public static String name = "Name:";
	public static String extendsD = "Extends:";
	public static String implementsD = "Implements:";
	public static String member = "Member:";
	public static String staticD = "Static:";
	public static String conFun = "ConstructFunction:";
	public static String staticFun = "StaticFunction:";
	public static String function = "Function: ";
	public static String abstractFun = "AbstractFunction:";
	
	//Function内部可能的信息声明
	public static String parameter = "Parameter:";
	public static String excutee = "Excutee:";
	public static String returnval = "Returnval";
	public static String excuter = "Excuter:";
	public static String localVar = "LocalVar:";
	public static String subFunction = "SubFunction:";
	public static String arc = "Arc:";
	public static String comment = "Comment:";
	
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
	public static String numberSplit = ", ";
	
}
