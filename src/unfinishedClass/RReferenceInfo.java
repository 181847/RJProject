package unfinishedClass;

import rClass.RReference;
import rClassInterface.IRReference;

public class RReferenceInfo extends RClassComponentInfo{
	public String referenceName;
	public String initialData;

	public RReferenceInfo(String rClassName, String referenceName) {
		super(rClassName);
		this.referenceName = referenceName;
	}
	
	public RReferenceInfo(String rClassName, String referenceName, String initialData){
		super(rClassName);
		this.referenceName = referenceName;
		this.initialData = initialData;
	}
	
	/**
	 * @return 要创建的RReference的名字。
	 */
	public String getReferenceName() {
		return referenceName;
	}

	/**
	 * @return 用于基本数据类型的初始化数据。
	 */
	public String getInitialData() {
		return initialData;
	}

	/**
	 * @param 要创建的RReference的名字。
	 */
	public void setReferenceName(String referenceName){
		this.referenceName = referenceName;
	}
	
	/**
	 * @param 用于基本数据类型的初始化数据。
	 */
	public void setInitialData(String initialData){
		this.initialData = initialData;
	}

	/**
	 * @return 指定信息地 RReference实例。
	 */
	public IRReference getInstance() {
		if (rClassID == 0){
			RLogger.log("当前信息体中的rClassID为0，无法创建实例，请检查RClass的类型是否正确。"
					+ "当前Info对象中的信息：\n"
					+ "rClassName: " + rClassName
					+ "rClassID: " + rClassID
					+ "referenceName: " + referenceName
					+ "initialData: " + initialData);
		}
		
		if (rClassID >= 1 && rClassID <= 9){
			if (initialData != null && !initialData.isEmpty()){
				try{
					return new RReference(rClassID, 
							rClassName, 
							referenceName, 
							getInitialDatas(), 
							1, 
							rClassID);
					
				} catch (NumberFormatException e){
					RLogger.log("RReferenceInfo尝试利用字符串初始化基本数据类型的数据，但是字符串格式不正确，发生异常，请检查initialData。"
								+ "当前Info对象中的信息：\n"
							+ "rClassName: " + rClassName
							+ "rClassID: " + rClassID
							+ "referenceName: " + referenceName
							+ "initialData: " + initialData);
					
					RLogger.logException(e);
				}
			}
			return new RReference(rClassID, 
					rClassName, 
					referenceName, 
					getDefaultDatas(), 
					1, 
					rClassID);
		} else {
			//非基本数据类型，只初始化RClassID，RClassName，和当前RReference的名字
			return new RReference(rClassID, rClassName, referenceName, null, 0, 0);
		}
	}

	
	/**
	 * @return 这个方法返回基本数据类型RReference中要用到的默认数值的datas。
	 */
	protected Object[] getDefaultDatas() {
		switch(rClassID){
		case 1:
			return new Object[]{new Byte("0")};
		case 2:
			return new Object[]{new Boolean(false)};
		case 3:
			return new Object[]{new Short((short)0)};
		case 4:
			return new Object[]{new Integer(0)};
		case 5:
			return new Object[]{new Long(0)};
		case 6:
			return new Object[]{new Float(0)};
		case 7:
			return new Object[]{new Double(0)};
		case 8:
			return new Object[]{new Character('\0')};
		case 9:
			return new Object[]{new String("")};
		}
		return null;
	}

	/**
	 * 已知RClassID表示的是基本数据类型的情况下，
	 * 尝试将initialData（字符串）转换为相应的Java包装类并且放置到一个新生成的长度为一的Object一维数组中返回。
	 * @return 包含基本数据类型的长度为一的Object一维数组。
	 */
	protected Object[] getInitialDatas() {
		switch(rClassID){
		case 1:
			return new Object[]{new Byte(initialData)};
		case 2:
			return new Object[]{new Boolean(initialData)};
		case 3:
			return new Object[]{new Short(initialData)};
		case 4:
			return new Object[]{new Integer(initialData)};
		case 5:
			return new Object[]{new Long(initialData)};
		case 6:
			return new Object[]{new Float(initialData)};
		case 7:
			return new Object[]{new Double(initialData)};
		case 8:
			return new Object[]{new Character(initialData.charAt(0))};
		case 9:
			return new Object[]{initialData};
		}
		return null;
	}

}
