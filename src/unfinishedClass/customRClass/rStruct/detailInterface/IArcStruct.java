package unfinishedClass.customRClass.rStruct.detailInterface;

import unfinishedClass.customRClass.rStruct.ArcPointStruct;

/**
 * 弧线RStruct结构接口。
 */
public interface IArcStruct {
	/**
	 * 定义弧线的起始点。
	 * @param arcPointStruct
	 * 		弧线的起始点结构。
	 */
	public void defineArcStart(ArcPointStruct startPointStruct);

	/**
	 * 定义弧线的终点。
	 * @param arcPointStruct
	 * 		弧线的终点结构。
	 */
	public void defineArcEnd(ArcPointStruct endPointStruct);
	
	/**
	 * 获取弧线起始点结构。
	 * @return
	 * 		弧线起始点结构。
	 */
	public ArcPointStruct getArcStart();
	
	/**
	 * 获取弧线终点结构。
	 * @return
	 * 		弧线终点结构。
	 */
	public ArcPointStruct getArcEnd();
}
