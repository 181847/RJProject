package unfinishedClass.customRClass.struct;

import unfinishedClass.customRClass.scriptBlock.ScriptDeclaration;
import unfinishedClass.customRClass.scriptBlock.information.ExcuterType;

/**
 * Function的Excutee（执行出口）组件信息，
 * 具体的详细信息包括：
 * Excuter的类型，可分为两类NE(NormalExcuter)/EE(ExceptionExcuter)
 */
public class ExcuterStruct extends Struct {
	protected ExcuterType excuterType;
	public ExcuterStruct(String excuterInfo){
		String[] infoArray = excuterInfo.split(ScriptDeclaration.fence);
		
		//设置执行出口的类型
		if (infoArray[0].equals(ScriptDeclaration.normalExcuter)){
			excuterType = ExcuterType.NORMAL_EXCUTER;
		} else {
			excuterType = ExcuterType.EXCEPTION_EXCUTER;
		}
		
		//设置执行出口的名字。
		setName(infoArray[1]);
	}
}
