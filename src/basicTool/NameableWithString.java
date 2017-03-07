package basicTool;
import basicInterface.INameable;

/**
 * 可命名对象，
 * 这个对象内部用一个字符串来存储一个名字，
 * 是一下这些类型的父类，
 * RClass，
 * FunctionMaker，
 * Function，
 * RReference，
 * Excutee，
 * Parameter，
 * Returnval，
 * Exucter。
 */
public class NameableWithString implements INameable
{
	String name;
	
	public NameableWithString(){
		name = "Unknown";
	}
	
	public NameableWithString(String name){
		this.name = name;
	}
	
	/**
	 * 重载Object的String toString()函数，
	 * 这个重载运用在RReference当中的，
	 * IRReference Member(String)、int locateRReferenceOf(String)，
	 * 目的是减少为了得到名字而需要进行的强制类型转换。
	 * @return 当前可命名对象内部存储的名字。
	 */
	@Override
	public String toString(){
		return name;
	}

	/**
	 * 获取名字专用的方法。
	 * @return 当前可命名对象内部存储的名字。
	 */
	@Override
	public String getName(){
		return name;
	}

	/**
	 * 设置名字。
	 * @param 新的名字。
	 */
	@Override
	public void setName(String newName){
		name = newName;
	}
}
