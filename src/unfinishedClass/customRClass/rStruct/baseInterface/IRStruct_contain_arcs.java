package unfinishedClass.customRClass.rStruct.baseInterface;

import unfinishedClass.customRClass.rStruct.ArcFieldStruct;
import unfinishedClass.customRClass.rStruct.ArcStruct;
import unfinishedClass.customRClass.rStruct.RSet;

/**
 * 包含弧线区域定义的RStruct接口。
 */
public interface IRStruct_contain_arcs {
	
	/**
	 * 用一个弧线定义区域来定义所有弧线。
	 * @param afStruct
	 * 		包含执行弧线你和参数弧线的定义区域。
	 */
	public void defineArcs_by_RStruct(ArcFieldStruct afStruct);
	
	/**
	 * 定义执行弧线。
	 * @param arcSet
	 * 		包含执行弧线定义的集合。
	 */
	public void defineEArcs_by_RSet(RSet<ArcStruct> arcSet);
	
	/**
	 * 定义参数弧线。
	 * @param arcSet
	 * 		包含参数弧线定义的集合。
	 */
	public void definePArcs_by_RSet(RSet<ArcStruct> arcSet);

	/**
	 * 定义执行弧线。
	 * @param arcStruct
	 * 		执行弧线定义。
	 */
	public void defineEArc(ArcStruct arcStruct);
	
	/**
	 * 定义参数弧线。
	 * @param arcStruct
	 * 		参数弧线定义。
	 */
	public void definePArc(ArcStruct arcStruct);
	
	public RSet<ArcStruct> getEArcSet();
	
	public RSet<ArcStruct> getPArcSet();

}
