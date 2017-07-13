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
