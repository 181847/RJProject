package unfinishedClass.customRClass.rStruct;

import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * 泛参指定结构，
 * 包括一个原泛参名，
 * 一个类引用。
 */
public class GPAssignStruct implements IRStruct {
	/**
	 * 同时指定被指配的泛参名和对应的类型引用的名字
	 * （泛参名或者实类型名）。
	 * @param assign
	 * 		形如“T: basic.Integer”，其中“T"就是被指配泛参名，
	 * 		“basic.Integer”就是用于指配的类型引用。
	 */
	public void defineAssign(String assign) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 设置指配类型，
	 * 泛参可以用实类型来指配，
	 * 或者用另一个泛参来指配。
	 * @param infoType
	 * 		GP_ASSIGN_CL（实类型）或者GP_ASSIGN_GP（泛参）。
	 */
	public void defineType(InformationType infoType) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 当指配类型为GP_ASSIGN_CL时，
	 * 可能需要为实类型继续指配泛参，
	 * 通过这个方法来对当前用于指配的实类型引用来继续进行泛参指配。
	 * @param subAssignSet
	 * 		对应实类型的泛参指配。
	 */
	public void defineGPAssign_forArgument_by_RSet(
			RSet<GPAssignStruct> subAssignSet) {
		// TODO Auto-generated method stub
		
	}

}
