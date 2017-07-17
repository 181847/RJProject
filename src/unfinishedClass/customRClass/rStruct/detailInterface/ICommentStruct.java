package unfinishedClass.customRClass.rStruct.detailInterface;

import unfinishedClass.customRClass.rStruct.LocationStruct;
import unfinishedClass.customRClass.rStruct.TextStruct;

/**
 * 注释信息结构接口。
 */
public interface ICommentStruct {

	/**
	 * 定义注释的方形区域信息，
	 * 定义了两个二维坐标。
	 * @param rect
	 * 		形如“(55.1, 22.1) -> (21.0, 50.1)”。
	 */
	public void defineRect(String rect);

	/**
	 * 设置注释的文本信息。
	 * @param textStruct
	 * 		包含纯文本信息的结构体。
	 */
	public void defineCommentText_by_RStruct(TextStruct textStruct);
	
	/**
	 * 获取左上角的坐标。
	 * @return
	 * 		左上角的坐标。
	 */
	public LocationStruct getLeftUpperPoint();
	
	/**
	 * 获取右下角的坐标。
	 * @return
	 * 		右下角的坐标。
	 */
	public LocationStruct getRightLowerPoint();

}
