package basicInterface;

public interface INameable {
	//重载Object的String toString()函数
	//这个重载运用在RReference当中的
	//IRReference Member(String)
	//int locateRReferenceOf(String)
	//目的是减少为了得到名字而需要进行的强制类型转换
	public String toString();
	//获得名字
	public String getName();
	//设置名字
	public void setName(String newName);
}
