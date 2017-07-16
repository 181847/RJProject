package unfinishedClass.customRClass.rStruct.baseRStruct;

import unfinishedClass.customRClass.rStruct.RSet;
import unfinishedClass.customRClass.rStruct.VarFieldStruct;
import unfinishedClass.customRClass.rStruct.VarStruct;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_vars;

/**
 * 包含名称、泛参定义、变量区域定义的RStruct的定义。
 */
public abstract class RStruct_contain_name_genParams_vars
		extends RStruct_contain_name_genParams
		implements IRStruct_contain_vars {
	
	/**
	 * 变量定义区域。
	 */
	protected VarFieldStruct varField;

	@Override
	public void defineVars_by_RStruct(VarFieldStruct vfStruct) {
		if (vfStruct == null) {
			throw new IllegalArgumentException(
					"通过VarFieldStruct来定义变量定义区域时，传入参数为null。");
		}
		
		varField.defineVars_by_RStruct(vfStruct);
	}

	@Override
	public void defineStaticVars_by_RSet(RSet<VarStruct> vSet) {
		if (vSet == null) {
			throw new IllegalArgumentException(
					"通过变量集合来定义变量定义区域的静态部分时，传入参数为null。");
		}
		
		varField.defineStaticVars_by_RSet(vSet);
	}

	@Override
	public void defineVars_by_RSet(RSet<VarStruct> vSet) {
		if (vSet == null) {
			throw new IllegalArgumentException(
					"通过变量集合来定义变量定义区域的非静态部分时，传入参数为null。");
		}
		
		varField.defineVars_by_RSet(vSet);
	}

	@Override
	public void defineStaticVar(VarStruct vStruct) {
		if (vStruct == null) {
			throw new IllegalArgumentException(
					"不能用null来定义变量定义区域中的静态变量。");
		}
		
		varField.defineStaticVar(vStruct);
	}

	@Override
	public void defineVar(VarStruct vStruct) {
		if (vStruct == null) {
			throw new IllegalArgumentException(
					"不能用null来定义变量定义区域中的非静态变量。");
		}
		
		varField.defineVar(vStruct);
	}
	
	@Override
	public RSet<VarStruct> getStaticVarSet() {
		return varField.getStaticVarSet();
	}


	@Override
	public RSet<VarStruct> getVarSet() {
		return varField.getVarSet();
	}

}
