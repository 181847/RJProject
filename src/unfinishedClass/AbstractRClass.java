package unfinishedClass;

import basicTool.NameableWithString;
import functionInterface.IFunction;
import functionInterface.IFunctionHeadSlot;
import functionInterface.IFunctionRearSlot;
import rClassInterface.IRClass;
import rClassInterface.IRReference;

/**
 * 这个AbstractRClass的主要实现的方法是实现的RClassID的设置和获取，
 * 创建了一个成员变量存储RClassID。
 * @author 75309
 *
 */
public abstract class AbstractRClass extends NameableWithString implements IRClass {
	/**
	 * 从RClassLoader那里分配到的RClassID
	 */
	public int rClassID;
	
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

	@Override
	public abstract IRReference getNewInstance();

	@Override
	public abstract IFunction ConstructFunction();

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
