package unfinishedClass.customRClass.scriptBlock.information;

public enum InformationType {
	//VOID表示非法信息。
	VOID, 
	//以下代表声明标志，并不是实际的脚本内容。
	TYPE, NAME, EXTENDS, IMPLEMENTS,
	MEMBER, STATIC, CONFUN, STATICFUN, 
	FUN, ABSTRACTFUN,
	//CONTENT表示实际的脚本内容。
	CONTENT,;
}
