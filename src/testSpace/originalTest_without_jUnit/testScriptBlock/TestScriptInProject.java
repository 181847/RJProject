package testSpace.originalTest_without_jUnit.testScriptBlock;

import basicTool.RLogger;
import testSpace.originalTest_without_jUnit.unordered.Test;

public class TestScriptInProject extends Test {
	public static void main(String[] args){
		prepare();
		
		Test.loader.loadRClassFrom("Project_2017_7_1.zip");
		RLogger.log("加载工程文件结束。");
	}
}
