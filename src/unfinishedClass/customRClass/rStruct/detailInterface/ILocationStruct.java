package unfinishedClass.customRClass.rStruct.detailInterface;

/**
 * 二维坐标结构接口。
 */
public interface ILocationStruct {
	/**
	 * 定义二维坐标。
	 * @param locationString
	 * 		格式类似 "(" + X坐标 + "," + Y坐标 + ")"，<br>
	 * 		例如："(12.2,45.2)"。
	 */
	public void defineLocation(String locationString);
	
	/**
	 * 定义X坐标。
	 * @param X
	 * 		X坐标。
	 */
	public void defineX(double X);

	/**
	 * 定义Y坐标。
	 * @param Y
	 * 		Y坐标。
	 */
	public void defineY(double Y);
	
	/**
	 * @return
	 * 		X轴坐标。
	 */
	public double getX();
	
	/**
	 * @return
	 * 		Y轴坐标。
	 */
	public double getY();
}
