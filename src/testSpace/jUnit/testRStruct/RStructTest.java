package testSpace.jUnit.testRStruct;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import testSpace.testTool.ScriptPicker;
import unfinishedClass.customRClass.rStruct.ArcStruct;
import unfinishedClass.customRClass.rStruct.ExcuteeStruct;
import unfinishedClass.customRClass.rStruct.FunStruct;
import unfinishedClass.customRClass.rStruct.GPAssignStruct;
import unfinishedClass.customRClass.rStruct.GenParamStruct;
import unfinishedClass.customRClass.rStruct.RClassRefStruct;
import unfinishedClass.customRClass.rStruct.RClassStruct;
import unfinishedClass.customRClass.rStruct.RSet;
import unfinishedClass.customRClass.rStruct.SubFunStruct;
import unfinishedClass.customRClass.rStruct.VarStruct;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_name;
import unfinishedClass.customRClass.rStruct.detailInterface.IRStruct_contain_name_as_RStruct;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.ScriptBlockHelper;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.analysisSpider.ScriptAnalysisSpider;
import unfinishedClass.customRClass.scriptBlock.spider.basicToolSpider.ScriptLineAssignerSpider;
import unfinishedClass.customRClass.scriptBlock.spider.grammarSpider.ScriptGrammarSpider;
import unfinishedClass.customRClass.scriptBlock.spider.structSpider.RClassStructSpider;

public class RStructTest {

	/**
	 * 供测试使用的Script头结点，
	 * 其中存储了RClass的路径，
	 * 子链中存储了所有RClass的定义。
	 */
	private ScriptBlock scriptBlock;
	
	/**
	 * 标准脚本文件路径。
	 */
	private String scriptPath = "src\\testSpace\\res\\script\\standardScript.crc";
	
	/**
	 * 改进后的标准脚本文件路径，
	 * 增加了多个Function，包括构造、静态、非静态、抽象Function，各一个。
	 */
	private String scriptPath_for_multiFunction = "src\\testSpace\\res\\script\\standardScript_1_1.crc";
	
	@Before
	public void setupSequence() {
		scriptBlock = 
				ScriptBlockHelper
				.generateScriptBlock(
						ScriptPicker
						.pickScriptFrom(scriptPath_for_multiFunction));

		//赋予行数信息。
		new ScriptLineAssignerSpider(scriptBlock.getSub())
		.workUntilEnd();

		//分析脚本信息，赋予标签信息。
		new ScriptAnalysisSpider(scriptBlock.getSub())
		.workUntilEnd();
		
		//检查脚本语法正确性。
		ScriptGrammarSpider sgs = new ScriptGrammarSpider(scriptBlock.getSub());
		sgs.workUntilEnd();
		if (sgs.occurredError()) {
			throw new RuntimeException("脚本语法错误，测试无法进行！" + sgs.report());
		}
		
	}
	
	@Test
	public void testRStructConstruct() {
		RClassStructSpider rcss = new RClassStructSpider();
		rcss.placeSpider(scriptBlock.getSub());
		rcss.workUntilEnd();
		
		RClassStruct rcs = rcss.getRStruct();

		// **************************脚本内容检查，初步检查*************************************************
		//名称正确。
		assertEquals("名称检查。", "com.github.liuyang.standard", rcs.getName());
		//类型是否正确
		assertEquals("类型检查。", InformationType.ABSTRACT_RCLASS, rcs.getType());
		//泛参定义有两个。
		assertEquals("泛参定义检查。", 2, rcs.getGenParamSet().size());
		//拥有非接口父类。
		Assert.assertNotNull("非接口父类检查", rcs.getExtends());
		//一个非接口父类。
		assertEquals("非接口父类名检查。", "default.base.baseClass1", rcs.getExtends().getName());
		//三个接口父类。
		assertEquals("接口父类名检查。", 3, rcs.getImplementSet().size());
		//两个静态变量。
		assertEquals("静态变量数量检查。", 2, rcs.getStaticVarSet().size());
		//两个非静态变量。
		assertEquals("非静态变量数量检查。", 2, rcs.getVarSet().size());
		//有构造Function。
		assertNotNull("构造Function存在检查。", rcs.getConstructFunction());
		//一个静态Function。
		assertEquals("静态Function数量检查。", 1, rcs.getStaticFunSet().size());
		//一个非静态Function。
		assertEquals("非静态Function数量检查。", 1, rcs.getFunSet().size());
		//一个抽象Function。
		assertEquals("抽象Function数量检查。", 1, rcs.getAbstractFunSet().size());
		
		// *******************************详细类成员检查**************************************************
		// ************************检查泛参定义************************************
		
		//单独检查泛参定义。
		assertGenParams(
				new String[]{
						"T", 
						"M"},
				new String[]{
						"basic.RObject", 
						"basic.Integer"},
				new InformationType[]{
						InformationType.CLASS_REF_CL, 
						InformationType.CLASS_REF_CL},
				rcs.getGenParamSet());
		
		//单独检查接口父类。
		assertClassRefSet(
				new String[]{
						"default.Interface1", 
						"default.Interface2", 
						"default.Interface3"},
				new InformationType[]{
						InformationType.CLASS_REF_CL, 
						InformationType.CLASS_REF_CL, 
						InformationType.CLASS_REF_CL},
				rcs.getImplementSet());
		
		//静态变量集合检查。
		assertVarSet(
				new String[]{
						"staticMember1",
						"staticMember2"},
				new String[]{
						"basic.Integer",
						"basic.Double"},
				new InformationType[]{
						InformationType.CLASS_REF_CL,
						InformationType.CLASS_REF_CL},
				new String[]{
						"12", 
						"12.2"},
				rcs.getStaticVarSet());
		
		//非静态变量集合检查。
		assertVarSet(
				new String[]{
						"member1",
						"member2"},
				new String[]{
						"basic.Integer",
						"basic.Array"},
				new InformationType[]{
						InformationType.CLASS_REF_CL,
						InformationType.CLASS_REF_CL},
				new String[]{
						"-12", 
						"10"},
				rcs.getVarSet());
		
		//检查构造Function。
		assertFunction("SELF", rcs.getConstructFunction());
		
		//检查唯一的一个静态Function。
		assertFunction("staticFunctionName", rcs.getStaticFunSet().get(0));
		
		//检查唯一的一个非静态Function。
		assertFunction("functionName", rcs.getFunSet().get(0));
		
		//检查唯一的一个抽象Function。
		assertAbstractFunction("abstractFunctionName", rcs.getAbstractFunSet().get(0));
		
		
	}

	/**
	 * 对一个Function结构进行统一的检查。
	 * @param expectedName
	 * 		Function的名字。
	 * @param funStruct
	 * 		被检查的Function结构。
	 */
	private void assertFunction(String name, FunStruct funStruct) {
		//借助抽象Function的检查过程对泛参定义、执行入口、执行出口、参数、返回值组件的信息提取进行检查。
		assertAbstractFunction(name, funStruct);

		//除了泛参定义、执行入口、执行出口、参数、返回值组件，
		//其他的所有信息都在下面进行信息提取检查。
		//本地静态变量检查。
		assertVarSet(
				new String[]{
						"staticLocalVar1", 
						"staticLocalVar2"},
				new String[]{
						"basic.Integer", 
						"basic.Double"},
				new InformationType[]{
						InformationType.CLASS_REF_CL,
						InformationType.CLASS_REF_CL},
				new String[]{
						"", 
						""},
				funStruct.getStaticVarSet());
		
		//本地非静态变量检查。
		assertVarSet(
				new String[]{
						"localVar1"},
				new String[]{
						"basic.Integer"},
				new InformationType[]{
						InformationType.CLASS_REF_CL},
				new String[]{
						"-12"},
				funStruct.getVarSet());
		
		//子Function检查。
		assertSubFunSet(
				new String[]{
						"multiply",
						"add",
						"divide"},
				new String[]{
						"myPackage.RClass1",
						"basic.Integer",
						"basic.Integer"},
				new InformationType[]{
						InformationType.CLASS_REF_CL,
						InformationType.CLASS_REF_CL,
						InformationType.CLASS_REF_CL},
				new double[]{
						-4,
						-5.1,
						15.0},
				new double[]{
						-7,
						-2.55,
						458},
				new String[]{
						"",
						"3",
						""},
				funStruct.getSubFunSet()
				);
		
		//对第一个子Function的泛参指定进行单独检查。
		assertGPAssignSet(
				new String[]{
						"T"},
				new String[]{
						"basic.Integer"},
				new InformationType[]{
						InformationType.CLASS_REF_CL},
				funStruct
				.getSubFunSet()
				.get(0)
				.getGPAssignSet());
		
		//检查执行弧线。
		assertArcSet(
				new int[]{
						1,
						3},
				new String[]{
						"funEnd",
						"end"},
				new int[]{
						4,
						1},
				new String[]{
						"construct",
						"fire"},
				funStruct.getEArcSet());
		
		//检查参数弧线。
		assertArcSet(
				new int[]{
						2},
				new String[]{
						"result"},
				new int[]{
						3},
				new String[]{
						"by"},
				funStruct.getPArcSet());
	}

	/**
	 * 检查弧线定义信息提取。
	 * @param arcStartIndexArr
	 * 		弧线起始序号。
	 * @param arcStartNameArr
	 * 		弧线起始组件名。
	 * @param arcEndIndexArr
	 * 		弧线结束序号。
	 * @param arcEndNameArr
	 * 		弧线结束组件名。
	 * @param arcSet
	 * 		被检查弧线集合。
	 */
	private void assertArcSet(
			int[] arcStartIndexArr, 
			String[] arcStartNameArr, 
			int[] arcEndIndexArr, 
			String[] arcEndNameArr, 
			RSet<ArcStruct> arcSet) {
		
		assertTrue("弧线检查集合数量。",
				arcStartIndexArr.length == arcStartNameArr.length
				&& arcStartIndexArr.length == arcEndIndexArr.length
				&& arcStartIndexArr.length == arcEndNameArr.length
				&& arcStartIndexArr.length == arcSet.size());
		
		ArcStruct aStruct;
		for (int checker = 0; checker < arcStartIndexArr.length; ++checker) {
			aStruct = arcSet.get(checker);
			assertEquals("弧线起始序号检查。", 
					arcStartIndexArr[checker], 
					aStruct.getArcStart().getIndex());
			assertEquals("弧线起始组件名检查。",
					arcStartNameArr[checker],
					aStruct.getArcStart().getName());
			assertEquals("弧线终点序号检查。", 
					arcEndIndexArr[checker], 
					aStruct.getArcEnd().getIndex());
			assertEquals("弧线终点组件名检查。",
					arcEndNameArr[checker],
					aStruct.getArcEnd().getName());
		}
		
	}

	/**
	 * 检查一系列子Function定义，
	 * 不包括子Function中的泛参指配和类型引用中的泛参指配。
	 * @param nameArr
	 * 		子Function名称。
	 * @param classNameArr
	 * 		引用类型名称。
	 * @param typeArr
	 * 		类型引用类型。
	 * @param xArr
	 * 		二维坐标的X轴。
	 * @param yArr
	 * 		二维坐标的Y轴。
	 * @param initArr
	 * 		修改信息，如果没有定义初始化信息的话，
	 * 		默认真实信息为一个空串，
	 * 		假如期望没有修改信息，
	 * 		请填写一个空串，而不是null。
	 * @param subFunSet
	 * 		被检查的子Function集合。
	 */
	private void assertSubFunSet(
			String[] nameArr, 
			String[] classNameArr, 
			InformationType[] typeArr, 
			double[] xArr,
			double[] yArr, 
			String[] modifyArr, 
			RSet<SubFunStruct> subFunSet) {
		//确保各个数组和集合的数量是一致的。
		assertTrue("子Function集合数量。", 
				nameArr.length == classNameArr.length
				&& nameArr.length == typeArr.length
				&& nameArr.length == xArr.length
				&& nameArr.length == yArr.length
				&& nameArr.length == modifyArr.length);
		
		SubFunStruct sfStruct;
		String modifyInfo;
		for (int checker = 0; checker < nameArr.length; ++checker) {
			sfStruct = subFunSet.get(checker);
			
			//子Function名字检查。
			assertEquals("子Function名字检查。", nameArr[checker], sfStruct.getName());
			//类型引用名字检查。
			assertEquals("类型引用名字检查。", classNameArr[checker], sfStruct.getClassRef().getName());
			//类型引用类型检查。
			assertEquals("类型引用类型检查。", typeArr[checker], sfStruct.getClassRef().getType());
			//X轴坐标检查，精确度0.0001。
			assertEquals("X轴坐标检查。", xArr[checker], sfStruct.getLocation().getX(), 0.0001);
			//Y轴坐标检查，精确度0.0001。
			assertEquals("Y轴坐标检查。", yArr[checker], sfStruct.getLocation().getY(), 0.0001);
			
			if (sfStruct.getModifyInfo() == null) {
				//如果没有定义修改信息，就将修改信息初始化为空串。
				modifyInfo = "";
			} else {
				modifyInfo = sfStruct.getModifyInfo().getAllText();
			}
			//修改信息检查。
			assertEquals("修改信息检查。", modifyArr[checker], modifyInfo);
		}
	}

	/**
	 * 检查一系列变量的基本定义。
	 * @param vNameArr
	 * 		变量名。
	 * @param classRefNameArr
	 * 		类型引用名。
	 * @param typeArr
	 * 		类型引用类型。
	 * @param initInfoArr
	 * 		初始化信息。
	 * @param varSet
	 * 		变量集合。 
	 */
	private void assertVarSet(
			String[] vNameArr, 
			String[] classRefNameArr, 
			InformationType[] typeArr,
			String[] initInfoArr, 
			RSet<VarStruct> varSet) {
		assertTrue("集合数量检查。",
				vNameArr.length == classRefNameArr.length
				&& vNameArr.length == typeArr.length
				&& vNameArr.length == initInfoArr.length);
		
		VarStruct vStruct;
		
		for (int checker = 0; checker < vNameArr.length; ++checker) {
			vStruct = varSet.get(checker);
			assertVarStruct(
					vNameArr[checker],
					classRefNameArr[checker],
					typeArr[checker],
					initInfoArr[checker],
					vStruct);
		}
	}

	/**
	 * 按照规定来检查Function有关抽象Function部分的信息，
	 * 其余的信息不予考虑。
	 * @param name
	 * 		期望的Function名称。
	 * @param funStruct
	 * 		被检查的Function
	 */
	private void assertAbstractFunction(String name, FunStruct funStruct) {
		assertEquals("Function定义名称检查。", name, funStruct.getName());
		
		//单独检查泛参定义。
		assertGenParams(
				new String[]{
						"T", 
						"M"},
				new String[]{
						"basic.RObject", 
						"basic.Integer"},
				new InformationType[]{
						InformationType.CLASS_REF_CL, 
						InformationType.CLASS_REF_CL},
				funStruct.getGenParamSet());
		
		//执行入口检查。
		assertNameSet(
				new String[]{"construct"},
				funStruct.getExcuteeSet());
		
		//异常执行出口检查。
		assertNameSet(
				new String[]{
						"CONSTRUCT_FAIL",
			            "ACCESS_DENIED"},
				funStruct.getEExcuterSet());
		
		//普通执行出口检查。
		assertNameSet(
				new String[]{
						"constructEnd"},
				funStruct.getNExcuterSet());
		
		//参数组件检查。
		assertVarSet(
				new String[]{
						"parameter1",
						"parameter2"},
				new String[]{
						"basic.Integer",
						"basic.Double"},
				new InformationType[]{
						InformationType.CLASS_REF_CL,
						InformationType.CLASS_REF_CL},
				new String[]{
						"", 
						""}, 
				funStruct.getParameterSet());
		
		//返回值组件检查。
		assertVarSet(
				new String[]{
						"returnval1",
						"returnval2"},
				new String[]{
						"basic.Integer",
						"basic.Double"},
				new InformationType[]{
						InformationType.CLASS_REF_CL,
						InformationType.CLASS_REF_CL},
				new String[]{
						"", 
						""}, 
				funStruct.getReturnvalSet());
		
	}

	/**
	 * 对所有实现了IRStruct_contain_name接口的RStruct，
	 * 进行检查。
	 * @param strings
	 * 		检查的名字。
	 * @param excuteeSet
	 * 		被检查对象。
	 */
	private void assertNameSet(String[] nameArr, RSet<? extends IRStruct_contain_name> rStructSet) {
		assertTrue("集合数量检查。", 
				nameArr.length == rStructSet.size());
		
		IRStruct_contain_name nStruct;
		for (int checker = 0; checker < nameArr.length; ++checker) {
			nStruct = rStructSet.get(checker);
			assertEquals("名称检查。", nameArr[checker], nStruct.getName());
		}
		
	}

	/**
	 * 对泛参指配进行检查，
	 * 按照序号对每个数组和集合内元素进行对应检查，
	 * 请一定要保证每个数组和集合的元素数量和顺序是一致的。
	 * @param gpNameArr
	 *		所有被指配的泛参名。
	 * @param classRefNameArr
	 * 		所有指配的引用类型的名字。
	 * @param typeArr
	 * 		引用类型的类型。
	 * @param gpSet
	 * 		被检查的泛参指配集合。
	 */
	private void assertGPAssignSet(
			String[] gpNameArr, 
			String[] classRefNameArr, 
			InformationType[] typeArr, 
			RSet<GPAssignStruct> gpSet) {
		assertTrue("集合数量检查。", 
				gpNameArr.length == classRefNameArr.length
				&& gpNameArr.length == typeArr.length
				&& gpNameArr.length == gpSet.size());
		GPAssignStruct gpaStruct;
		
		//循环检查各个对象
		for (int checker = 0; checker < gpNameArr.length ; checker++) {
			//获取单个泛参指配结构。
			gpaStruct = gpSet.get(checker);
			
			assertEquals(
					"泛参指配，泛参名检查。", 
					gpNameArr[checker], 
					gpaStruct.getName());
			
			assertEquals(
					"泛参指配，引用类型名检查。", 
					classRefNameArr[checker], 
					gpaStruct
					.getClassRef()		//获取引用类型。
					.getName());		//获取引用类型名。
			
			assertEquals(
					"泛参指配，指配类型检查。", 
					typeArr[checker], 
					gpaStruct.getType());
		}
		
	}

	/**
	 * 对一个变量结构的简单信息进行判断。
	 * @param varName
	 * 		变量名。
	 * @param classRefName
	 * 		引用类型名。
	 * @param classRefType
	 * 		引用类型类型（引用实类型还是泛参）。
	 * @param initInfo
	 * 		初始化信息，如果期望没有出事化信息就传入一个空串""。
	 */
	private void assertVarStruct(
			String varName, 
			String classRefName, 
			InformationType classRefType, 
			String initInfo, 
			VarStruct vStruct) {
		assertEquals("变量名检查。", varName, vStruct.getName());
		assertEquals("变量引用类型检查。", classRefName, vStruct.getClassRef().getName());
		assertEquals("变量引用类型类型检查。", classRefType, vStruct.getClassRef().getType());
		
		String realInitString = "";
		//如果没有定义初始化信息的话，将真实的初始化信息设为空串“”。
		if (vStruct.getInitInfo() != null) {
			//如果设定了初始化信息，就用真实的信息进行检查。
			realInitString = vStruct.getInitInfo().getAllText();
		}
		assertEquals("初始化信息检查。", initInfo, realInitString);
	}

	
	/**
	 * 检查一系列类型引用。
	 * @param nameArr
	 * 		所有被检查的类型引用的名字。
	 * @param typeArr
	 * 		类型引用的类型。
	 * @param rfSet
	 * 		被检查的类型引用集合。
	 */
	private void assertClassRefSet(
			String[] nameArr, 
			InformationType[] typeArr, 
			RSet<RClassRefStruct> rfSet) {
		//确保检查数量一致。
		assertTrue("集合数量检查。", 
				nameArr.length == rfSet.size() 
				&& nameArr.length == typeArr.length);
		
		RClassRefStruct rfStruct;
		
		for (int checker = 0; checker < nameArr.length; ++checker) {
			rfStruct = rfSet.get(checker);
			assertEquals("类引用名检查。", 
					nameArr[checker], 
					rfStruct.getName());
			
			assertEquals("类引用类型检查。", 
					typeArr[checker], 
					rfStruct.getType());
		}
		
	}

	/**
	 * 针对已获取的RClassStruct的泛参定义部分进行检查。
	 * @param typeArr 
	 * 		约束类型引用的类型。
	 * @param classRefNameArr
	 * 		约束类型引用的名字。 
	 * @param gpNameArr 
	 * 		泛参定义名。
	 * @param gpSet
	 * 		被检查的泛参声明集合。
	 */
	private void assertGenParams(
			String[] gpNameArr, 
			String[] classRefNameArr, 
			InformationType[] typeArr, 
			RSet<GenParamStruct> gpSet) {
		
		assertTrue("集合数量检查。", 
				gpNameArr.length == classRefNameArr.length
				&& gpNameArr.length == typeArr.length
				&& gpNameArr.length == gpSet.size());
		
		GenParamStruct gpStruct;
		
		//循环检查每个元素。
		for (int checker = 0; checker < gpNameArr.length; ++checker) {
			gpStruct = gpSet.get(checker);
		
			//泛参名检查。
			assertEquals("泛参名检查。", 
					gpNameArr[checker], 
					gpStruct.getName());
			
			//T被限定为basic.RObject。
			assertEquals("泛参约束类型引用。", 
					classRefNameArr[checker], 
					gpStruct.getClassRef().getName());
			
			//引用类型为实类型。
			assertEquals("泛参约束的类型引用的类型", 
					typeArr[checker], 
					gpStruct.getClassRef().getType());
		}
	}
}
