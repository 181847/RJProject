package unfinishedClass.customRClass.rStruct.baseInterface;

import unfinishedClass.customRClass.rStruct.FunStruct;
import unfinishedClass.customRClass.rStruct.RClassRefStruct;
import unfinishedClass.customRClass.rStruct.RSet;
import unfinishedClass.customRClass.rStruct.VarFieldStruct;

public interface IRClassStruct 
extends IRStruct_contain_type, IRStruct_contain_name, IRStruct_contain_genParams{
	/**
	 * 定义父类。
	 * @param rStruct
	 * 		类型引用结构。
	 */
	public void defineExtends(RClassRefStruct rStruct);

	/**
	 * 定义接口。
	 * @param interfaceSet
	 * 		类型引用集合。
	 */
	public void defineImplements_by_RSet(RSet<RClassRefStruct> interfaceSet);
	
	/**
	 * 追加接口实现。
	 * @param classRefStruct
	 * 		接口类型引用。
	 * @exception NameConflictionException
	 * 		接口名冲突。
	 */
	public void defineImplement(RClassRefStruct classRefStruct);

	/**
	 * 定义变量成员。
	 * @param varField
	 * 		静态和非静态变量定义区域。
	 */
	public void defineMembers_by_RStruct(VarFieldStruct varField);

	/**
	 * 定义构造Function。
	 * @param funStruct
	 * 		构造Function结构。
	 */
	public void defineConFun(FunStruct funStruct);

	/**
	 * 定义静态Function。
	 * @param funStruct
	 * 		静态Function结构。
	 */
	public void defineStaticFun(FunStruct funStruct);

	/**
	 * 定义普通Function。
	 * @param funStruct
	 * 		普通Function结构。
	 */
	public void defineFun(FunStruct funStruct);

	/**
	 * 定义抽象Function。
	 * @param funStruct
	 * 		抽象Function结构。
	 */
	public void defineAbstractFun(FunStruct funStruct);

	/**
	 * 记录当前RClass所来自的路径，
	 * 包括工程名和脚本路径。
	 * @param path
	 * 		工程名和脚本路径。
	 */
	public void logSourcePath(String path);
}
