package unfinishedClass.customRClass.rStruct.detailInterface;

import unfinishedClass.customRClass.rStruct.CommentStruct;
import unfinishedClass.customRClass.rStruct.ExcuteeStruct;
import unfinishedClass.customRClass.rStruct.ExcuterFieldStruct;
import unfinishedClass.customRClass.rStruct.ExcuterStruct;
import unfinishedClass.customRClass.rStruct.RSet;
import unfinishedClass.customRClass.rStruct.SubFunStruct;
import unfinishedClass.customRClass.rStruct.VarStruct;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_arcs;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_genParams;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_name;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_vars;

public interface IFunStruct 
		extends 
		IRStruct_contain_name, 
		IRStruct_contain_genParams,
		IRStruct_contain_vars,
		IRStruct_contain_arcs{

	/**
	 * 定义执行入口。
	 * @param excuteeSet
	 * 		执行入口结构集合。
	 */
	public void defineExcutees_by_RSet(RSet<ExcuteeStruct> excuteeSet);

	/**
	 * 定义参数组件。
	 * @param parameterSet
	 * 		参数结构结合。
	 */
	public void defineParameters_by_RSet(RSet<VarStruct> parameterSet);

	/**
	 * 定义执行出口。
	 * @param excuterSet
	 * 		执行出口结构集合。
	 */
	public void defineExcuters_by_RStruct(ExcuterFieldStruct excuterSet);

	/**
	 * 定义返回值组件。
	 * @param returnvalSet
	 * 		返回值结构集合。
	 */
	public void defineReturnvals_by_RSet(RSet<VarStruct> returnvalSet);

	/**
	 * 定义subFun。
	 * @param subFunSet
	 * 		subFun结构集合。
	 */
	public void defineSubFuns_by_RSet(RSet<SubFunStruct> subFunSet);

	/**
	 * 定义注释。
	 * @param commentSet
	 * 		注释结构集合。
	 */
	public void defineComments_by_RSet(RSet<CommentStruct> commentSet);
	
	// ************************单一RStruct结构定**************************
	
	/**
	 * 单独定义执行入口。
	 * @param eStruct
	 * 		执行入口结构。
	 * @throws NameConflictException
	 * 		如果发生组件入口命名冲突就要抛出这个异常。
	 */
	public void defineExcutee(ExcuteeStruct eStruct);
	
	/**
	 * 单独定义参数组件。
	 * @param vStruct
	 * 		参数定义。
	 * @throws NameConflictException
	 * 		如果发生参数组件命名冲突就要抛出这个异常。
	 */
	public void defineParameter(VarStruct vStruct);
	
	/**
	 * 单独定义异常执行入口。
	 * @param eStruct
	 * 		异常执行入口结构。
	 * @throws NameConflictException
	 * 		如果发生异常执行入口命名冲突就要抛出这个异常。
	 */
	public void defineExceptionExcuter(ExcuterStruct eStruct);
	
	/**
	 * 单独定义普通执行入口。
	 * @param eStruct
	 * 		普通执行入口结构。
	 * @throws NameConflictException
	 * 		如果发生普通执行入口命名冲突就要抛出这个异常。
	 */
	public void defineNormalExcuter(ExcuterStruct eStruct);
	
	/**
	 * 单独定义返回值组件。
	 * @param eStruct
	 * 		返回值组件结构。
	 * @throws NameConflictException
	 * 		如果发生返回值组件命名冲突就要抛出这个异常。
	 */
	public void defineReturnval(VarStruct vStruct);
	
	/**
	 * 单独定义子Fun。
	 * @param sfStruct
	 * 		子Fun结构。
	 * @throws NameConflictException
	 * 		如果发生 子Fun 命名冲突就要抛出这个异常。
	 */
	public void defineSubFun(SubFunStruct sfStruct);

	/**
	 * 单独定义注释区域。
	 * @param cStruct
	 * 		注释区域结构。
	 */
	public void defineComment(CommentStruct cStruct);
	
	
}
