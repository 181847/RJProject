package unfinishedClass.customRClass.rStruct;

import unfinishedClass.Exception.RStructException.LocationFormatException;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct;
import unfinishedClass.customRClass.rStruct.detailInterface.ICommentStruct;
import unfinishedClass.customRClass.scriptBlock.ScriptDeclaration;

/**
 * 存储了一个注释的结构，
 * 包括一个方形的区域（两个二维坐标），
 * 一个连续的字符串（可换行）。
 */
public class CommentStruct implements IRStruct, ICommentStruct {
	/**
	 * 左上角坐标。
	 */
	protected LocationStruct lf_up_location;
	
	/**
	 * 右下角坐标。
	 */
	protected LocationStruct rt_lo_location;
	
	/**
	 * 注释的详细文字信息。
	 */
	protected TextStruct commentContent;
	

	/**
	 * 定义注释的方形区域信息，
	 * 定义了两个二维坐标。
	 * @param rect
	 * 		形如“(55.1, 22.1) -> (21.0, 50.1)”。
	 * @throws LocationformatException
	 * 		如果分析坐标信息时 发生错误，将会抛出这个异常。
	 */
	public void defineRect(String rect) {
		if (rect == null || rect.isEmpty()) {
			throw new IllegalArgumentException("不能用null或者空串定义注释方形区域。");
		}
		
		//分割为两个二维坐标。
		String[] locations = rect.split(ScriptDeclaration.arrow);
		
		if (locations.length != 2){
			throw new LocationFormatException("方形区域的坐标信息无法正常分割为两个坐标。");
		}
		
		//定义左上角。
		lf_up_location.defineLocation(locations[0]);
		//定义右下角。
		rt_lo_location.defineLocation(locations[1]);
	}

	/**
	 * 设置注释的文本信息。
	 * @param textStruct
	 * 		包含纯文本信息的结构体。
	 */
	public void defineCommentText_by_RStruct(TextStruct textStruct) {
		commentContent = textStruct;
	}

	@Override
	public LocationStruct getLeftUpperPoint() {
		return lf_up_location;
	}

	@Override
	public LocationStruct getRightLowerPoint() {
		return rt_lo_location;
	}

}
