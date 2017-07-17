package unfinishedClass.customRClass.rStruct;

import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct;
import unfinishedClass.customRClass.rStruct.baseRStruct.RStruct_contain_name_genParams_vars_arcs_excuters;
import unfinishedClass.customRClass.rStruct.detailInterface.IFunStruct;

/**
 * 存储了一个Function定义的结构体。
 */
public class FunStruct
extends RStruct_contain_name_genParams_vars_arcs_excuters
implements IRStruct, IFunStruct{
	
	/**
	 * 执行入口集合。
	 */
	protected RSet<ExcuteeStruct> excuteeSet;
	
	/**
	 * 参数组件集合。
	 */
	protected RSet<VarStruct> paramSet;
	
	/**
	 * 返回值组件集合。
	 */
	protected RSet<VarStruct> returnSet;
	
	/**
	 * 子Function集合。
	 */
	protected RSet<SubFunStruct> subFunSet;
	
	/**
	 * 注释集合。
	 */
	protected RSet<CommentStruct> commentSet;
	
	public FunStruct() {
		excuteeSet = new RSet<ExcuteeStruct>();
		paramSet = new RSet<VarStruct>();
		returnSet = new RSet<VarStruct>();
		subFunSet = new RSet<SubFunStruct>();
		commentSet = new RSet<CommentStruct>();
	}
	
	/**
	 * @param name
	 * 		要求符合组件命名规范。
	 * @throws IllegalArgumentException
	 * 		如果不符合组件命名规范就会抛出异常。
	 */
	@Override
	public void defineName(String name) {
		//以组件命名规范进行命名。
		this.defineNameAsComponentName(name);
	}

	// *************************集合定义*******************************

	public void defineExcutees_by_RSet(RSet<ExcuteeStruct> eSet) {
		if (excuteeSet == null) {
			throw new IllegalArgumentException("通过集合定义FunStruct中的执行入口时，"
					+ "传入参数为null。");
		}
		
		for (ExcuteeStruct eStruct : excuteeSet) {
			defineExcutee(eStruct);
		}
	}

	public void defineParameters_by_RSet(RSet<VarStruct> pSet) {
		if (pSet == null) {
			throw new IllegalArgumentException("通过集合定义FunStruct中的参数组件时，"
					+ "传入参数为null。");
		}
		
		for (VarStruct vStruct : pSet) {
			defineParameter(vStruct);
		}
	}

	public void defineReturnvals_by_RSet(RSet<VarStruct> rSet) {
		if (rSet == null) {
			throw new IllegalArgumentException("通过集合定义FunStruct中的返回值组件时，"
					+ "传入参数为null。");
		}
		
		for (VarStruct vStruct : rSet) {
			defineReturnval(vStruct);
		}
	}

	public void defineSubFuns_by_RSet(RSet<SubFunStruct> sSet) {

		if (sSet == null) {
			throw new IllegalArgumentException("通过集合定义FunStruct中的子Function时，"
					+ "传入参数为null。");
		}
		
		for (SubFunStruct sfStruct : sSet) {
			defineSubFun(sfStruct);
		}
	}

	/**
	 * 定义注释。
	 * @param commentSet
	 * 		注释结构集合。
	 */
	public void defineComments_by_RSet(RSet<CommentStruct> commentSet) {
		if (commentSet == null) {
			throw new IllegalArgumentException("通过集合定义FunStruct中的注释时，"
					+ "传入参数为null。");
		}
		
		for (CommentStruct cStruct : commentSet) {
			defineComment(cStruct);
		}
	}
	

	// ********************单体定义*********************************

	@Override
	public void defineExcutee(ExcuteeStruct eStruct) {
		if (eStruct == null) {
			throw new IllegalArgumentException("不能用null来定义执行入口。");
		}
		
		excuteeSet.add(eStruct);
	}

	@Override
	public void defineParameter(VarStruct vStruct) {
		if (vStruct == null) {
			throw new IllegalArgumentException("不能用null来定义参数组件。");
		}
		
		paramSet.add(vStruct);
	}

	@Override
	public void defineReturnval(VarStruct vStruct) {
		if (vStruct == null) {
			throw new IllegalArgumentException("不能用null来定义返回值组件。");
		}
		
		returnSet.add(vStruct);
	}

	@Override
	public void defineSubFun(SubFunStruct sfStruct) {
		if (sfStruct == null) {
			throw new IllegalArgumentException("不能用null来定义子Function。");
		}
		
		subFunSet.add(sfStruct);
	}

	@Override
	public void defineComment(CommentStruct cStruct) {
		if (cStruct == null) {
			throw new IllegalArgumentException("不能用null来定义注释。");
		}
		
		commentSet.add(cStruct);
	}
	
	// *************************属性获取*******************************

	@Override
	public RSet<ExcuteeStruct> getExcuteeSet(){
		return excuteeSet;
	}

	@Override
	public RSet<CommentStruct> getCommentSet(){
		return commentSet;
	}

	@Override
	public RSet<VarStruct> getParameterSet() {
		return paramSet;
	}

	@Override
	public RSet<VarStruct> getReturnvalSet() {
		return returnSet;
	}

	@Override
	public RSet<SubFunStruct> getSubFunSet() {
		return subFunSet;
	}
	

}
