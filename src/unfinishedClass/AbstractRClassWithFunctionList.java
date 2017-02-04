package unfinishedClass;

import basicTool.NameableWithString;
import functionInterface.IFunction;
import functionInterface.IFunctionHeadSlot;
import functionInterface.IFunctionList;
import functionInterface.IFunctionRearSlot;
import rClassInterface.IRClass;
import rClassInterface.IRReference;

/**
 * 基本数据类型RClass
 * Java包装类RClass的父类抽象类
 * 其中包含一个functionList，通过添加具体的Function的实例对象，
 * 来记录该RClass可使用的Function有哪些，
 * 获取Function实例对象的时候，
 * 先找到之前添加进来的Function实例对象，
 * 然后通过这个实例对象用getClass()方法得到对应Function的Class，
 * 然后发动newInstance()方法得到一个新的Function对象，并返回
 */
public abstract class AbstractRClassWithFunctionList extends NameableWithString implements IRClass{
	IFunctionList functionList;
	
	public AbstractRClassWithFunctionList(String name){
		super(name);
		//无参构造方法申请五个元素空间
		functionList = new FunctionList();
	}
	
	public AbstractRClassWithFunctionList(String name, int space){
		super(name);
		functionList = new FunctionList(space);
	}

	/**
	 * 需子类重载
	 * 获得本RClass的实例对象的抽象方法
	 */
	public abstract IRReference getNewInstance();
	
	/**
	 * 获取Function实例对象的时候，
	 * 先找到之前添加进来的Function实例对象，
	 * 然后通过这个实例对象用getClass()方法得到对应Function的Class，
	 * 然后发动newInstance()方法得到一个新的Function对象，并返回新生成的Function实例
	 * @throws InstantiationException 
	 */
	public IFunction Function(String functionName) throws InstantiationException, IllegalAccessException{
		IFunction tempFunction = null;
		try {
			tempFunction = functionList.Function(functionName).getClass().newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return tempFunction;
	}
	
	@Override
	public IFunction getFunction(int functionNumber) throws InstantiationException, IllegalAccessException{
		IFunction tempFunction = null;
		try {
			tempFunction = functionList.getFunction(functionNumber).getClass().newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return tempFunction;
	}
	
	public int insertFunction(IFunction function){
		return functionList.insertItem(function);
	}
	
	public int deleteFunctionOf(String functionName){
		return functionList.deleteItem(functionName);
	}
	
	
	public int fillFunctionGraphOf(String functionName,
									IFunctionHeadSlot headSlot,
									IFunctionRearSlot rearSlot){
		//Empty Body
		return 0;
	}
	
	public int fillFunctionGraphOf(int functionNumber,
									IFunctionHeadSlot headSlot,
									IFunctionRearSlot rearSlot){
		//Empty Body
		return 0;
	}
	
	/**
	 * 得到指定名字Function的序号
	 */
	public int getFunctionIndexOf(String functionName){
		return functionList.getIndexOf(functionName);
	}
}
