package unfinishedClass.customRClass.rStruct.baseRStruct;

import unfinishedClass.customRClass.rStruct.ArcFieldStruct;
import unfinishedClass.customRClass.rStruct.ArcStruct;
import unfinishedClass.customRClass.rStruct.RSet;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_arcs;

public abstract class RStruct_contain_name_genParams_vars_arcs 
		extends RStruct_contain_name_genParams_vars
		implements IRStruct_contain_arcs {
	
	/**
	 * 弧线定义区域。
	 */
	protected ArcFieldStruct arcField;
	
	public RStruct_contain_name_genParams_vars_arcs() {
		arcField = new ArcFieldStruct();
	}
	
	public void defineArcs_by_RStruct(ArcFieldStruct arcField) {
		if (arcField == null) {
			throw new IllegalArgumentException(
					"通过ArcFieldStruct定义SubFun的弧线区域时，"
					+ "传入的参数为null。");
		}
		
		this.arcField.defineArcs_by_RStruct(arcField);
	}


	@Override
	public void defineEArcs_by_RSet(RSet<ArcStruct> arcSet) {
		if (arcSet == null) {
			throw new IllegalArgumentException(
					"通过弧线集合定义SubFun的执行弧线部分时，"
					+ "传入的参数为null。");
		}
		
		arcField.defineEArcs_by_RSet(arcSet);
	}


	@Override
	public void definePArcs_by_RSet(RSet<ArcStruct> arcSet) {
		if (arcSet == null) {
			throw new IllegalArgumentException(
					"通过弧线集合定义SubFun的参数弧线部分时，"
					+ "传入的参数为null。");
		}
		
		arcField.definePArcs_by_RSet(arcSet);
	}

	@Override
	public void defineEArc(ArcStruct arcStruct) {
		if (arcStruct == null) {
			throw new IllegalArgumentException(
					"不能用null来定义执行弧线。");
		}
		
		arcField.defineEArc(arcStruct);
	}

	@Override
	public void definePArc(ArcStruct arcStruct) {
		if (arcStruct == null) {
			throw new IllegalArgumentException(
					"不能用null来定义参数弧线。");
		}
		
		arcField.definePArc(arcStruct);
	}

	@Override
	public RSet<ArcStruct> getEArcSet() {
		return arcField.getEArcSet();
	}

	@Override
	public RSet<ArcStruct> getPArcSet() {
		return arcField.getPArcSet();
	}

}
