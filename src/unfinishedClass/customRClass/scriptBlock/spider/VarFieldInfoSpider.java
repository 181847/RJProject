package unfinishedClass.customRClass.scriptBlock.spider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.Information;

/**
 * 提取Block链上的信息作为成员变量的信息，
 * 这里面可能还包括静态变量，
 * 静态变量会作为另一个类型存储在MemberStruct当中。
 */
public class VarFieldInfoSpider extends AbstractBCSpider {
	protected VarFieldStruct varFieldStruct;
	
	public VarFieldInfoSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		varFieldStruct = new varFieldStruct();
	}

	@Override
	protected void dealWithTargetBlock() {
		switch(targetBlock.getInformation().getType()){
		case STATIC:
			dealWith_STATIC();
			break;
		case VAR:
			dealWith_VAR();
			break;
		default:
			break;
		}
	}
	
	/**
	 * 提取targetBlock.information中存储的变量信息到varFieldStruct中。
	 */
	protected void dealWith_VAR() {
		varFieldStruct.addVar(information.getOriginalString());
	}

	/**
	 * 提取targetBlock子链中存储的变量信息作为静态成员信息。
	 */
	protected void dealWith_STATIC() {
		VarSetInfoSpider infoSpider = 
				new VarSetInfoSpider(targetBlock.getSub());
		infoSpider.workUntilEnd();
		
		//添加整理得到的成员信息
		varFieldStruct.addStaticSetStruct(infoSpider.getVarSet());
	}

	public VarFieldStruct getVarFieldStruct() {
		return varFieldStruct;
	}

}
