package rClass;

import function.tool.FunctionMakerWithClass;
import functionInterface.IFunction;
import functionInterface.IFunctionHeadSlot;
import functionInterface.IFunctionRearSlot;
import rClassInterface.IRClass;
import rClassInterface.IRReference;

public abstract class AbstractRClassForJava extends AbstractRClassWithFunctionFactory implements IRClass {

	/**
	 * @param rClassName RClass的名字。
	 * @param factorySpace functionFactory的初始空间数量，
	 * functionFactory用来装载产生function的functionMaker，
	 * 虽说空间会在不够的时候自动扩容，
	 * 但是先定义好空间数量更节省性能。
	 */
	public AbstractRClassForJava(String rClassName, int factorySpace) {
		super(rClassName, factorySpace);
	}
	
	/**
	 * 自动为functionFactory申请5个空间数量。
	 * @param rClassName RClass的名字。
	 */
	public AbstractRClassForJava(String rClassName){
		super(rClassName, 5);
	}
	
	@Override
	public IRReference getNewInstance() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 为functionFactory，插入一个FunctionMaker，
	 * 这里不手动传入Function的名字，而是由程序自动生成一个Function实例，
	 * 然后从这个新生成的Function实例获得名字，进而设置FunctionMaker的名字。
	 * @param functionClass 用于直接创建Function的FunctionClass。
	 * @return 如果已存在相同名字的元素就返回2； 如果插入成功就返回1。
	 */
	public int insertFunctionClass(Class<? extends IFunction> functionClass){
		return insertFunctionMaker(new FunctionMakerWithClass(functionClass));
		
	}
	
	/**
	 * 为FunctionFactory，插入一个构造FunctionMaker。
	 * @param functionClass 一个用于产生构造Function的Function类型。
	 * @return 成功返回1，如果已存在就返回-1，
	 * 不对原来的构造Function做任何改动。
	 */
	public int insertConstructFunctionClass(Class<? extends IFunction> functionClass){
		return insertConstructFunctionMaker(new FunctionMakerWithClass(functionClass));
	}
	

	@Override
	public int fillFunctionGraphOf(String functionName, IFunctionRearSlot rearSlot, IFunctionHeadSlot headSlot) {
		//Empty Body
		return 0;
	}

	@Override
	public int fillFunctionGraphOf(int functionIndex, IFunctionRearSlot rearSlot, IFunctionHeadSlot headSlot) {
		//Empty Body
		return 0;
	}
}
