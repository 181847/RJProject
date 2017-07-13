package unfinishedClass.customRClass.rStruct;

import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * 包含一个RClass定义的结构。
 */
public class RClassStruct
implements IRStruct, 	
			RStruct_contain_genParams{
	
	/**
	 * 定义泛型参数。
	 * @param fd
	 * 		泛参定义集合。
	 */
	@Override
	public int defineGenParam_by_RSet(RSet<GenParamStruct> fd) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 定义RClass的类型，
	 * 接口、普通、抽象。
	 * @param infoType
	 * 		INTERFACE、ABSTRACT_RCLASS、RCLASS。
	 */
	public void defineType(InformationType infoType) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 定义RClass的类名。
	 * @param name
	 * 		RClass的类名。
	 */
	public void defineName(String name) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 定义父类。
	 * @param rStruct
	 * 		类型引用结构。
	 */
	public void defineExtends(RClassRefStruct rStruct) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 定义接口。
	 * @param interfaceSet
	 * 		类型引用集合。
	 */
	public void defineImplements_by_RSet(RSet<RClassRefStruct> interfaceSet) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 定义变量成员。
	 * @param varField
	 * 		静态和非静态变量定义区域。
	 */
	public void defineMembers_by_RStruct(VarFieldStruct varField) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 定义构造Function。
	 * @param funStruct
	 * 		构造Function结构。
	 */
	public void defineConFun(FunStruct funStruct) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 定义静态Function。
	 * @param funStruct
	 * 		静态Function结构。
	 */
	public void defineStaticFun(FunStruct funStruct) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 定义普通Function。
	 * @param funStruct
	 * 		普通Function结构。
	 */
	public void defineFun(FunStruct funStruct) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 定义抽象Function。
	 * @param funStruct
	 * 		抽象Function结构。
	 */
	public void defineAbstractFun(FunStruct funStruct) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 记录当前RClass所来自的路径，
	 * 包括工程名和脚本路径。
	 * @param path
	 * 		工程名和脚本路径。
	 */
	public void logSourcePath(String path) {
		// TODO Auto-generated method stub
		
	}

}
