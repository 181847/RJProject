package unfinishedClass.customRClass.scriptBlock.spider.infoSpider.spider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;
import unfinishedClass.customRClass.scriptBlock.spider.infoSpider.infoStruct.struct.FunctionStruct;

/**
 * 抽象类实现有关收集Function信息的各种方法。
 */
public abstract class FunAbstractInfoSpider extends AbstractBCSpider {
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
	protected void dealWith_COMMENT() {
		CommentSetInfoSpider infoSpider = 
				new CommentSetInfoSpider(targetBlock.getSub());
		infoSpider.workUntilEnd();
		
		functionStruct.setCommentSet(infoSpider.getCommentSet());
	}

	/**
	 * 收集弧线信息。
	 */
	protected void dealWith_ARC() {
		ArcSetInfoSpider infoSpider = 
				new ArcSetInfoSpider(targetBlock.getSub());
		infoSpider.workUntilEnd();
		
		functionStruct.setArcSet(infoSpider.getArcSet());
	}

	/**
	 * 收集子Function的信息。
	 */
	protected void dealWith_SUBFUN() {
		SubFunSetInfoSpider infoSpider = 
				new SubFunSetInfoSpider(targetBlock.getSub());
		infoSpider.workUntilEnd();
		
		functionStruct.setSubFunSet(infoSpider.getSubFunSet());
	}

	/**
	 * 收集本地变量信息。
	 */
	protected void dealWith_LOCALVAR() {
		VarFieldInfoSpider infoSpider = 
				new VarFieldInfoSpider(targetBlock.getSub());
		infoSpider.workUntilEnd();
		
		//提取静态变量集合
		functionStruct
		.setStaticLocalVarSet(
				infoSpider
				.getVarFieldStruct()
				.getStaticVarSet());
		
		//提取非静态变量集合
		functionStruct
		.setLocalVarSet(
				infoSpider
				.getVarFieldStruct()
				.getVarSet());
	}

	/**
	 * 收集Returnval组件信息。
	 */
	protected void dealWith_RETURNVAL() {
		ReturnvalSetInfoSpider infoSpider = 
				new ReturnvalSetInfoSpider(targetBlock.getSub());
		infoSpider.workUntilEnd();
		
		functionStruct.setReturnvalSet(infoSpider.getReturnvalSet());
	}

	/**
	 * 收集Excuter组件信息。
	 */
	protected void dealWith_EXCUTER() {
		ExcuterSetInfoSpider infoSpider = 
				new ExcuterSetInfoSpider(targetBlock.getSub());
		infoSpider.workUntilEnd();
		
		functionStruct.setExcuterSet(infoSpider.getExcutersStruct());
	}

	/**
	 * 收集Parameter组件信息。
	 */
	protected void dealWith_PARAMETER() {
		ParameterSetInfoSpider infoSpider = 
				new ParameterSetInfoSpider(targetBlock.getSub());
		infoSpider.workUntilEnd();
		
		functionStruct.setParameterSet(infoSpider.getParameterSet());
	}

	/**
	 * 收集Excutee组件信息。
	 */
	protected void dealWith_EXCUTEE() {
		ExcuteeSetInfoSpider infoSpider = 
				new ExcuteeSetInfoSpider(targetBlock.getSub());
		infoSpider.workUntilEnd();
		
		functionStruct.setExcuteeSet(infoSpider.getExcuteeSet());
	}

	public FunctionStruct getFunStruct(){
		return functionStruct;
	}

}
