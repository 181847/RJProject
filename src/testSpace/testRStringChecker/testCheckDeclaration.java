package testSpace.testRStringChecker;

import basicTool.RLogger;
import unfinishedClass.customRClass.RStringChecker;
import unfinishedClass.customRClass.scriptBlock.ScriptDeclaration;

public class testCheckDeclaration {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String checkString = 
				ScriptDeclaration.locationStart
				+ "12.1" + ScriptDeclaration.numberSplit
				+ "45.1" + ScriptDeclaration.locationEnd
				+ ScriptDeclaration.arrow
				+ ScriptDeclaration.locationStart
				+ "45" + ScriptDeclaration.numberSplit
				+ "77.1" + ScriptDeclaration.locationEnd;
		RLogger.log("检查结果：" 
				+ RStringChecker.checkRect(checkString));
	}

}
