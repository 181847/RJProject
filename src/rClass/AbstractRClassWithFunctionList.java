package rClass;

import function.tool.FunctionList;
import functionInterface.IFunction;
import functionInterface.IFunctionHeadSlot;
import functionInterface.IFunctionList;
import functionInterface.IFunctionRearSlot;
import rClassInterface.IRClass;
import rClassInterface.IRReference;
import unfinishedClass.AbstractRClass;

/**
 * 基本数据类型RClass和Java包装类RClass的父类抽象类，
 * 其中包含一个functionList，通过添加具体的Function的实例对象，
 * 来记录该RClass可使用的Function有哪些，
 * 获取Function实例对象的时候，
 * 先找到之前添加进来的Function实例对象，
 * 然后通过这个实例对象用getClass()方法得到对应Function的Class，
 * 然后发动newInstance()方法得到一个新的Function对象，并返回。
 */
public abstract class AbstractRClassWithFunctionList extends AbstractRClass implements IRClass{
	/**
	 * 用于记录本RClass类的成员Function
	 */
	IFunctionList functionList;
	
	public AbstractRClassWithFunctionList(String name){
		super(name);
		//无参构造方法申请五个Funtion元素空间
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
	
	@Override
	public IFunction ConstructFunction() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 通过名字获取新的Function实例，
	 * 先找到之前添加进来的Function实例对象，
	 * 然后通过这个实例对象用getClass()方法得到对应Function的Class，
	 * 然后发动newInstance()方法得到一个新的Function对象，并返回新生成的Function实例
	 * @param functionName 要获取的Function的名字
	 * @return 一个新的Function实例，注：返回的实例不是原来存储在functionList中的实例，
	 * 而是新生成的实例对象
	 * @throws InstantiationException 
	 * @throws IllegalAccessException
	 */
	public IFunction Function(String functionName){
		IFunction tempFunction = null;
		try {
			tempFunction = functionList.getFunction(functionName);
			if (tempFunction != null){
				tempFunction = tempFunction.getClass().newInstance();
			}
			
		} catch (InstantiationException e) {
			System.out.println("无法获得新的Function实例：指定对象可能为："
					+ "抽象类，接口类，数组类，原始数据类，空类型，"
					+ "或者该类型没有无参构造方法。");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println("getClass()无法得到类对象，newInstance()无法调用。");
			e.printStackTrace();
		}
		return tempFunction;
	}
	
	/**
	 * 通过序号获取新的Function实例，
	 * 先找到之前添加进来的Function实例对象，
	 * 然后通过这个实例对象用getClass()方法得到对应Function的Class，
	 * 然后发动newInstance()方法得到一个新的Function对象，并返回新生成的Function实例
	 * @param functionName 要获取的Function的名字
	 * @return 一个新的Function实例，注：返回的实例不是原来存储在functionList中的实例，
	 * 而是新生成的实例对象
	 * @throws InstantiationException 
	 * @throws IllegalAccessException
	 */
	@Override
	public IFunction getFunction(int functionIndex){
		IFunction tempFunction = null;
		try {
			tempFunction = functionList.getFunction(functionIndex);
			if (tempFunction != null){
				tempFunction = tempFunction.getClass().newInstance();
			}
			
		} catch (InstantiationException e) {
			System.out.println("无法获得新的Function实例：指定对象可能为："
					+ "抽象类，接口类，数组类，原始数据类，空类型，"
					+ "或者该类型没有无参构造方法。");
			e.printStackTrace();			
		} catch (IllegalAccessException e) {
			System.out.println("getClass()无法得到类对象，newInstance()无法调用。");
			e.printStackTrace();
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
									IFunctionRearSlot rearSlot,
									IFunctionHeadSlot headSlot){
		//Empty Body
		return 0;
	}
	
	public int fillFunctionGraphOf(int functionNumber,
									IFunctionRearSlot rearSlot,
									IFunctionHeadSlot headSlot){
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
