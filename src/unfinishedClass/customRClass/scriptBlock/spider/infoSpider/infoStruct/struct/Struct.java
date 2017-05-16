package unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct.struct;

import basicTool.NameableWithString;

public class Struct extends NameableWithString {
	public Struct(){
		name = "UnknownStruct";
	}
	
	/**
	 * 初始化的时候讲名字也初始化。
	 * @param name
	 */
	public Struct(String name){
		this.name = name;
	}
}
