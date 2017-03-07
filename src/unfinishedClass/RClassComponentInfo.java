package unfinishedClass;

import rClass.RClassLoaderManager;

/**
 * 所有RClass组件的Info类型的Info对象，
 * 一般有Function，MemberInfo。
 * @author 75309
 *
 */
public abstract class RClassComponentInfo{
	public int rClassID;
	public String rClassName;

	/**
	 * 设置RClass的名字，
	 * 与此同时设置rClassID。
	 * @param rClassName RClass的名字。
	 */
	public RClassComponentInfo(String rClassName){
		setRClassName(rClassName);
	}
	
	/**
	 * @return rClassID。
	 */
	public int getRClassID() {
		return rClassID;
	}

	/**
	 * @return RClass的名字。
	 */
	public String getRClassName(){
		return rClassName;
	}
	
	/**
	 * 设置信息中的RClass的名字，
	 * 与此同时也会更新RClassID。
	 * @param rClassName 新的RClass的名字。
	 * @return 信息更新之后的RClassID。
	 */
	public int setRClassName(String rClassName){
		this.rClassName = rClassName;
		rClassID = RClassLoaderManager.getRClassLoader().getRClassIDOf(rClassName);
		return rClassID;
	}
}
