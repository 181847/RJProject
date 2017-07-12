package unfinishedClass.customRClass.rStruct;

import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * 存储类型引用的结构，
 * 可以引用实类型、泛型或者泛参。
 */
public class RClassRefStruct implements IRStruct {

	/**
	 * 定义类引用的名字，
	 * 表示泛参的名字或者类名。
	 * @param targetInfoString
	 * 		引用泛参时，这个字符串应该符合组件命名规范；
	 * 		引用实类型时，这个字符串应该符合类名规范。
	 */
	public void defineName(String targetInfoString) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 设置引用的类型，
	 * 引用分为泛参引用和实类型引用。
	 * @param infoType
	 * 		CLASS_REF_CL或者CLASS_REF_GP。
	 */
	public void defineRefType(InformationType infoType) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 如果是实类型引用，
	 * 这个实类型可能是泛型，
	 * 需要为这个泛型内部的泛参进行指配。
	 * @param rSet_fromSub_use
	 * 		包含多个泛参指配结构的集合。
	 */
	public void defineGPAssign_by_RSet(RSet<GPAssignStruct> gpasSet) {
		// TODO Auto-generated method stub
		
	}

}
