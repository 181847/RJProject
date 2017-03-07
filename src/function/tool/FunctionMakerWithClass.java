package function.tool;

import basicTool.RLogger;
import functionInterface.IFunction;
import functionInterface.IFunctionHeadSlot;
import functionInterface.IFunctionMaker;
import functionInterface.IFunctionRearSlot;
import unfinishedClass.AbstractFunctionMakerWithRClassID;

/**
 * 拥有一个Function类型Class对象的FunctionMaker，
 * 通过Class的反射来创建Function的实例对象，
 * 用于FunctionFactory。
 */
public class FunctionMakerWithClass extends AbstractFunctionMakerWithRClassID implements IFunctionMaker{
	Class<? extends IFunction> functionClass;
	
	public FunctionMakerWithClass(String functionName, Class<? extends IFunction> functionClass){
		super(functionName);
		this.functionClass = functionClass;
	}
	
	public FunctionMakerWithClass(Class<? extends IFunction> functionClass){
		super("");
		this.functionClass = functionClass;
		IFunction newFunctionProduct = null;
		try {
			newFunctionProduct = functionClass.newInstance();
		} catch (InstantiationException e) {
			RLogger.log("从functionClass获取FunctionMaker的名字时失败，"
					+ "可能是因为要创建的Function类是抽象类、接口、数组类、原始类型、void类型，或者该类没有无参构造方法。"
					+ "functionClass的名字：" + functionClass.getName());
			RLogger.logException(e);
			
		} catch (IllegalAccessException e) {
			RLogger.log("从functionClass获取FunctionMaker的名字时失败，"
					+ "由于反射创建Function的实例失败。"
					+ "functionClass的名字：" + functionClass.getName());
			RLogger.logException(e);
		}
		
		if (newFunctionProduct == null){
			setName("ERROR!Name");
		} else {
			setName(newFunctionProduct.getName());
		}
	}

	@Override
	public IFunction makeFunction() {
		if (functionClass == null){
			RLogger.log("FunctionMakerWithClass没有functionClass变量，无法创建Function实例。"
					+ "FunctionMaker的名字：" + getName());
			return null;
		}
		
		IFunction newFunctionProduct = null;
		try {
			newFunctionProduct = functionClass.newInstance();
		} catch (InstantiationException e) {
			RLogger.log("FunctionMaker准备创建一个Function实例，"
					+ "但是要创建的Function类可能为抽象类、接口、数组类、原始类型、void类型，或者该类没有无参构造方法。"
					+ "FunctionMaker的名字：" + getName());
			RLogger.logException(e);
			
		} catch (IllegalAccessException e) {
			RLogger.log("FunctionMaker准备创建一个Function实例，"
					+ "但是反射创建Function的实例失败。"
					+ "FunctionMaker的名字：" + getName());
			RLogger.logException(e);
		}
		
		if (newFunctionProduct == null){
			return null;
		}
		return newFunctionProduct;
	}

	/**
	 * 此方法对于本类型的FunctionMaker无作用。
	 */
	@Override
	public int fillFunctionGraphOf(IFunctionRearSlot rearSlot, IFunctionHeadSlot headSlot) {
		return 0;
	}
}
