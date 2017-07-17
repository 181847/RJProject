package unfinishedClass.customRClass.rStruct;

import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct;
import unfinishedClass.customRClass.rStruct.baseInterface.IRStruct_contain_arcs;

/**
 * 包含弧线定义的区域，
 * 内部包括执行弧线和参数弧线。
 */
public class ArcFieldStruct 
		implements IRStruct, IRStruct_contain_arcs {
	
	/**
	 * 执行弧线集合。
	 */
	protected RSet<ArcStruct> eArcSet;
	
	/**
	 * 参数弧线集合。
	 */
	protected RSet<ArcStruct> pArcSet;
	
	public ArcFieldStruct() {
		eArcSet = new RSet<ArcStruct>();
		pArcSet = new RSet<ArcStruct>();
	}
	
	@Override
	public void defineArcs_by_RStruct(ArcFieldStruct afStruct) {
		if (afStruct == null) {
			throw new IllegalArgumentException(
					"通过弧线区域定义弧线时，传入参数为null");
		}
		
		defineEArcs_by_RSet(afStruct.getEArcSet());
		definePArcs_by_RSet(afStruct.getPArcSet());
	}
	
	public void defineEArcs_by_RSet(RSet<ArcStruct> eArcSet) {
		if (eArcSet == null) {
			throw new IllegalArgumentException(
					"通过弧线集合定义执行弧线时，传入参数为null");
		}
		
		for (ArcStruct aStruct : eArcSet) {
			defineEArc(aStruct);
		}
	}
	
	public void definePArcs_by_RSet(RSet<ArcStruct> pArcSet) {
		if (pArcSet == null) {
			throw new IllegalArgumentException(
					"通过弧线集合定义参数弧线时，传入参数为null");
		}
		
		for (ArcStruct aStruct : pArcSet) {
			defineEArc(aStruct);
		}
	}

	@Override
	public void defineEArc(ArcStruct arcStruct) {
		if (arcStruct == null) {
			throw new IllegalArgumentException(
					"不能用null来定义执行弧线。");
		}
		
		eArcSet.add(arcStruct);
	}

	@Override
	public void definePArc(ArcStruct arcStruct) {
		if (arcStruct == null) {
			throw new IllegalArgumentException(
					"不能用null来定义参数弧线。");
		}
		
		pArcSet.add(arcStruct);
	}

	@Override
	public RSet<ArcStruct> getEArcSet() {
		return eArcSet;
	}

	@Override
	public RSet<ArcStruct> getPArcSet() {
		return pArcSet;
	}

}
