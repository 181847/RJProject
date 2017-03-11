package unfinishedClass.customRClass;

import basicTool.RLogger;
import function.tool.FunctionFactory;
import functionInterface.IFunctionMaker;
import rClass.RClassLoaderManager;
import rClassInterface.IRClass;
import unfinishedClass.RReferenceInfo;

public class CustomRClassHelper {
	/**
	 * 关于subRClass的继承，必须保证subRClass已经分配好了ID，
	 * 任何正确性检查都放在外部进行，
	 * 本方法只负责初始化。
	 * @param superID
	 * 		父类RClassID。
	 * @param interfaceIDs
	 * 		要实现的接口的RClassID。
	 * @param staticMembers
	 * 		静态成员的信息。
	 * @param normalMembers
	 * 		普通成员的信息。
	 * @param functionMakers
	 * 		成员FunctionMaker。
	 * @return
	 */
	public static int extendsRClass(
			CustomRClass subRClass,
			int superID, 
			int[] interfaceIDs,
			RReferenceInfo[] staticMembers,
			RReferenceInfo[] normalMembers,
			IFunctionMaker constructFunctionMaker,
			IFunctionMaker[] functionMakers){
		
		subRClass.insertConstructFunctionMaker(constructFunctionMaker);
		
		//创建成员Function偏移索引和成员FunctionMaker
		subRClass.functionOffsetIndex = new OffsetIndex();
		subRClass.customFunctionFactory = new CustomFunctionFactory();
		subRClass.functionFactory = subRClass.customFunctionFactory;
		for (int interfaceID : interfaceIDs){
			extendsFunction(subRClass, interfaceID);
		}
		extendsFunction(subRClass, superID);
		
		//获取父类RClass对象
		CustomRClass superRClass = 
				(CustomRClass) RClassLoaderManager
						.getRClassLoader()
						.getRClass(superID);
		

		//初始化成员信息
		subRClass.staticMembers = staticMembers;
		subRClass.normalMembers = normalMembers;
		
		//继承父类成员，
		//并且添加本类新定义的成员。
		extendsMembers(subRClass, superRClass.members, staticMembers, normalMembers); 
		
		//TODO
		return 0;
	}
	
	/**
	 * 从指定的父类那里继承Function，
	 * 每个父类的Function都会放在各个父类的区域内，
	 * 
	 * @param subRClass
	 * 		子类RClass对象，
	 * 		子类对象必须初始化好functionOffsetIndex和customFunctionFactory。
	 * @param superID
	 * 		要继承的父类RClassID。
	 * @return
	 * 		subRClass为null返回0；
	 * 		superID大于等于0，返回-1；
	 * 		成功返回1。
	 */
	public static int extendsFunction(CustomRClass subRClass, int superID){
		if (subRClass == null){
			RLogger.logError("CustomRClassHelper为子类RClass继承父类Function的时候发生错误，"
					+ "子类RClass对象为null，继承失败。");
			return 0;
		}
		if (superID >= 0){
			RLogger.logError("CustomRClassHelper为子类RClass继承父类Function的时候发生错误，"
					+ "要继承的父类RClassID不是CustomRClass，请检查，superID：" + superID + "。");
			return -1;
		}
		
		CustomRClass superRClass = 
				(CustomRClass) RClassLoaderManager
				.getRClassLoader()
				.getRClass(superID);
		
		//继承extendedRClass的所有父类，包括接口和抽象类
		for (int superRClassID : superRClass.getSuperIDArray()){
			if ( subRClass.haveNotExtended(superRClassID) ) {
				extendsFunction(subRClass, superRClassID);
			}
		}
		
		CustomFunctionFactory subFunctionFactory = subRClass.customFunctionFactory;
		subRClass.functionOffsetIndex.addOffsetIndex(superID, subFunctionFactory.getNum());
		
		for (IFunctionMaker functionMaker : superRClass.customFunctionFactory.toFunctionMakerArray()){
			//将当前subFunctionFactory中的同名Function覆盖掉，
			//如果没有同名的Function，就直接退出。
			subFunctionFactory.overrideFunctionMaker(functionMaker);
			
			//讲这个FunctionMaker追加到subFunctionFactory的尾部。
			subFunctionFactory.addFunctionMaker(functionMaker);
		}
		return 1;
	}
}
