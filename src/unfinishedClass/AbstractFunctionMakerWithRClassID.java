package unfinishedClass;

import basicInterface.INameable;
import basicTool.NameableWithString;
import functionInterface.IFunction;
import functionInterface.IFunctionHeadSlot;
import functionInterface.IFunctionMaker;
import functionInterface.IFunctionRearSlot;

/**
 * 这个类实现了IFunctionMaker中的关于获取和和设置RClassID的方法。
 */
public abstract class AbstractFunctionMakerWithRClassID extends NameableWithString
		implements IFunctionMaker, INameable {
	public int rClassID;
	
	/**
	 * @param name FunctionMaker的名字，
	 * 这个名字同时也应该是这个FunctionMaker所要创建的Function的名字。
	 */
	public AbstractFunctionMakerWithRClassID(String name){
		super(name);
	}
	
	@Override
	public abstract IFunction makeFunction();

	@Override
	public abstract int fillFunctionGraphOf(
			IFunction headSentryFunction,
			IFunction rearSentryFunction);
	
	/**
	 * @return 当前FunctionMaker所属的RClass的ID。
	 */
	@Override
	public int getRClassID() {
		return rClassID;
	}

	/**
	 * 设置这个FunctionMaker所属的RClass的ID。
	 * @param rClassID FunctionMaker所属的RClass的ID。
	 */
	@Override
	public void setRClassID(int rClassID) {
		this.rClassID = rClassID;
	}
}
