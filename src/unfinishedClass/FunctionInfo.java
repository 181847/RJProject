package unfinishedClass;

import functionInterface.IFunction;
import rClass.RClassLoaderManager;
import rClassInterface.IRClass;

public class FunctionInfo extends RClassComponentInfo{
	public String functionName;
	public int functionIndex;
	public IRClass rClass;

	/**
	 * @param rClassName 这个Function所属的RClass的名字。
	 * @param functionName 这个Function的名字。
	 */
	public FunctionInfo(String rClassName, String functionName) {
		super(rClassName);
		setFunctionName(functionName);
	}
	
	/**
	 * 设置Funciton的名字，
	 * 同时也会更新functionIndex。
	 * @param functionName
	 * @return
	 */
	public int setFunctionName(String functionName){
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
			return rClass.getFunction(functionIndex);
		}
		return null;
	}
}
