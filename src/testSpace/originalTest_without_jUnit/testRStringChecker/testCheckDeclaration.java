package testSpace.originalTest_without_jUnit.testRStringChecker;

import basicTool.RLogger;
import unfinishedClass.customRClass.RStringChecker;
import unfinishedClass.customRClass.scriptBlock.ScriptDeclaration;

public class testCheckDeclaration {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String checkString = 
				ScriptDeclaration.location_start
				+ "12.1" + ScriptDeclaration.numberSplit
				+ "45.1" + ScriptDeclaration.location_end
				+ ScriptDeclaration.arrow
				+ ScriptDeclaration.location_start
				+ "45" + ScriptDeclaration.numberSplit
				+ "77.1" + ScriptDeclaration.location_end;
		RLogger.log("检查结果：" 
				+ RStringChecker.checkRect(checkString));
	}

}
