package testSpace.testScriptBlock;

import testSpace.Test;

public class TestScriptInProject extends Test {
	public static void main(String[] args){
		prepare();
		
		Test.loader.loadRClassFrom("ProjectForScript.zip");
	}
}
