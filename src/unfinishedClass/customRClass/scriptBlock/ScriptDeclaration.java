package unfinishedClass.customRClass.scriptBlock;

/**
 * 存储脚本文件中的关键声明字段。
 */
public class ScriptDeclaration {
	public static String hierarchy = "    ";	//四个空格
	public static String nameHierarchy = ".";
	public static String fence = " ";
	
	//顶层信息声明
	public static String declar_type = "Type:";
	public static String declar_name = "Name:";
	public static String declar_GPs = "GenericsParams:";		//泛参声明
	public static String declar_extends = "Extends:";
	public static String declar_implements = "Implements:";
	public static String declar_members = "Members:";
	public static String declar_static = "Static:";
	public static String declar_fun_confun = "ConstructFunction:";
	public static String declar_fun_static = "StaticFunction:";
	public static String declar_fun = "Function:";
	public static String declar_fun_abstract = "AbstractFunction:";
	
	//Function内部可能的信息声明
	public static String declar_parameters = "Parameters:";
	public static String declar_excutees = "Excutees:";
	public static String declar_returnvals = "Returnvals:";
	public static String declar_excuters = "Excuters:";
		//两种Excuter的类型声明
		//普通Exucter，正常线路上的执行出口
		public static String declar_normal_excuter = "NormalE:";
		//异常Excuter，发生异常时的执行出口
		public static String declar_exception_excuter = "ExcepE:";
	
	/**
	 * 初始化信息声明。
	 */
	public static String declar_init = "Init:";
		
	public static String declar_local_vars = "LocalVars:";
	public static String declar_subFuns = "SubFunctions:";
		/**
		 * SubFun的泛参指定声明。
		 */
		public static String declar_GP_assign = "GPAssign:";
		
		/**
		 * SubFun的修改信息声明。
		 */
		public static String declar_modify = "Modify:";
		
	public static String declar_arcs = "Arcs:";
	public static String declar_comments = "Comments:";
	
	//三种RClass类型的声明
	public static String interfaceType = "Interface";
	public static String abstractRClassType = "AbstractRClass";
	public static String rClassType = "RClass";

	//子Fun的位置声明的开始和结束的标志符
	public static String location_start = "(";
	public static String location_end = ")";

	//子Fun的Modify声明的开始和结束的标志符
	public static String modify_start = "{";
	public static String modify_end = "}";
	
	//子Fun中的弧线声明字段
	public static String declar_arcs_e_to_e = "EtoE:";
	public static String declar_arcs_r_to_p = "RtoP:";

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
