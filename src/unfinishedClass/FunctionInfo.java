package unfinishedClass;

import basicInterface.IModifier;
import basicTool.RLogger;
import functionInterface.IFunction;
import rClass.RClassLoaderManager;
import rClassInterface.IRClass;

public class FunctionInfo extends RClassComponentInfo{
	public String functionName;
	public int functionIndex;
	public IRClass rClass;
	public String modifierInfo;

	/**
	 * @param rClassName 
	 * 		这个Function所属的RClass的名字。
	 * @param functionName 
	 * 		这个Function的名字。
	 */
	public FunctionInfo(String rClassName, String functionName) {
		super(rClassName);
		switch( setFunctionName(functionName) ){
		case -1:
			RLogger.log("从rClass获取Function的序号失败，当前Function信息不正确，请检查："
					+ "当前FunctionInfo对象中的信息：\n"
					+ "rClassName: " + rClassName
					+ "rClassID: " + rClassID
					+ "functionName: " + functionName);
		}
	}
	
	/**
	 * 创建一个带有定制信息的Function。
	 * @param rClassName 这个Function所属的RClass的名字。
	 * @param functionName 这个Function的名字。
	 * @param modifierInfo 定制Function的信息。
	 */
	public FunctionInfo(String rClassName, String functionName, String modifierInfo){
		this(rClassName, functionName);
		switch( setModiferInfo(modifierInfo) ){
		case 0:
			RLogger.log("ModifierInfo（定制Function信息）设置失败，modifierInfo参数为null，请检查："
					+ "当前FunctionInfo对象中的信息：\n"
					+ "rClassName: " + rClassName
					+ "rClassID: " + rClassID
					+ "functionName: " + functionIndex);
		}
	}
	
	/**
	 * 设置modifierInfo（定制信息）。
	 * @param modifierInfo 定制Function所需要的特定规范的字符串。
	 * @return modifierInfo为null返回0；
	 * 否则返回1。
	 */
	public int setModiferInfo(String modifierInfo){
		if (modifierInfo == null){
			return 0;
		}
		this.modifierInfo = modifierInfo;
		return 1;
	}
	
	/**
	 * 设置Funciton的名字，
	 * 同时也会更新functionIndex。
	 * @param functionName
	 * @return 如果名字为null，返回-2。
	 * 如果成功在rClass中找到相应的Function；
	 * 返回这个Function的序号（大于等于0的正整数）；
	 * 如果没有找到Function，
	 * 返回-1。
	 */
	public int setFunctionName(String functionName){
		if (functionName == null){
			return -2;
		}
		this.functionName = functionName;
		rClass = RClassLoaderManager.
							getRClassLoader().
							getRClass(getRClassID());
		
		if (rClass != null){
			functionIndex = rClass.getFunctionIndexOf(functionName);
		} else {
			functionIndex = 0;
		}
		return functionIndex;
	}
	
	/**
	 * @return 当前这个信息体当中的Function的名字。
	 */
	public String getFunctionName(){
		return functionName;
	}
	
	/**
	 * @return function在RClass中的序号。
	 */
	public int getFunctionIndex(){
		return functionIndex;
	}

	/**
	 * 获取这个信息体所指定的Function的新实例。
	 * @return 一个新的Function实例。
	 */
	public IFunction getInstance() {
		if (rClass != null){
			IFunction newFunction = rClass.getFunction(functionIndex);
			
			if (newFunction == null){
				RLogger.log("从FunctionInfo中获取Function实例失败，请检查："
					+ "当前FunctionInfo对象中的信息：\n"
					+ "rClassName: " + rClassName
					+ "rClassID: " + rClassID
					+ "functionName: " + functionIndex
					+ "modifierInfo: " + modifierInfo);
			}
			if (newFunction instanceof IModifier 
				&& modifierInfo != null 
				&& !modifierInfo.isEmpty() ){
				((IModifier) newFunction).modify(modifierInfo);
			}
			return newFunction;
		}
		RLogger.log("从FunctionInfo中获取Function实例失败，rClass对象为null，请检查："
					+ "当前FunctionInfo对象中的信息：\n"
					+ "rClassName: " + rClassName
					+ "rClassID: " + rClassID
					+ "functionName: " + functionIndex
					+ "modifierInfo: " + modifierInfo);
		return null;
	}
}
