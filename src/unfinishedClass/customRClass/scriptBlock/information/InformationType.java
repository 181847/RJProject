package unfinishedClass.customRClass.scriptBlock.information;

public enum InformationType {
	//VOID表示非法信息。
	VOID,
	
	//以下代表声明标志，并不是实际的脚本内容。
	TYPE, NAME, 
	GENERIC_PARAMS, //泛参声明
	EXTENDS, IMPLEMENTS,
	MEMBER, STATIC, CONFUN, STATICFUN, 
	FUN, ABSTRACTFUN,
	
	//以下是Function内部的信息类型
	PARAMETER, RETURNVAL, EXCUTEE, EXCUTER,
	LOCALVAR, SUBFUN, ARC, COMMENT,
	
	//三种RClass的类型。
	INTERFACE, ABSTRACT, CLASS,
	
	//一个引用RClass的信息块，
	//即可以表示为一个单独RClass（例如：String），
	//也可以表示为一个泛型引用
	//（例如MyClass<T> ，T, MyClass<String>，其中T是一个未知的泛参）。
	CLASSREF,
	
	GEN_PARAM,	//泛参
	VAR, 		//一个可以带有初始值变量信息
	CLASSNAME, 	//一个RClass的类名。
	CONTENT, 	//CONTENT表示实际的脚本内容。
	RECT;		//RECT表示一个方形的区域左上角与右下角的两个坐标。
}
