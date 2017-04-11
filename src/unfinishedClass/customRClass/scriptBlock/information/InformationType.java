package unfinishedClass.customRClass.scriptBlock.information;

public enum InformationType {
	//VOID表示非法信息。
	VOID,
	
	//以下代表声明标志，并不是实际的脚本内容。
	TYPE, NAME, EXTENDS, IMPLEMENTS,
	MEMBER, STATIC, CONFUN, STATICFUN, 
	FUN, ABSTRACTFUN,
	
	//以下是Function内部的信息类型
	PARAMETER, RETURNVAL, EXCUTEE, EXCUTER,
	LOCALVAR, SUBFUN, ARC, COMMENT,
	
	//三种RClass的类型。
	INTERFACE, ABSTRACT, CLASS,
	
	VAR, 		//一个可以带有初始值变量信息
	CLASSNAME, 	//一个RClass的类名。
	CONTENT, 	//CONTENT表示实际的脚本内容。
	RECT;		//RECT表示一个方形的区域左上角与右下角的两个坐标。
}
