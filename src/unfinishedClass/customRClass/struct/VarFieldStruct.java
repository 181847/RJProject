package unfinishedClass.customRClass.struct;

import unfinishedClass.customRClass.set.VarSet;

/**
 * 收集多个变量的信息，
 * 同时划分出一个单独的区域用来存储静态变量，
 * 这个类用来在RClassStruct中存储成员变量，
 * 以及在FunctionStruct中存储本地变量。
 */
public class VarFieldStruct{
	protected VarSet staticSet;
	protected VarSet set;

	/**
	 * 添加静态成员变量集合。
	 * @param varSet
	 * 		静态成员变量集合。
	 */
	public void setStaticSet(VarSet varSet) {
		staticSet = varSet;
	}
	
	/**
	 * 添加非静态成员。
	 */
	public void addVar(String varInfo){
		set.addVar(varInfo);
	}
	
	/**
	 * 添加静态成员。
	 */
	public void addStaticVar(String varInfo){
		staticSet.addVar(varInfo);
	}
	
	/**
	 * 返回静态成员集合。
	 */
	public VarSet getStaticVarSet(){
		return staticSet;
	}

	/**
	 * 返回非静态成员集合。
	 */
	public VarSet getVarSet() {
		return set;
	}
}
