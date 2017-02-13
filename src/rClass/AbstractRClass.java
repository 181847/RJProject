package rClass;

import basicTool.NameableWithString;
import functionInterface.IFunction;
import functionInterface.IFunctionHeadSlot;
import functionInterface.IFunctionMaker;
import functionInterface.IFunctionRearSlot;
import rClassInterface.IRClass;
import rClassInterface.IRReference;

/**
 * 这个AbstractRClass的主要实现的方法是实现的RClassID的设置和获取，
 * constructionFunction的设置和获取，
 * 创建一个construcFunctionMaker成员变量，
 * 创建了一个成员变量存储RClassID。
 */
public abstract class AbstractRClass extends NameableWithString implements IRClass {
	/**
	 * 用于存储一个构造FunctionMaker。
	 */
	IFunctionMaker constructFunctionMaker;
	
	/**
	 * 从RClassLoader那里分配到的RClassID。
	 */
	public int rClassID;
	
	/**
	 * @param rClassName RClass的名字。
	 */
	public AbstractRClass(String rClassName){
		super(rClassName);
	}
	
	@Override
	public int getRClassID(){
		return rClassID;
	}
	
	@Override
	public void setRClassID(int rClassID){
		this.rClassID = rClassID;
	}

	/**
	 * 得到ConstructFunction的实例，
	 * 如果constructFunctionMaker为null，这个方法就返回null。
	 * @return 一个constructFunction实例。
	 */
	@Override
	public IFunction ConstructFunction(){
		if (constructFunctionMaker == null){
			return null;
		}
		
		IFunction newFunctionInstance = constructFunctionMaker.makeFunction();
		if (newFunctionInstance != null){
			newFunctionInstance.setRClassID(getRClassID());
		}
		return newFunctionInstance;
	}
	
	/**
	 * 为RClass，插入一个构造constructFunctionMaker。
	 * @param constructFunctionMaker 一个用于产生构造Function的FunctionMaker。
	 * @return 成功返回1，如果已存在就返回-1，
	 * 不对原来的构造Function做任何改动。
	 */
	public int insertConstructFunctionMaker(IFunctionMaker constructFunctionMaker){
		if (this.constructFunctionMaker == null){
			this.constructFunctionMaker = constructFunctionMaker;
			return 1;
		}
		return 2;
	}
	
	@Override
	public abstract IRReference getNewInstance();

	@Override
	public abstract IFunction Function(String functionName);

	@Override
	public abstract IFunction getFunction(int functionIndex);

	@Override
	public abstract int getFunctionIndexOf(String functionName);

	@Override
	public abstract int fillFunctionGraphOf(String functionName, IFunctionRearSlot rearSlot, IFunctionHeadSlot headSlot);

	@Override
	public abstract int fillFunctionGraphOf(int functionIndex, IFunctionRearSlot rearSlot, IFunctionHeadSlot headSlot);
}
