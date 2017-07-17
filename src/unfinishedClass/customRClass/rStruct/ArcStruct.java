package unfinishedClass.customRClass.rStruct;

import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct;
import unfinishedClass.customRClass.rStruct.detailInterface.IArcStruct;

public class ArcStruct implements IRStruct, IArcStruct {
	/**
	 * 弧线头部定义。
	 */
	protected ArcPointStruct startPoint;
	
	/**
	 * 弧线尾部定义。
	 */
	protected ArcPointStruct endPoint;

	/**
	 * 定义弧线的起始点。
	 * @param arcPointStruct
	 * 		弧线的起始点结构。
	 */
	@Override
	public void defineArcStart(ArcPointStruct startPointStruct) {
		if (startPointStruct == null) {
			throw new IllegalArgumentException("不能用null来定义弧线头部。");
		}
		
		startPoint = startPointStruct;
	}

	/**
	 * 定义弧线的终点。
	 * @param arcPointStruct
	 * 		弧线的终点结构。
	 */
	@Override
	public void defineArcEnd(ArcPointStruct endPointStruct) {
		if (endPointStruct == null) {
			throw new IllegalArgumentException("不能用null来定义弧线尾部。");
		}
		
		endPoint = endPointStruct;
	}

}
