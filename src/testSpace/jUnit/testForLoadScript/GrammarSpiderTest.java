package testSpace.jUnit.testForLoadScript;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import testSpace.testTool.ScriptPicker;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.ScriptBlockHelper;
import unfinishedClass.customRClass.scriptBlock.spider.analysisSpider.ScriptAnalysisSpider;
import unfinishedClass.customRClass.scriptBlock.spider.basicToolSpider.ScriptLineAssignerSpider;
import unfinishedClass.customRClass.scriptBlock.spider.grammarSpider.ScriptGrammarSpider;

public class GrammarSpiderTest {
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
	}
	
	/**
	 * 测试标准脚本路径的语法是否能够争取。
	 */
	@Test
	public void testScriptGrammarSpider() {
		ScriptGrammarSpider sgs = new ScriptGrammarSpider(scriptBlock.getSub());
		
		sgs.workUntilEnd();
		
		assertFalse(sgs.report(), sgs.occurredError());
	}
}
