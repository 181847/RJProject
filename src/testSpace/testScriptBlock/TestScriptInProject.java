package testSpace.testScriptBlock;

import basicTool.RLogger;
import testSpace.Test;

public class TestScriptInProject extends Test {
	public static void main(String[] args){
		prepare();
		
		Test.loader.loadRClassFrom("ProjectForScript.zip");
		RLogger.log("加载工程文件结束。");
	}
}
