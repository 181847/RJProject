package unfinishedClass.customRClass.rStruct;

import unfinishedClass.Exception.RStructException.LocationFormatException;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct;
import unfinishedClass.customRClass.rStruct.detailInterface.ILocationStruct;
import unfinishedClass.customRClass.scriptBlock.ScriptDeclaration;

/**
 * 存储了一个二维坐标信息的结构。
 */
public class LocationStruct implements IRStruct, ILocationStruct {
	/**
	 * X坐标。
	 */
	protected double X;
	
	/**
	 * Y坐标。
	 */
	protected double Y;
	
	public LocationStruct() {
		X = Y = 0;
	}

	public LocationStruct(String locationString) {
		defineLocation(locationString);
	}

	/**
	 * @throws LocationFormatException
	 * 		坐标格式不符合要求。
	 */
	@Override
	public void defineLocation(String locationString) {
		//防止null和空串。
		if (locationString == null || locationString.isEmpty()) {
			throw new IllegalArgumentException("不能用null或者空串定义二维坐标。");
		}
		
		//保证至少有两个括号和一个逗号。
		if (locationString.length() <= 3) {
			throw new LocationFormatException("坐标字符串长度过短，不符合规范。");
		}
		
		//剔除前后的括号。
		locationString = locationString.substring(
				ScriptDeclaration.location_start.length(), 
				locationString.length() - ScriptDeclaration.location_end.length());
		
		//按数字分割符分割两个数字部分。
		String[] locationDefines = locationString.split(ScriptDeclaration.numberSplit);
		
		if (locationDefines.length != 2) {
			throw new LocationFormatException("坐标字符串不符合规范。");
		}
		
		//转换字符串到数字。
		double newX, newY;
		try {
			newX = Double.parseDouble(locationDefines[0]);
			newY = Double.parseDouble(locationDefines[1]);
		} catch (NumberFormatException e) {
			throw new LocationFormatException("坐标字符串中的文字无法正常转化为数字。");
		}
		
		//定义XY坐标。
		defineX(newX);
		defineY(newY);
	}

	@Override
	public void defineX(double X) {
		this.X = X;
	}

	@Override
	public void defineY(double Y) {
		this.Y = Y;
	}

	@Override
	public double getX() {
		return X;
	}

	@Override
	public double getY() {
		return Y;
	}

}
