package unfinishedClass.customRClass.struct;

import unfinishedClass.customRClass.scriptBlock.ScriptDeclaration;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.struct.enumeration.ArcType;

public class ArcStruct extends Struct {
	protected ArcType arcType;
	
	/**
	 * 弧线的方向不以出度入度来区分，
	 * 而是用左端和右端来区分，
	 * 左端连接EARC的执行出口，PARC的返回值；
	 * 右端连接EARC的执行入口，PARC的参数。
	 */
	int lf_index, rt_index;
	String lf_name, rt_name;
	
	/**
	 * 初始化一个Arc类型，
	 * Arc包括执行线路上的弧线，
	 * 或者参数弧线。
	 * @param informationType
	 * 		标记弧线的类型，这一项应该为InformationType.
	 * @param arcInfo
	 */
	public ArcStruct(String arcInfo){
		//用于分割弧线类型声明的位置信息。
		int subLength;
		if (arcInfo.startsWith(ScriptDeclaration.EArc)){
			arcType = ArcType.EARC;
			subLength = ScriptDeclaration.EArc.length();
		} else {
			arcType = ArcType.PARC;
			subLength = ScriptDeclaration.PArc.length(); 
		}
		
		//将弧线类型声明剔除，然后提取具体的弧线信息。
		pickDetail(arcInfo.substring(subLength));
		
	}

	/**
	 * 提取弧线信息，例如：<br>
	 * “1.funEnd -> 4.construct”<br>
	 * 本方法主要是从中提取出两个数字和两个组件的名字，
	 * 分成：“1”、“4”、“funEnd”、“construct”，
	 * 并分别记录在成员变量，lf_index、rt_index、lf_name、rt_name。
	 * @param substring
	 * 		弧线信息，例如：<br>
	 * “1.funEnd -> 4.construct”<br>
	 * 		弧线信息不区分弧线类型。
	 */
	private void pickDetail(String substring) {
		// TODO Auto-generated method stub
		
	}
}
