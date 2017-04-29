package unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct;

import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * 收集Function信息的类型，
 * 这个类可以作为各种类型Function的承载类型：
 * ConFun/StaticFun/Fun/AbstractFun。
 */
public class FunctionStruct {
	protected InformationType funType;
	protected String funName;
	protected ExcuteeSet excuteeSet;
	protected ParameterSet parameterSet;
	protected ExcuterSet excuterSet;
	protected ReturnvalSet returnvalSet;
	protected VarFieldStruct localVarSet;
	protected SubFunSet subFunSet;
	protected ArcSet arcSet;
	protected CommentSet commentSet;

	/**
	 * 设置Function的类型，
	 * 包括以下四种：<br>
	 * InformationType.CONFUN/STATICFUN/FUN/ABSTRACTFUN
	 * @param fun
	 * 		function的类型。
	 */
	public void setType(InformationType type) {
		funType = type;
	}

	/**
	 * 设置Function的名字。
	 * @param functionName
	 * 		Function的名字。
	 */
	public void setName(String functionName) {
		funName = functionName;
	}

	/**
	 * 添加Excutee组件集合。
	 * @param excuteeSet
	 * 		Excutee组件的集合。
	 */
	public void setExcuteeSet(ExcuteeSet excuteeSet) {
		this.excuteeSet = excuteeSet;
	}

	/**
	 * 添加Parameter组件集合。
	 * @param parameterSet
	 * 		Parameter组件信息集合。
	 */
	public void setParameterSet(ParameterSet parameterSet) {
		this.parameterSet = parameterSet;
	}

	/**
	 * 添加Excuter组件集合。
	 * @param excuterSet
	 * 		Excuter组件信息集合。
	 */
	public void setExcuterSet(ExcuterSet excuterSet) {
		this.excuterSet = excuterSet;
	}
	
	/**
	 * 添加Returnval组件集合。
	 * @param returnvalSet
	 * 		Returnval组件信息集合。
	 */
	public void setReturnvalSet(ReturnvalSet returnvalSet) {
		this.returnvalSet = returnvalSet;
	}

	/**
	 * 添加本地变量集合。
	 * @param varFieldStruct
	 * 		本地变量集合。
	 */
	public void setLocalVarSet(VarFieldStruct varFieldStruct) {
		localVarSet = varFieldStruct;
	}

	/**
	 * 添加子Function集合。
	 * @param varFieldStruct
	 * 		子Function集合。
	 */
	public void setSubFunSet(SubFunSet subFunSet) {
		this.subFunSet = subFunSet;
	}

	/**
	 * 添加弧线集合。
	 * @param arcSet
	 * 		弧线集合。
	 */
	public void setArcSet(ArcSet arcSet) {
		this.arcSet = arcSet;
	}

	/**
	 * 添加一个注释集合。
	 * @param commentSet
	 * 		注释集合。
	 */
	public void setCommentSet(CommentSet commentSet) {
		this.commentSet = commentSet;
	}
}
