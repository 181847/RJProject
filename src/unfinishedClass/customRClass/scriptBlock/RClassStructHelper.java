package unfinishedClass.customRClass.scriptBlock;

import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct.RClassStruct;

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
	 * 检查名称唯一性。
	 * @param rClassStruct
	 * 		检查目标。
	 * @param testResult
	 * 		检查结果。
	 */
	private static void testNameConstrainOn(RClassStruct rClassStruct, RSTestResult testResult) {
		// TODO Auto-generated method stub
		
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
		
	}

	/**
	 * 专门测试抽象类型RClassStruct的信息完整。
	 * @param rClassStruct
	 * 		检查目标。
	 * @param testResult
	 * 		检查结果。
	 */
	private static void testAbstractConstraintOn(RClassStruct rClassStruct, RSTestResult testResult) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 专门测试接口类型RClassStruct的信息完整。
	 * @param rClassStruct
	 * 		检查目标。
	 * @param testResult
	 * 		检查结果。
	 */
	private static void testInterfaceConstraintOn(RClassStruct rClassStruct, RSTestResult testResult) {
		// TODO Auto-generated method stub
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
				funSet.haveAbstractFun()){
			testResult.occurredError();
			testResult.appendReason("接口类型RClass不能声明抽象Function。");
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
