package unfinishedClass.customRClass.scriptBlock.spider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;

public class FunAbstractInfoSpider extends AbstractBCSpider {
	protected FunctionStruct functionStruct;
	
	public FunAbstractInfoSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		functionStruct = new FunctionStruct();
	}

	@Override
	protected void dealWithTargetBlock() {
		switch(targetBlock.getInformation().getType()){
		case EXCUTEE:
			dealWith_EXCUTEE();
			break;
		case PARAMETER:
			dealWith_PARAMETER();
			break;
		case EXCUTER:
			dealWith_EXCUTER();
			break;
		case RETURNVAL:
			dealWith_RETURNVAL();
			break;
		case LOCALVAR:
			dealWith_LOCALVAR();
			break;
		case SUBFUN:
			dealWith_SUBFUN();
			break;
		case ARC:
			dealWith_ARC();
			break;
		case COMMENT:
			dealWith_COMMENT();
			break;
		default:
			break;
		}
	}
	
	/**
	 * 收集注释信息。
	 */
	private void dealWith_COMMENT() {
		// TODO Auto-generated method stub
		CommentSetInfoSpider infoSpider = 
				new CommentSetInfoSpider(targetBlock.getSub());
		infoSpider.workUntilEnd();
		
		functionStruct.addCommentSetStruct(infoSpider.getCommentSetStruct());
	}

	/**
	 * 收集弧线信息。
	 */
	private void dealWith_ARC() {
		ArcSetInfoSpider infoSpider = 
				new ArcSetInfoSpider(targetBlock.getSub());
		infoSpider.workUntilEnd();
		
		functionStruct.addArcSetStruct(infoSpider.getArcSetStruct());
	}

	/**
	 * 收集子Function的信息。
	 */
	protected void dealWith_SUBFUN() {
		SubFunSetInfoSpider infoSpider = 
				new SubFunSetInfoSpider(targetBlock.getSub());
		infoSpider.workUntilEnd();
		
		functionStruct.addSubFunSetStruct(infoSpider.getSubFunSetStruct());
	}

	/**
	 * 收集本地变量信息。
	 */
	protected void dealWith_LOCALVAR() {
		VarFieldInfoSpider infoSpider = 
				new VarFieldInfoSpider(targetBlock.getSub());
		infoSpider.workUntilEnd();
		
		functionStruct.addLocalVarSetStruct(infoSpider.getVarFieldStruct());
	}

	/**
	 * 收集Returnval组件信息。
	 */
	protected void dealWith_RETURNVAL() {
		ReturnvalSetInfoSpider infoSpider = 
				new ReturnvalSetInfoSpider(targetBlock.getSub());
		infoSpider.workUntilEnd();
		
		functionStruct.addReturnvalSetStruct(infoSpider.getReturnvalSetStruct());
	}

	/**
	 * 收集Excuter组件信息。
	 */
	protected void dealWith_EXCUTER() {
		ExcuterSetInfoSpider infoSpider = 
				new ExcuterSetInfoSpider(targetBlock.getSub());
		infoSpider.workUntilEnd();
		
		functionStruct.addExcuterSetStruct(infoSpider.getExcutersStruct());
	}

	/**
	 * 收集Parameter组件信息。
	 */
	protected void dealWith_PARAMETER() {
		ParameterSetInfoSpider infoSpider = 
				new ParameterSetInfoSpider(targetBlock.getSub());
		infoSpider.workUntilEnd();
		
		functionStruct.addParameterSetStruct(infoSpider.getParameterSetStruct());
	}

	/**
	 * 收集Excutee组件信息。
	 */
	protected void dealWith_EXCUTEE() {
		ExcuteeSetInfoSpider infoSpider = 
				new ExcuteesInfoSpider(targetBlock.getSub());
		infoSpider.workUntilEnd();
		
		functionStruct.addExcuteeSetStruct(infoSpider.getExcuteeSetStruct());
	}

	public FunctoinStruct getFunStruct(){
		return functionStruct;
	}

}
