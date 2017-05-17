package unfinishedClass.customRClass.set;

import basicTool.NameableWithString;
import basicTool.NamedItemList;
import unfinishedClass.customRClass.struct.Struct;

/**
 * 包含各种RClass信息的集合。
 */
public class Set extends NamedItemList{

	/**
	 * 按照序号获取内部的一个struct对象。
	 * @param structNum
	 * 		想要获取的Struct的序号。
	 * @return
	 * 		对应序号的Struct对象。
	 */
	public Struct getStruct(int structNum) {
		NameableWithString returnVal
			= (Struct) getItem(structNum);
		
		if (returnVal == null){
			return null;
		} else {
			return (Struct) returnVal;
		}
	}
	
	/**
	 * 在尾部添加一个Struct结构，
	 * null不能被添加到内部，
	 * 当空间不够的时候回自动增加内部空间。
	 * @param newStruct
	 * 		被添加的结构。
	 */
	public void append(Struct newStruct){
		if (newStruct == null){
			//空指针不能添加。
			return;
		}
		
		if (itemNum >= buffer.length){
			//将存储空间扩大两倍。
			doubleExpendList();
		}
		
		//向列表的尾部添加newStruct。
		buffer[itemNum++] = newStruct;
	}

}
