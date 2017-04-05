package unfinishedClass.customRClass.scriptBlock;

/**
 * 存储脚本文件中的关键声明字段。
 */
public class ScriptDeclaration {
	public static char hierarchy = '\t';
	
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
}
