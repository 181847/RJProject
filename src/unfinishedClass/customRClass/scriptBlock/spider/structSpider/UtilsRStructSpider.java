package unfinishedClass.customRClass.scriptBlock.spider.structSpider;

import unfinishedClass.customRClass.RStringChecker;
import unfinishedClass.customRClass.rStruct.FunStruct;
import unfinishedClass.customRClass.rStruct.GenParamStruct;
import unfinishedClass.customRClass.rStruct.RClassRefStruct;
import unfinishedClass.customRClass.rStruct.RSet;
import unfinishedClass.customRClass.rStruct.VarFieldStruct;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.ScriptDeclaration;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.CountableSpider;

/**
 * 这是一个工具类型的Spider，
 * 内部包含所有有关提取Block子链Struct信息的方法，
 * 例如提取子链中的变量定义，
 * 提取子链中的Function定义……
 * 所有这些可用的方法都会在此类中作为内部工具方法提供出来，
 * 子类通过继承此类，然后在countWork()中按照意愿来组合使用这些方法，
 * 就可以实现各种结构的定义。<br>
 * 所有操作的对象都是targetBlock.subBlock。
 */
public abstract class UtilsRStructSpider extends CountableSpider {

	public UtilsRStructSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}
	
	protected InformationType getRClassType(){
		//检查当前targetBlock是否是类型声明。
		if (infoType != InformationType.DECLAR_TYPE){
			//防止获取非法信息。
			return InformationType.VOID;
		} else {
			
			//从子链中第一个非头部节点中获取infoType。
			return subBlock
					.getNext()
					.getInformation()
					.getType();
		}
	}
	
	/**
	 * 已知infoType是类名声明，
	 * 获取子链中的具体类名定义。
	 * @return
	 * 		如果infoType是DECLAR_NAME，
	 * 		返回subBlock.next.information.originalString()，
	 * 		否则返回"WRONG_DECLAR_NAME_INFO_TYPE".
	 */
	protected String getRClassName() {
		//检查当前targetBlock是否是类名声明。
		if (infoType != InformationType.DECLAR_NAME){
			//防止获取非法信息。
			return "WRONG_DECLAR_NAME_INFO_TYPE";
		} else {
			return subBlock
					.getNext()
					.getInformation()
					.getOriginalString();
		}
	}
	
	/**
	 * 获取子链中的泛参定义集合。
	 * @return
	 * 		如果targetBlock的类型是DECLAR_GEN_PARAMS，返回子链中的泛参定义集合。
	 * 		否则返回null。
	 */
	protected RSet<GenParamStruct> getGenParamRSet(){
		if (infoType != InformationType.DECLAR_GEN_PARAMS){
			//防止获取非法信息。
			return null;
		}
		
		GenParamSetSpider gpss = 
				new GenParamSetSpider(subBlock);
		
		gpss.workUntilEnd();
		
		return gpss.getRSet();
	}
	
	/**
	 * 已知当前targetBlock的类型是GEN_PARAM，
	 * 连同targetBlock和其子链，
	 * 一同生成一个泛参定义结构。
	 * @return
	 * 		如果infoType不是GEN_PARAM，返回null。
	 * 		否则返回一个泛参定义结构。
	 */
	protected GenParamStruct getTargetGenParamStruct(){
		if (infoType != InformationType.GEN_PARAM){
			//防止获取非法信息。
			return null;
		}
		
		GenParamStruct gps = new GenParamStruct();
		
		//利用targetBlock中的信息定义泛参名。
		gps.defineName(targetInfoString);
		
		//定义泛型约束。
		gps.defineGPConstraint(
				getRClassRefRSet()		//获取子链中的类引用集合。
				.getRStruct(0));		//获取类引用集合中的第一个类引用作为泛型约束。
		
		return gps;
		
	}
	
	/**
	 * 获取父类引用合集，
	 * 费接口父类或者接口父类都可以使用这个方法，
	 * 来获取子链中的连续类引用声明。
	 * @return
	 * 		如果infoType即不是DECLAR_EXTENDS、DECLAR_GEN_PARAMS、又不是DECLAR_IMPLEMENTS，
	 * 		则返回null。
	 * 		否则返回子链中的类引用集合。
	 */
	protected RSet<RClassRefStruct> getRClassRefRSet(){
		if (infoType != InformationType.DECLAR_EXTENDS
				&& infoType != InformationType.DECLAR_IMPLEMENTS
				&& infoType != InformationType.DECLAR_GEN_PARAMS){
			//防止获取非法信息，
			//不是是父类声明的时候不允许从子链中获取类引用合集。
			return null;
		}
		
		RClassRefSetSpider rrss =
				new RClassRefSetSpider(subBlock);
		
		rrss.workUntilEnd();
		
		return rrss.getRSet();
	}
	
	/**
	 * 获取子链中的变量区域，
	 * 变量区域中包括静态变量和非静态变量。
	 * @return
	 * 		如果infoType不是
	 */
	protected VarFieldStruct getVarFieldStruct() {
		if (infoType != InformationType.DECLAR_MEMBERS
				&& infoType != InformationType.DECLAR_LOCALVARS) {
			//防止获取非法信息。
			return null;
		}
		
		VarFieldStructSpider vfss = 
				new VarFieldStructSpider(subBlock);
		
		vfss.workUntilEnd();
		
		return vfss.getRStruct();
	}

	/**
	 * 已知子链上存储着一个Function的定义，
	 * 适用于所有四种类型的Function，
	 * 并且会根据脚本的定义给Function定义名字，
	 * 构造Function的名字固定是"@SELF"。
	 * @return
	 * 		返回收集得到的FunStruct。
	 */
	protected FunStruct getFunStruct() {
		String funName = extractFunName();
		
		if (funName.isEmpty()){
			//function的名字为空表示当前targetBlock的类型不是
			//四种function声明，不能获取FunStruct。
			return null;
		}
		
		FunStructSpider fss = 
				new FunStructSpider(subBlock);
		
		fss.workUntilEnd();
		
		//定义Function的名字。
		fss.defineName(funName);
		
		return fss.getRStruct();
	}
	
	/**
	 * 已知targetBlock为Function类型（除了ConFun的另外三种），
	 * 提取其中的Function名字。
	 * @param declarFunStatic
	 * 		Function的具体类型，
	 * 		DECLAR_FUN_STATIC---静态Function、
	 * 		DECLAR_FUN----------普通Function、
	 * 		DELCAR_FUN_ABSTRACT---抽象Function。
	 * @return
	 * 		Function的名字，如果当前infoType不是三种Function中的一种，返回空串，
	 * 		如果当前infoType是DECLAR_FUN_CONFUN的话，
	 * 		就返回"@SELF"。
	 */
	private String extractFunName() {
		switch(infoType){
		case DECLAR_FUN_CONFUN:
			//对于构造Function，返回这个特殊的名字，
			//这个名字不符合组件命名规范，
			//只能在程序中这样赋予。
			return "@SELF";
			
		case DECLAR_FUN_STATIC:
			//静态Function，
			//将前面的声明字段剔除，
			//返回剩余的名称部分。
			return 
					targetInfoString
					.substring(
							ScriptDeclaration
							.declar_fun_static
							.length());
			
		case DECLAR_FUN:
			//普通Function，
			//将前面的声明字段剔除，
			//返回剩余的名称部分。
			return 
					targetInfoString
					.substring(
							ScriptDeclaration
							.declar_fun
							.length());
			
		case DECLAR_FUN_ABSTRACT:
			//抽象Function，
			//将前面的声明字段剔除，
			//返回剩余的名称部分。
			return
					targetInfoString
					.substring(
							ScriptDeclaration
							.declar_fun_abstract
							.length());
			
		default:
			return "NO_MATCH_FUN_NAME";
		}
	}

}
