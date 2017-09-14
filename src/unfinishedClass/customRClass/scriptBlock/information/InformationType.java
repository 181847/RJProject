package unfinishedClass.customRClass.scriptBlock.information;

public enum InformationType {
	//VOID表示非法信息。
	VOID,
	
	/**
	
	//以下代表声明标志，并不是实际的脚本内容。
	TYPE, NAME, 
	GENERIC_PARAMS, //泛参声明
	EXTENDS, IMPLEMENTS,
	MEMBER, STATIC, CONFUN, STATICFUN, 
	FUN, ABSTRACTFUN,
	
	//以下是Function内部的信息类型
	PARAMETER, RETURNVAL,
	LOCALVAR, COMMENT,
	
	
	//一个引用RClass的信息块，
	//即可以表示为一个单独RClass（例如：String），
	//也可以表示为一个泛型引用
	//（例如MyClass<T> ，T, MyClass<String>，其中T是一个未知的泛参）。
	CLASSREF,
	CLASSNAME, 	//一个RClass的类名。
	CONTENT, 	//CONTENT表示实际的脚本内容。
	*/
	
	/**********************RClass声明信息部分*******************************/
	
	
	/**
	 * RClass的类型声明。
	 */
	DECLAR_TYPE,

	/**
	 * 接口RClass声明。
	 */
	INTERFACE, 
	
	/**
	 * 抽象RClass声明。
	 */
	ABSTRACT_RCLASS, 
	
	/**
	 * 普通RClass声明。
	 */
	RCLASS,
	
	/**
	 * RClass的名称声明。
	 */
	DECLAR_NAME,
	
	/**
	 * RClass的泛参声明。
	 */
	DECLAR_GEN_PARAMS,
	
	/**
	 * 继承非接口父类声明。
	 */
	DECLAR_EXTENDS,
	
	/**
	 * 继承接口父类声明。
	 */
	DECLAR_IMPLEMENTS,
	
	/**
	 * 成员变量声明。
	 */
	DECLAR_MEMBERS,
	
	/**
	 * 构造Function声明。
	 */
	DECLAR_FUN_CONFUN,
	
	/**
	 * 静态Function声明。
	 */
	DECLAR_FUN_STATIC,
	
	/**
	 * 非静态Function声明	。
	 */
	DECLAR_FUN,
	
	/**
	 * 抽象Function声明。
	 */
	DECLAR_FUN_ABSTRACT,
	
	/**********************静态变量区域声明部分*******************************/
	
	/**
	 * 所有声明变量中，属于静态变量的部分。
	 */
	DECLAR_STATIC,
	
	
	/**********************Function声明信息部分*******************************/
	
	/**
	 * 执行入口声明。	
	 */
	DECLAR_EXCUTEES,
	
	/**
	 * Function对外参数声明。
	 */
	DECLAR_PARAMETERS,
	
	/**
	 * 执行出口声明。
	 */
	DECLAR_EXCUTERS,
	
	/**
	 * Function对外返回值声明。
	 */
	DECLAR_RETURNVALS,
	
	/**
	 * Function使用的本地变量声明。
	 */
	DECLAR_LOCALVARS,
	
	/**
	 * Function调用的子Fun声明。
	 */
	DECLAR_SUBFUNS,
	
	/**
	 * 弧线声明。
	 */
	DECLAR_ARCS,
	
	/**
	 * 注释声明。
	 */
	DECLAR_COMMENTS,
	
	/**********************执行出口声明部分*******************************/
	
	/**
	 * 异常执行出口声明。
	 */
	DECLAR_EXCUTERS_EXCEPTION,
	
	/**
	 * 普通执行出口声明。
	 */
	DECLAR_EXCUTERS_NORMAL,
	
	/**********************弧线类型声明部分*******************************/
	
	/**
	 * 执行弧线声明。
	 */
	DECLAR_ARCS_E_TO_E,
	
	/**
	 * 参数弧线声明。
	 */
	DECLAR_ARCS_R_TO_P,

	
	/**********************可变声明字段****************************************************/
	
	/**********************类型相关字段*******************************/
	
	/**
	 * 指向具体RClass的类型引用。
	 */
	CLASS_REF_CL,
	
	/**
	 * 指向泛参所示类型的类型引用。
	 */
	CLASS_REF_GP,
	
	/**
	 * 泛参指定为具体类型。
	 */
	GP_ASSIGN_CL,
	
	/**
	 * 泛参指定为另一个泛参。
	 */
	GP_ASSIGN_GP,
	
	/**
	 * 单个泛参声明
	 */
	GEN_PARAM,
	
	
	/**********************Function部分字段*******************************/
	
	/**
	 * 单一执行入口。
	 */
	EXCUTEE,
	
	/**
	 * 单一执行出口。
	 */
	EXCUTER,
	
	/**
	 * 单个变量声明，同时可以用于Function的参数和返回值。
	 */
	VAR,
	
	/**
	 * 变量初始化信息开始声明标志。
	 */
	DECLAR_INIT,
	
	/**
	 * 用于初始化变量的信息。
	 */
	INFO_INIT,
	
	/**********************SubFun字段*******************************/
	
	/**
	 * 单个子Function声明
	 */
	SUBFUN,
	
	/**
	 * 坐标信息
	 */
	LOCATION,
	
	/**
	 * 开始声明泛参指定信息。
	 */
	DECLAR_GP_ASSIGN,
	
	/**
	 * 开始声明修改信息。
	 */
	DECLAR_MODIFY,
	
	/**
	 * 真实的修改信息。
	 */
	INFO_MODIFY,

	
	/**********************弧线字段*******************************/
	
	/**
	 * 单一弧线声明。
	 */
	ARC,
	
	/**
	 * 单一弧线出口端。
	 */
	ARC_START,
	
	/**
	 * 单一弧线入口端。
	 */
	ARC_END,

	
	/**********************注释字段*******************************/
	
	/**
	 * 单一注释的方形区域。
	 */
	RECT,
	
	/**
	 * 注释的实际内容。
	 */
	INFO_COMMENT,
	
}
