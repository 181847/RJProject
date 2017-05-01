package unfinishedClass.customRClass.scriptBlock;

import java.util.Hashtable;

import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct.FunSet;
import unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct.FunctionStruct;
import unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct.RClassStruct;
import unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct.VarSet;

/**
 * 处理RClassStruct相关信息的工具类。
 */
public class RClassStructHelper {

	/**
	 * 测试RClassStruct内部的各个信息约束是否正确。
	 * @param rClassStruct
	 * 		被检查的RClassStruct。
	 * @return
	 * 		一个专门表示检查结果的对象。
	 */
	public static RSTestResult testConstraintOn(RClassStruct rClassStruct) {
		// TODO Auto-generated method stub
		RSTestResult testResult = new RSTestResult();
		
		//检查基本约束：
		//类型信息、名称信息。
		testBasicConstraintOn(rClassStruct, testResult);
		
		//检查类型约束，保证RClassStruct中的类型和内部信息匹配。
		testTypeConstraintOn(rClassStruct, testResult);
		
		//检查名称唯一性
		testNameConstrainOn(rClassStruct, testResult);
		
		//检查构造Fun约束，保证如果继承了某个父类，
		//则必须在构造Fun当中调用一次父类的构造Fun。
		testConFunConstrainOn(rClassStruct, testResult);
		
		return null;
	}

	/**
	 * 检查构造Fun约束，保证如果继承了某个父类，
	 * 则必须在构造Fun当中调用一次父类的构造Fun。
	 * @param rClassStruct
	 * 		检查目标。
	 * @param testResult
	 * 		检查结果。
	 */
	private static void testConFunConstrainOn(RClassStruct rClassStruct, RSTestResult testResult) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 检查名称唯一性，
	 * 即各类成员名称的唯一性，
	 * 以及Function内部组件的名称唯一性。
	 * @param rClassStruct
	 * 		检查目标。
	 * @param testResult
	 * 		检查结果。
	 */
	private static void testNameConstrainOn(RClassStruct rClassStruct, RSTestResult testResult) {
		//测试成员变量中是否有重名。
		testMemNameCOn(rClassStruct, testResult);
		
		//测试静态Fun和普通Fun中是否有重名。
		testFunNameCOn(rClassStruct, testResult);
		
		//测试各个Function内部组件是否有重名现象。
		testFunCompNameCOn(rClassStruct, testResult);
	}

	/**
	 * 测试各个Function内部组件是否有重名现象。
	 * @param rClassStruct
	 * 		检查目标。
	 * @param testResult
	 * 		检查结果。
	 */
	private static void testFunCompNameCOn(RClassStruct rClassStruct, RSTestResult testResult) {
		//TODO
		FunSet funSet;
		FunctionStruct fun;
		int funNum;
		
		fun = rClassStruct.getConFun();
		
		//检查构造Function组件名。
		if (fun != null){
			testFunCompNCOn(fun, testResult);
		}
		
		//规定循环两次，
		//分别对静态Function和非静态Function进行检查，
		//第一次检查静态Function，
		//第二次检查非静态Function。
		for(int loop = 2; loop > 0; --loop){
			if (funSet != null){
				for (funNum = funSet.getNum(); funNum >= 0; ++i){
					testFunCompNCOn(fun, testResult);
				}//for funNum
			}//if funSet != null
			
			//切换到非静态Function。
			funSet = rClassStruct.getFunSet();
		}//for loop
	}

	/**
	 * 测试Fun是否有重名。
	 * @param rClassStruct
	 * 		检查目标。
	 * @param testResult
	 * 		检查结果。
	 */
	private static void testFunNameCOn(RClassStruct rClassStruct, RSTestResult testResult) {
		Hashtable<String, Object> nameTable = 
				new Hashtable<String, Object>(20, 0.5f);
		FunSet funSet = 
				rClassStruct.getStaticFunSet();
		int funNum;
		String name;
		
		//首先添加构造Function的名字。
		if (rClassStruct.getType() != InformationType.INTERFACE){
			nameTable.put(rClassStruct.getConFun().getName(), null);
		}
		
		//规定循环两次，
		//分别对静态Function和非静态Function进行检查，
		//第一次检查静态Function，
		//第二次检查非静态Function。
		for(int loop = 2; loop > 0; --loop){
			if (funSet != null){
				for (funNum = funSet.getNum(); funNum >= 0; ++i){
					name = funSet
							.getFun(funNum)
							.getName();
				
					if (null == nameTable.get(name)){
						nameTable.put(name, null);
					} else {
						testResult.occurredError();
						testResult.appendReason("发现重名的Function名：" + name + "。");
						}
				}//for funNum
			}//if funSet != null
			
			//切换到非静态Function。
			funSet = rClassStruct.getFunSet();
		}//for loop
	}

	/**
	 * 测试成员变量是否有重名。
	 * @param rClassStruct
	 * 		检查目标。
	 * @param testResult
	 * 		检查结果。
	 */
	private static void testMemNameCOn(RClassStruct rClassStruct, RSTestResult testResult) {
		Hashtable<String, Object> nameTable = 
				new Hashtable<String, Object>(20, 0.5f);
		
		VarSet memberSet = 
				rClassStruct.getStaticMemSet();
		int memberNum;
		String name;
		
		//规定循环两次，
		//分别对静态成员和非静态成员进行检查，
		//第一次检查静态成员，
		//第二次检查非静态成员。
		for(int loop = 2; loop > 0; --loop){
			if (memberSet != null){
				for (memberNum = memberSet.getNum(); memberNum >= 0; ++i){
					name = memberSet
							.getVar(memberNum)
							.getName();
				
					if (null == nameTable.get(name)){
						nameTable.put(name, null);
					} else {
						testResult.occurredError();
						testResult.appendReason("发现重名的成员变量名：" + name);
						}
				}//for memberNum
			}//if memberSet != null
			
			//切换到非静态成员。
			memberSet = rClassStruct.getMemSet();
		}//for loop
	}

	/**
	 * 检查类型约束，保证RClassStruct中的类型和内部信息匹配。
	 * @param rClassStruct
	 * 		检查目标。
	 * @param testResult
	 * 		检查结果。
	 */
	private static void testTypeConstraintOn(RClassStruct rClassStruct, RSTestResult testResult) {
		switch(rClassStruct.getType()){
		case INTERFACE:
			//专门测试接口类型RClassStruct的信息完整。
			testInterfaceConstraintOn(rClassStruct, testResult);
			break;
		case ABSTRACT:
			//专门测试接口类型RClassStruct的信息完整。
			testAbstractConstraintOn(rClassStruct, testResult);
			break;
		case CLASS:
			//专门测试接口类型RClassStruct的信息完整。
			testClassConstraintOn(rClassStruct, testResult);
			break;
		default:
			break;
		}
	}

	/**
	 * 专门测试普通类型RClassStruct的信息完整。
	 * @param rClassStruct
	 * 		检查目标。
	 * @param testResult
	 * 		检查结果。
	 */
	private static void testClassConstraintOn(RClassStruct rClassStruct, RSTestResult testResult) {
		// TODO Auto-generated method stub
		if (rClassStruct.getConFun() == null){
			testResult.occurredError();
			testResult.appendReason("普通类型RClass没有声明构造Function。");
		}
		FunSet funSet = rClassStruct.getFunSet();
		if (funSet != null && 
				funSet.haveAbstractFun()){
			testResult.occurredError();
			testResult.appendReason("普通类型RClass不能声明抽象Function。");
		}
	}

	/**
	 * 专门测试抽象类型RClassStruct的信息完整。
	 * @param rClassStruct
	 * 		检查目标。
	 * @param testResult
	 * 		检查结果。
	 */
	private static void testAbstractConstraintOn(RClassStruct rClassStruct, RSTestResult testResult) {
		if (rClassStruct.getConFun() == null){
			testResult.occurredError();
			testResult.appendReason("抽象类型RClass没有声明构造Function。");
		}
	}

	/**
	 * 专门测试接口类型RClassStruct的信息完整。
	 * @param rClassStruct
	 * 		检查目标。
	 * @param testResult
	 * 		检查结果。
	 */
	private static void testInterfaceConstraintOn(RClassStruct rClassStruct, RSTestResult testResult) {
		if (rClassStruct.getExtend() != null){
			testResult.occurredError();
			testResult.appendReason("接口类型RClass不能声明非接口父类。");
		}
		if (rClassStruct.getMemberVarSet() != null){
			testResult.occurredError();
			testResult.appendReason("接口类型RClass不能声明成员变量。");
		}
		if (rClassStruct.getConFun() != null){
			testResult.occurredError();
			testResult.appendReason("接口类型RClass不能声明构造Function。");
		}
		if (rClassStruct.getStaticFun() != null){
			testResult.occurredError();
			testResult.appendReason("接口类型RClass不能声明静态Function。");
		}
		
		FunSet funSet = rClassStruct.getFunSet();
		if (funSet != null && 
				funSet.haveFun()){
			testResult.occurredError();
			testResult.appendReason("接口类型RClass不能声明普通Function，只能声明抽象Function。");
		}
		
	}

	/**
	 * 检查基本约束：
	 * 类型信息、名称信息。
	 * @param rClassStruct
	 * 		检查目标。
	 * @param testResult
	 * 		检查结果。
	 */
	private static void testBasicConstraintOn(RClassStruct rClassStruct, RSTestResult testResult) {
		InformationType type = rClassStruct.getType();
		String name = rClassStruct.getName();
		
		switch(type){
		case INTERFACE:
		case ABSTRACT:
		case CLASS:
			//以上三种情况均为正确情况
			break;
		default:
			//不属于以上三种情况的都是错误。
			//发生错误。
			testResult.occurredError();
			testResult.appendReason("RClass的类型信息错误，未知的类型。");
			break;
		}
		
		if (name == null || name.isEmpty()){
			testResult.occurredError();
			testResult.appendReason("RClass的名称信息为空。");
		}
	}

}
