package unfinishedClass.customRClass.rStruct.baseInterface;

/**
 * 拥有名字的RStruct的通用接口。
 */
public interface IRStruct_contain_name {
	
	/**
	 * 设置名字。
	 * @param name
	 * 		被设置的名字。
	 */
	public void defineName(String name);
	
	/**
	 * @return
	 * 		结构中定义的名称，
	 * 		如果还没有定义名字就抛出CodeRefuseException，
	 * 		不允许在设置RStruct的名字之前获取名字。
	 */
	public String getName();
}
