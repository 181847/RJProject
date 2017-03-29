package unfinishedClass.customRClass;

import unfinishedClass.customRClass.script.RClassScriptStruct;
import unfinishedClass.customRClass.script.RClassScriptStructHelper;

public class LoadCusRClassSequenceHelper {
	public static void showSequence(LoadCusRClassSequence sequence){
		int length = sequence.getNum();
		RClassScriptStruct scriptStruct;
		for (int i = 0; i < length; ++i){
			 scriptStruct = (RClassScriptStruct) sequence.getItem(i);
			 RClassScriptStructHelper.showScript(scriptStruct);
		}
	}
}
