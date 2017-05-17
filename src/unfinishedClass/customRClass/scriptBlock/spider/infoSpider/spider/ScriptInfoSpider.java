package unfinishedClass.customRClass.scriptBlock.spider.infoSpider.spider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.spider.AbstractBCSpider;
import unfinishedClass.customRClass.scriptBlock.spider.InfoSpider;
import unfinishedClass.customRClass.struct.RClassRefStruct;
import unfinishedClass.customRClass.struct.RClassStruct;

/**
 * 已知Block链为一个脚本信息链，
 * 收集这些脚本信息，
 * 来生成一个RClassStruct，
 * RClassStruct内部整理信息的格式遵循以下规范：<br>
 * 单独的信息将直接作为子项存储在rClassStruct中，
 * 例如RClass的类型信息将直接存在rClassStruct中；<br>
 * 而包含多个子信息的大信息块将会整理成一个专门的Struct之后再存储到rClassStruct中，
 * 例如继承父类的信息可能包含不止一个接口名字，
 * 这些接口名字将会统一整理在implementsStruct中，然后将ImplementsStruct放到rClassStruct中，
 * 同理对于成员信息、Function信息都会做同样的操作。
 */
public class ScriptInfoSpider extends AbstractBCSpider {
	/**
	 * 存储着一个RClass定义的结构化变量，
	 * 内部包含RClass的类型、名字、成员Function……
	 */
	protected RClassStruct rClassStruct;

	public ScriptInfoSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		rClassStruct = new RClassStruct();
	}

	@Override
	protected void dealWithTargetBlock() {
		switch(targetBlock.getInformation().getType()){
		case TYPE:
			dealWith_TYPE();
			break;
		case NAME:
			dealWith_NAME();
			break;
		case EXTENDS:
			dealWith_EXTENDS();
			break;
		case IMPLEMENTS:
			dealWith_IMPLEMENTS();
			break;
		case MEMBER:
			dealWith_MEMBER();
			break;
		case CONFUN:
			dealWith_CONFUN();
			break;
		case STATICFUN:
			dealWith_STATICFUN();
			break;
		case FUN:
			dealWith_FUN();
			break;
		case ABSTRACTFUN:
			dealWith_ABSTRACTFUN();
			break;
		default:
			break;
		}
	}
	
	/**
	 * 提取targetBlock的子链中存储的
	 * 成员Function信息到RClassStruct当中。
	 */
	protected void dealWith_ABSTRACTFUN() {
		AbstractFunInfoSpider infoSpider = 
				new AbstractFunInfoSpider(
						targetBlock.getSub(),
						targetBlock
						.getInformation()
						.getOriginalString());
		infoSpider.workUntilEnd();
		
		//添加整理得到的构造Function信息
		rClassStruct.appendAbstractFunStruct(infoSpider.getFunStruct());
	}

	/**
	 * 提取targetBlock的子链中存储的
	 * 成员Function信息到RClassStruct当中。
	 */
	protected void dealWith_FUN() {
		FunInfoSpider infoSpider = 
				new FunInfoSpider(
						targetBlock.getSub(),
						targetBlock
						.getInformation()
						.getOriginalString());
		infoSpider.workUntilEnd();
		
		//添加整理得到的构造Function信息
		rClassStruct.appendFunStruct(infoSpider.getFunStruct());
	}
	
	/**
	 * 提取targetBlock的子链中存储的
	 * 静态Function信息到RClassStruct当中。
	 */
	protected void dealWith_STATICFUN() {
		StaticFunInfoSpider infoSpider = 
				new StaticFunInfoSpider(
						targetBlock.getSub(),
						targetBlock
							.getInformation()
							.getOriginalString());
		infoSpider.workUntilEnd();
		
		//添加整理得到的构造Function信息
		rClassStruct.appendStaticFunStruct(infoSpider.getFunStruct());
	}

	/**
	 * 提取targetBlock的子链中存储的
	 * 构造Function信息到RClassStruct当中。
	 */
	protected void dealWith_CONFUN() {
		ConFunInfoSpider infoSpider = 
				new ConFunInfoSpider(
						targetBlock.getSub());
		infoSpider.workUntilEnd();
		
		//添加整理得到的构造Function信息
		rClassStruct.setConFunStruct(infoSpider.getFunStruct());
	}

	/**
	 * 提取targetBlock的子链中存储的成员变量信息到RClassStruct当中。
	 */
	protected void dealWith_MEMBER() {
		VarFieldInfoSpider infoSpider = 
				new VarFieldInfoSpider(targetBlock.getSub());
		infoSpider.workUntilEnd();
		
		//添加整理得到的成员信息
		rClassStruct.setMemberVarSet(infoSpider.getVarSet());
		//静态成员信息
		rClassStruct.setStaticMemberVarSet(infoSpider.getStaticVarSet());
	}

	/**
	 * 提取targetBlock.subBlock链上的每一个Block.information
	 * 中存储的RClass的原始父类接口名称信息
	 * 到RClassStruct当中，
	 * 这里不会进行任何检查。
	 */
	private void dealWith_IMPLEMENTS() {
		ImplementSetInfoSpider infoSpider = 
				new ImplementSetInfoSpider(targetBlock.getSub());
		infoSpider.workUntilEnd();
		
		//添加整理得到的继承父类接口信息
		rClassStruct.setImplementSet(infoSpider.getImplementSet());
	}

	/**
	 * 提取targetBlock.subBlock.next.information
	 * 中存储的RClass的原始父类名称信息
	 * 到RClassStruct当中，
	 * 这里不会进行任何检查。
	 */
	protected void dealWith_EXTENDS() {
		rClassStruct.setExtends(
				new RClassRefStruct(
						targetBlock
						.getSub()
						.getNext()
						.getInformation()
						.getOriginalString()));
	}

	/**
	 * 提取targetBlock.subBlock.next.information
	 * 中存储的RClass的原始名称信息
	 * 到RClassStruct当中，
	 * 这里不会进行任何检查。
	 */
	protected void dealWith_NAME() {
		rClassStruct.setName(
				targetBlock
				.getSub()
				.getNext()
				.getInformation()
				.getOriginalString());
	}

	/**
	 * 提取targetBlock.subBlock.next.informationType
	 * 中存储的RClass的类型信息
	 * 到RClassStruct当中，
	 * 这里不会进行任何检查。
	 */
	protected void dealWith_TYPE() {
		rClassStruct.setType(
				targetBlock
				.getSub()
				.getNext()
				.getInformation()
				.getType());
	}

	public RClassStruct getRClassStruct() {
		return rClassStruct;
	}
	
	

}
