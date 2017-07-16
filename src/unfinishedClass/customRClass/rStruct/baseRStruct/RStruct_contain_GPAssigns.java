package unfinishedClass.customRClass.rStruct.baseRStruct;

import unfinishedClass.Exception.RStructException.NameConflictException;
import unfinishedClass.customRClass.rStruct.GPAssignStruct;
import unfinishedClass.customRClass.rStruct.RSet;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_GPAssigns;

/**
 * 包含泛参指配的父类。
 */
public class RStruct_contain_GPAssigns 
implements IRStruct_contain_GPAssigns {
	
	/**
	 * 泛参指定集合。
	 */
	public RSet<GPAssignStruct> gpaSet;

	@Override
	public void defineGPAssigns_by_RSet(RSet<GPAssignStruct> gpaSet) {
		if (gpaSet == null) {
			throw new IllegalArgumentException(
					"通过集合定义泛参指定时，传入的参数为null。");
		}
		
		for (GPAssignStruct gpaStruct : gpaSet) {
			defineGPAssign(gpaStruct);
		}
	}

	@Override
	public void defineGPAssign(GPAssignStruct gpaStruct) {
		if (gpaStruct == null) {
			throw new IllegalArgumentException(
					"不能用null来定义泛参指配。");
		}
		
		//TODO 在这里要添加对泛参指配名称冲突的检查，
		//一旦发生名称冲突就要抛出NameConflictException。
		
		gpaSet.add(gpaStruct);
	}

}
