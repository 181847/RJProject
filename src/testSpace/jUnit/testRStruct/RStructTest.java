package testSpace.jUnit.testRStruct;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import testSpace.testTool.ScriptPicker;
import unfinishedClass.customRClass.rStruct.RClassStruct;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.ScriptBlockHelper;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.analysisSpider.ScriptAnalysisSpider;
import unfinishedClass.customRClass.scriptBlock.spider.basicToolSpider.ScriptLineAssignerSpider;
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
	
	@Before
	public void setupSequence() {
		scriptBlock = 
				ScriptBlockHelper
				.generateScriptBlock(
						ScriptPicker
						.pickScriptFrom(scriptPath));

		//赋予行数信息。
		new ScriptLineAssignerSpider(scriptBlock.getSub())
		.workUntilEnd();

		//分析脚本信息，赋予标签信息。
		new ScriptAnalysisSpider(scriptBlock.getSub())
		.workUntilEnd();
	}
	
	@Test
	public void testRStructConstruct() {
		RClassStructSpider rcss = new RClassStructSpider();
		rcss.placeSpider(scriptBlock.getSub());
		rcss.workUntilEnd();
		
		rcss.getRStruct();
		
		RClassStruct rcs = rcss.getRStruct();
		
		//名称正确。
		assertEquals("com.github.liuyang.RClassDemo1", rcs.getName());
		//类型是否正确
		assertEquals(InformationType.ABSTRACT_RCLASS, rcs.getType());
		//泛参定义只有一个。
		assertEquals(1, rcs.getGenParamSet().size());
		//两个静态成员变量。
		assertEquals(2, rcs.getStaticVarSet().size());
		//两个非静态变量。
		assertEquals(2, rcs.getVarSet().size());
		//没有构造Function。
		assertEquals(null, rcs.getConstructFunction());
		//没有静态Function。
		assertEquals(0, rcs.getStaticFunSet().size());
		//一个非静态Function。
		assertEquals(1, rcs.getFunSet().size());
		//没有抽象Function。
		assertEquals(0, rcs.getAbstractFunSet().size());
	}
}
