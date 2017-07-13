package unfinishedClass.customRClass.rStruct;

import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_genParams;

/**
 * 存储了一个Function定义的结构体。
 */
public class FunStruct 
implements IRStruct ,
IRStruct_contain_genParams{

	/**
	 * 设置Function的名字。
	 * @param funName
	 * 		如果Function是ConFun类型，传入的字符串
	 * 		固定是一个不符合组件命名规范的字符串；
	 * 		其他情况传入的都是符合组件命名规范的字符串。
	 */
	public void defineName(String funName) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 定义Function的泛参。
	 * @param gpSet
	 * 		泛参结构集合。
	 * @return 
	 */
	public int defineGenParams_by_RSet(RSet<GenParamStruct> gpSet) {
		//TODO
		return 0;
	}

	/**
	 * 定义执行入口。
	 * @param excuteeSet
	 * 		执行入口结构集合。
	 */
	public void defineExcutees_by_RSet(RSet<ExcuteeStruct> excuteeSet) {
		// TODO Auto-generated method stub
		
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
	 * 定义本地变量。
	 * @param varField
	 * 		变量区域，内部包括静态变量和非静态变量。
	 */
	public void defineLocalVars_by_RStruct(VarFieldStruct varField) {
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
	 * 定义弧线。
	 * @param arcField
	 * 		弧线区域，包括执行弧线和参数弧线。
	 */
	public void defineArcs_by_RStruct(ArcFieldStruct arcField) {
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

}
