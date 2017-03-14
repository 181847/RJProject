package unfinishedClass;

public class RReferenceHelper {
	/**
	 * 引用类型信息，
	 * @param refInfos
	 * 		第一维任意长度，表示有多少个引用类型，
	 * 		第二维必须三个长度，
	 * 		分别存类型、名字、初始化数据。
	 * @return
	 * 		如果参数为null，返回null；
	 * 		如果参数的第一维长度为0，
	 * 		返回长度为0的RReferenceInfo数组；
	 * 		如果参数长度不为0，
	 * 		任何不满足以下条件的参数都将会返回null。
	 * 		{
	 * 			第二维长度必须为3，
	 * 			第二维中前两个单元必须包含有内容的字符串，
	 * 			第二维中第三个单元为有内容的字符串或者空串。
	 * 		}
	 */
	public static RReferenceInfo[] makeRRefArray(String[][] refInfos){
		//TODO
		return null;
	}
}
