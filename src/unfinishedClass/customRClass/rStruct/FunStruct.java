package unfinishedClass.customRClass.rStruct;

import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct;
import unfinishedClass.customRClass.rStruct.baseRStruct.RStruct_contain_name_genParams_vars;
import unfinishedClass.customRClass.rStruct.detailInterface.IFunStruct;

/**
 * 存储了一个Function定义的结构体。
 */
public class FunStruct
extends RStruct_contain_name_genParams_vars
implements IRStruct, IFunStruct{
	
	/**
	 * 弧线定义区域。
	 */
	protected ArcFieldStruct arcField;

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
	/**
	 * 定义执行入口。
	 * @param excuteeSet
	 * 		执行入口结构集合。
	 */
	public void defineExcutees_by_RSet(RSet<ExcuteeStruct> excuteeSet) {
		
	}

	/**
	 * 定义参数组件。
	 * @param parameterSet
	 * 		参数结构结合。
	 */
	public void defineParameters_by_RSet(RSet<VarStruct> parameterSet) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 定义执行出口。
	 * @param excuterSet
	 * 		执行出口结构集合。
	 */
	public void defineExcuters_by_RStruct(ExcuterFieldStruct excuterSet) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 定义返回值组件。
	 * @param returnvalSet
	 * 		返回值结构集合。
	 */
	public void defineReturnvals_by_RSet(RSet<VarStruct> returnvalSet) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 定义subFun。
	 * @param subFunSet
	 * 		subFun结构集合。
	 */
	public void defineSubFuns_by_RSet(RSet<SubFunStruct> subFunSet) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 定义注释。
	 * @param commentSet
	 * 		注释结构集合。
	 */
	public void defineComments_by_RSet(RSet<CommentStruct> commentSet) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 定义弧线。
	 * @param arcField
	 * 		弧线区域，包括执行弧线和参数弧线。
	 */
	public void defineArcs_by_RStruct(ArcFieldStruct arcField) {
		if (arcField == null) {
			throw new IllegalArgumentException(
					"通过ArcFieldStruct定义SubFun的弧线区域时，"
					+ "传入的参数为null。");
		}
		
		this.arcField.defineArcs_by_RStruct(arcField);
	}

	@Override
	public void defineEArcs_by_RSet(RSet<ArcStruct> arcSet) {
		if (arcSet == null) {
			throw new IllegalArgumentException(
					"通过弧线集合定义SubFun的执行弧线部分时，"
					+ "传入的参数为null。");
		}
		
		arcField.defineEArcs_by_RSet(arcSet);
	}

	@Override
	public void definePArcs_by_RSet(RSet<ArcStruct> arcSet) {
		if (arcSet == null) {
			throw new IllegalArgumentException(
					"通过弧线集合定义SubFun的参数弧线部分时，"
					+ "传入的参数为null。");
		}
		
		arcField.definePArcs_by_RSet(arcSet);
	}

	// ********************单体定义*********************************
	
	@Override
	public void defineEArc(ArcStruct arcStruct) {
		if (arcStruct == null) {
			throw new IllegalArgumentException(
					"不能用null来定义执行弧线。");
		}
		
		arcField.defineEArc(arcStruct);
	}

	@Override
	public void definePArc(ArcStruct arcStruct) {
		if (arcStruct == null) {
			throw new IllegalArgumentException(
					"不能用null来定义参数弧线。");
		}
		
		arcField.definePArc(arcStruct);
	}

	@Override
	public void defineExcutee(ExcuteeStruct eStruct) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void defineParameter(VarStruct vStruct) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void defineExceptionExcuter(ExcuterStruct eStruct) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void defineNormalExcuter(ExcuterStruct eStruct) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void defineReturnval(VarStruct vStruct) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void defineSubFun(SubFunStruct sfStruct) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void defineComment(CommentStruct cStruct) {
		// TODO Auto-generated method stub
		
	}
	
	// *************************属性获取*******************************

	@Override
	public RSet<ArcStruct> getEArcSet() {
		return arcField.getEArcSet();
	}

	@Override
	public RSet<ArcStruct> getPArcSet() {
		return arcField.getPArcSet();
	}

}
