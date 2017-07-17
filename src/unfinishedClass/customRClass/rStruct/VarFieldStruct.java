package unfinishedClass.customRClass.rStruct;

import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_vars;

/**
 * 包括静态变量和非静态变量的定义集合。
 */
public class VarFieldStruct 
		implements IRStruct, IRStruct_contain_vars {
	
	/**
	 * 静态变量集合。
	 */
	protected RSet<VarStruct> staticVarSet;
	
	/**
	 * 非静态变量集合。
	 */
	protected RSet<VarStruct> varSet;
	
	public VarFieldStruct() {
		staticVarSet = new RSet<VarStruct>();
		varSet = new RSet<VarStruct>();
	}


	@Override
	public void defineVars_by_RStruct(VarFieldStruct vfStruct) {
		if (vfStruct == null) {
			throw new IllegalArgumentException(
					"通过VarFieldStruct来定义变量定义区域时，传入参数为null。");
		}

		defineStaticVars_by_RSet(vfStruct.getStaticVarSet());
		defineVars_by_RSet(vfStruct.getVarSet());
	}

	@Override
	public void defineStaticVars_by_RSet(RSet<VarStruct> vSet) {
		if (vSet == null) {
			throw new IllegalArgumentException(
					"通过变量集合来定义变量定义区域的静态部分时，传入参数为null。");
		}
		
		for (VarStruct vStruct : vSet) {
			defineStaticVar(vStruct);
		}
	}

	@Override
	public void defineVars_by_RSet(RSet<VarStruct> vSet) {
		if (vSet == null) {
			throw new IllegalArgumentException(
					"通过变量集合来定义变量定义区域的非静态部分时，传入参数为null。");
		}
		
		for (VarStruct vStruct : vSet) {
			defineVar(vStruct);
		}
	}

	@Override
	public void defineStaticVar(VarStruct vStruct) {
		if (vStruct == null) {
			throw new IllegalArgumentException(
					"不能用null来定义变量定义区域中的静态变量。");
		}
		
		//TODO 在这里添加对命名冲突的检测，防止声明同名的变量。
		
		staticVarSet.add(vStruct);
	}

	@Override
	public void defineVar(VarStruct vStruct) {
		if (vStruct == null) {
			throw new IllegalArgumentException(
					"不能用null来定义变量定义区域中的非静态变量。");
		}
		//TODO 在这里添加对命名冲突的检测，防止声明同名的变量。
		
		varSet.add(vStruct);
	}

	@Override
	public RSet<VarStruct> getStaticVarSet() {
		return staticVarSet;
	}


	@Override
	public RSet<VarStruct> getVarSet() {
		return varSet;
	}

}
