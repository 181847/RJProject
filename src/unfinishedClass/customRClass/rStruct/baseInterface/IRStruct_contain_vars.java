package unfinishedClass.customRClass.rStruct.baseInterface;

import unfinishedClass.customRClass.rStruct.RSet;
import unfinishedClass.customRClass.rStruct.VarFieldStruct;
import unfinishedClass.customRClass.rStruct.VarStruct;

/**
 * 包含变量定义区域的RStruct的父类，
 * RClassStruct和FunStruct都可以包含变量定义区域。
 */
public interface IRStruct_contain_vars {
	/**
	 * 用一个变量定义区域来对内部的所有变量进行定义。
	 * @param vfStruct
	 * 		包含静态和非静态变量的定义区域。
	 */
	public void defineVars_by_RStruct(VarFieldStruct vfStruct);
	
	/**
	 * 定义静态变量，集合中所有变量都会记录为静态变量。
	 * @param vSet
	 * 		包含一系列变量定义的集合。
	 * @throws NameConflictException
	 * 		如果发生变量名冲突就会抛出此异常。 
	 */
	public void defineStaticVars_by_RSet(RSet<VarStruct> vSet);

	/**
	 * 定义非静态变量，集合中所有变量都会记录为非静态变量。
	 * @param vSet
	 * 		包含一系列变量定义的集合。
	 * @throws NameConflictException
	 * 		如果发生变量名冲突就会抛出此异常。 
	 */
	public void defineVars_by_RSet(RSet<VarStruct> vSet);
	
	/**
	 * 定义静态变量。
	 * @param vStruct
	 * 		变量结构。
	 * @throws NameConflictException
	 * 		如果发生变量名冲突就会抛出此异常。 
	 */
	public void defineStaticVar(VarStruct vStruct);
	
	/**
	 * 定义非静态变量。
	 * @param vStruct
	 * 		变量结构。
	 * @throws NameConflictException
	 * 		如果发生变量名冲突就会抛出此异常。 
	 */
	public void defineVar(VarStruct vStruct);
	
	/**
	 * @return
	 * 		所有静态变量的集合，
	 * 		即使没有定义静态变量，返回值也不是null，
	 * 		而是一个长度为0的ArrayList。
	 */
	public RSet<VarStruct> getStaticVarSet();
	
	/**
	 * @return
	 * 		所有非静态变量的集合，
	 * 		即使没有定义非静态变量，返回值也不是null，
	 * 		而是一个长度为0的ArrayList。
	 */
	public RSet<VarStruct> getVarSet();
}
