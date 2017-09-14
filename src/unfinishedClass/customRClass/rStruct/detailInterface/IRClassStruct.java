package unfinishedClass.customRClass.rStruct.detailInterface;

import unfinishedClass.customRClass.rStruct.FunStruct;
import unfinishedClass.customRClass.rStruct.RClassRefStruct;
import unfinishedClass.customRClass.rStruct.RSet;
import unfinishedClass.customRClass.rStruct.VarFieldStruct;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_genParams;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_name;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_type;

public interface IRClassStruct 
		extends 
			IRStruct_contain_type, 
			IRStruct_contain_name,
			IRStruct_contain_genParams{
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
	
	/**
	 * 获取非接口父类。
	 * @return
	 * 		非接口父类的类引用，
	 * 		如果没有非接口父类就返回null。
	 */
	public RClassRefStruct getExtends();
	
	/**
	 * 获取接口父类集合。
	 * @return
	 * 		接口父类集合，如果没有定义接口父类，
	 * 		返回的对象长度为0（RSet以ArrayList实现）。
	 */
	public RSet<RClassRefStruct> getImplementSet();
	
	/**
	 * 获取构造Function。
	 * @return
	 * 		构造Function，如果没有定义的话就返回null。
	 */
	public FunStruct getConstructFunction();
	
	/**
	 * 获取静态Function集合。
	 * @return
	 * 		静态Function集合，如果没有静态Function，
	 * 		返回对象长度为0（RSet以ArrayList实现）。
	 */
	public RSet<FunStruct> getStaticFunSet();
	
	/**
	 * 获取非静态Function集合。
	 * @return
	 * 		非静态Function集合，如果没有非静态Function，
	 * 		返回对象长度为0（RSet以ArrayList实现）。
	 */
	public RSet<FunStruct> getFunSet();
	
	/**
	 * 获取抽象Function集合。
	 * @return
	 * 		抽象Function集合，如果没有抽象Function，
	 * 		返回对象长度为0（RSet以ArrayList实现）。
	 */
	public RSet<FunStruct> getAbstractFunSet();
}
