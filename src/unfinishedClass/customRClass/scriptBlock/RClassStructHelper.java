package unfinishedClass.customRClass.scriptBlock;

import java.util.Hashtable;

import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct.ExcuteeSet;
import unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct.FunSet;
import unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct.FunctionStruct;
import unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct.RClassStruct;
import unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct.Set;
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
		//TODO 在构造Function中检查父类的构造Function是否被调用。
		//testConFunConstrainOn(rClassStruct, testResult);
		
		return testResult;
	}

	/**
	 * 检查构造Fun约束，保证如果继承了某个父类，
	 * 则必须在构造Fun当中调用一次父类的构造Fun。
	 * @param rClassStruct
	 * 		检查目标。
	 * @param testResult
	 * 		存储测试结果。
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
	 * 		存储测试结果。
	 */
	private static void testNameConstrainOn(RClassStruct rClassStruct, RSTestResult testResult) {
		//测试成员变量中是否有重名。
		//在静态和非静态成员的Set中检查重名Struct。
		testNameCOn(rClassStruct.getStaticMemberVarSet(),
				rClassStruct.getMemberVarSet(),
				null,
				"RClass的成员变量重名检查",
				testResult);
		
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
	 * 		存储测试结果。
	 */
	private static void testFunCompNameCOn(RClassStruct rClassStruct, RSTestResult testResult) {
		FunctionStruct fun = rClassStruct.getConFun();
		//检查构造Function组件名。
		if (fun != null){
			testFunCompNCOn(fun, testResult);
		}

		FunSet funSet
			= rClassStruct.getStaticFunSet();
		//规定循环两次，
		//分别对静态Function和非静态Function进行检查，
		//第一次检查静态Function，
		//第二次检查非静态Function。
		for(int loop = 2, funNum = 0; loop > 0; --loop){
			if (funSet != null){
				for (funNum = funSet.getNum(); funNum >= 0; --funNum){
					testFunCompNCOn(fun, testResult);
				}//for funNum
			}//if funSet != null
			
			//切换到非静态Function。
			funSet = rClassStruct.getFunSet();
		}//for loop
	}

	/**
	 * 单独检查一个FunctionStruct内部的各个组件是否有重名现象
	 * @param fun
	 * 		被检查的FunctionStruct
	 * @param testResult
	 * 		存储测试结果。
	 */
	private static void testFunCompNCOn(FunctionStruct fun, RSTestResult testResult) {
		Set waitSequence[] = new Set[]{ 
				fun.getExcuteeSet(),
				fun.getParameterSet(),
				fun.getExcuterSet(),
				fun.getReturnvalSet()};
		//用于描述集合中重名检查的字符串，
		//一一对应到waitSequence中的每一个Set的描述。
		String descripArry[] = new String[]{
				"执行出口",
				"参数组件",
				"执行出口",
				"返回值组件"};
		
		for (int loop = 0; loop < 4; ++loop){
			//在各个单独的集合中检查是否有重名的项目
			testNameCOn(waitSequence[loop], descripArry[loop], testResult);
		}
		
		//在静态和非静态成员变量集合中一起
		//检查Function的本地变量是否有重名。
		testNameCOn(fun.getStaticLocalVarSet(), 
				fun.getLocalVarSet(), 
				null,
				"Function本地变量",
				testResult);
	}

	/**
	 * 对两个Set一起进行重名判断，
	 * 找出两个Set中重复的名字。
	 * @param staticLocalVarSet
	 * 		第一个被检查的Set。
	 * @param localVarSet
	 * 		第二个被检查的Set。
	 * @param extraConflicNameArry
	 * 		添加的冲突名字，
	 * 		这些名字将会额外添加进重名的检查过程中。
	 * @param description
	 * 		对于检查过程的详细描述，
	 * 		这个描述信息将会用于向testResult记录错误信息。
	 * @param testResult
	 * 		存储测试结果。
	 */
	private static void testNameCOn(Set firstSet, 
			Set secondSet, String[] extraConflicNameArry, 
			String description, RSTestResult testResult) {
		Hashtable<String, Object> nameTable = 
				new Hashtable<String, Object>(15, 0.5f);
		String name;		//临时存储被检查的名字
		int num;			//计数单个Set中的Struct数量
		
		//添加额外的冲突检测名
		if (extraConflicNameArry != null){
			for (String extraName : extraConflicNameArry){
				nameTable.put(extraName, null);
			}
		}

		Set testSet = firstSet;
		//规定循环两次，
		//分别对firstSet和secondSet进行检查，
		//第一次检查firstSet，
		//第二次检查secondSet。
		for(int loop = 2; loop > 0; --loop){
			if (testSet != null){
				for (num = testSet.getNum(); num >= 0; --num){
					name = testSet
							.getStruct(num)
							.getName();
					//查看是否有记录
					if (nameTable.containsKey(name)){
						//记录重名的错误信息
						testResult.occurredError();
						testResult.appendReason(description 
								+ "的重名检查过程中发现重复的名字：" 
								+ name);
					} else {
						//添加记录
						nameTable.put(name, null);
					}//else
				}//for funNum
			}//if funSet != null
					
			//切换到第二个Set再进行检查。
			testSet = secondSet;
		}//for loop
	}

	/**
	 * 在一个集合当中检查是否有重名的Struct。
	 * @param set
	 * 		被检查的集合，
	 * 		这个集合中包含着拥有名字的Struct对象。
	 * @param description
	 * 		描述信息，
	 * 		用于向testResult输出错误信息。
	 * @param testResult
	 * 		存储测试结果。
	 */
	private static void testNameCOn(Set set, 
			String description, RSTestResult testResult) {
		Hashtable<String, Object> nameTable = 
				new Hashtable<String, Object>(10, 0.5f);
		String name;
		for (int structNum = set.getNum(); structNum > 0; --structNum){
			name = set.getStruct(structNum).getName();
			//查看是否有记录
			if (nameTable.containsKey(name)){
				//记录重名的错误信息
				testResult.occurredError();
				testResult.appendReason(description 
						+ "的重名检查过程中发现重复的名字：" 
						+ name);
			} else {
				//添加记录
				nameTable.put(name, null);
			}
		}
	}

	/**
	 * 测试静态、非静态 和 抽象Function是否有重名。
	 * @param rClassStruct
	 * 		检查目标。
	 * @param testResult
	 * 		检查结果。
	 */
	private static void testFunNameCOn(RClassStruct rClassStruct, RSTestResult testResult) {
		String[] conflicNameArry = null;
		//首先添加构造Function的名字。
		if (rClassStruct.getType() != InformationType.INTERFACE){
			//如果不是接口RClass就添加上构造Function的名字。
			FunctionStruct conFun = rClassStruct.getConFun();
			if (conFun != null){
				//添加上构造Function的名字，
				//这个名字将会在后来的静态和非静态Function重名检查中用到，
				//防止和构造Function重名。
				conflicNameArry = new String[]{conFun.getName()};
			}
		}
		//在静态和非静态Function的Set中检查重名Struct。
		testNameCOn(rClassStruct.getStaticFunSet(),
				rClassStruct.getFunSet(),
				conflicNameArry,
				"RClass的Function重名检查",
				testResult);
	}

	/**
	 * 检查类型约束，保证RClassStruct中的类型和内部信息匹配。
	 * @param rClassStruct
	 * 		检查目标。
	 * @param testResult
	 * 		检查结果。
	 */
	private static void testTypeConstraintOn(RClassStruct rClassStruct, 
			RSTestResult testResult) {
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
	private static void testClassConstraintOn(RClassStruct rClassStruct, 
			RSTestResult testResult) {
		//保证有构造Function
		if (rClassStruct.getConFun() == null){
			testResult.occurredError();
			testResult.appendReason("普通类型RClass没有声明构造Function。");
		}
		
		//保证声明的Function全是非抽象Function
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
	private static void testAbstractConstraintOn(RClassStruct rClassStruct, 
			RSTestResult testResult) {
		//保证有构造Function
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
	private static void testInterfaceConstraintOn(RClassStruct rClassStruct,
			RSTestResult testResult) {
		//保证没有非接口父类
		if (rClassStruct.getExtend() != null){
			testResult.occurredError();
			testResult.appendReason("接口类型RClass不能声明非接口父类。");
		}
		
		//保证没有静态成员变量
		if (rClassStruct.getStaticMemberVarSet() != null
				|| rClassStruct.getMemberVarSet() != null){
			testResult.occurredError();
			testResult.appendReason("接口类型RClass不能声明成员变量。");
		}
		
		//保证没有构造Function
		if (rClassStruct.getConFun() != null){
			testResult.occurredError();
			testResult.appendReason("接口类型RClass不能声明构造Function。");
		}
		
		//保证没有静态Function
		if (rClassStruct.getStaticFunSet() != null){
			testResult.occurredError();
			testResult.appendReason("接口类型RClass不能声明静态Function。");
		}
		
		//保证声明的Function全是抽象Function
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
	private static void testBasicConstraintOn(RClassStruct rClassStruct, 
			RSTestResult testResult) {
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
