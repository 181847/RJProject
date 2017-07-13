package unfinishedClass.customRClass.scriptBlock.spider.structSpider;

import unfinishedClass.customRClass.rStruct.ArcPointStruct;
import unfinishedClass.customRClass.rStruct.CommentStruct;
import unfinishedClass.customRClass.rStruct.ExcuteeStruct;
import unfinishedClass.customRClass.rStruct.ExcuterStruct;
import unfinishedClass.customRClass.rStruct.FunStruct;
import unfinishedClass.customRClass.rStruct.GPAssignStruct;
import unfinishedClass.customRClass.rStruct.GenParamStruct;
import unfinishedClass.customRClass.rStruct.IRStruct;
import unfinishedClass.customRClass.rStruct.LocationStruct;
import unfinishedClass.customRClass.rStruct.RClassRefStruct;
import unfinishedClass.customRClass.rStruct.RSet;
import unfinishedClass.customRClass.rStruct.SubFunStruct;
import unfinishedClass.customRClass.rStruct.VarStruct;
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
 * 方法中末尾带有“_fromSub”意味着从子链中获取信息
 */
public abstract class UtilsRStructSpider extends CountableSpider {

	/**
	 * 空的构造方法。
	 */
	public UtilsRStructSpider() {
		//什么也不做。
	}
	
	public UtilsRStructSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}
	
	protected InformationType getFistInfoType_fromSub(){
		//从子链中第一个非头部节点中获取infoType。
		return subBlock
				.getNext()
				.getInformation()
				.getType();
	}
	
	/**
	 * 获取子链上第一个Block的字符串。
	 * @return
	 * 		子链上第一个Block的字符串。
	 */
	protected String getFistInfoString_fromSub() {
		return subBlock
				.getNext()
				.getInformation()
				.getOriginalString();
	}
	
	/**
	 * 已知当前targetBlock的类型是GEN_PARAM，
	 * 连同targetBlock和其子链，
	 * 一同生成一个泛参定义结构。
	 * @return
	 * 		如果infoType不是GEN_PARAM，返回null。
	 * 		否则返回一个泛参定义结构。
	 */
	protected GenParamStruct getGenParamStruct(){
		if (infoType != InformationType.GEN_PARAM){
			//防止获取非法信息。
			return null;
		}
		
		GenParamStruct gps = new GenParamStruct();
		
		//利用targetBlock中的信息定义泛参名。
		gps.defineName(targetInfoString);
		
		//定义泛型约束。
		gps.defineGPConstraint(
				//获取子链中的类引用集合。
				getRSet_fromSub_use(
						new RClassRefSetSpider())
				//获取类引用集合中的第一个类引用作为泛型约束。
				.getRStruct(0));						
		
		return gps;
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
			//四种function声明中的一种，不能获取FunStruct。
			//防止读取非法信息。
			return null;
		}
		
		FunStruct fs = 
				getRStruct_fromSub_use(
						new FunStructSpider());
		
		//定义Function的名字。
		fs.defineName(funName);
		
		return fs;
	}
		
	/**
	 * 从targetBlock及其子链中获取变量定义。
	 * @return
	 * 		一个完整的变量定义结构。
	 */
	protected VarStruct getVarStruct(){
		VarStruct vs = null;
		switch(infoType){
		case VAR:
			//从子链中获取一个变量定义的信息，
			//这个获取的变量定义没有名字。
			vs = getRStruct_fromSub_use(
					//收集除了名字之外的变量定义 。
					new VarStructSpider());
			//定义变量名。
			vs.defineName(targetInfoString);
			break;
			
		default:
			break;
		}
		
		return vs;
	}
	
	/**
	 * 从当前targetBlock及其子链上获取一个类型引用定义。
	 * @return
	 * 		一个完整的类型引用定义。
	 */
	protected RClassRefStruct getRClassRefStruct(){
		RClassRefStruct rfs = new RClassRefStruct();
		switch(infoType){
		case CLASS_REF_CL:
			//设置类型名称。
			rfs.defineName(targetInfoString);
			//定义引用类型。
			rfs.defineRefType(infoType);
			if (hasSubBlock) {
				//如果有子链。
				//定义泛参指定。
				rfs.defineGPAssign_by_RSet(
						//获取子链中的泛参指定。
						getRSet_fromSub_use(
								new GPAssignSetSpider()));
			}
			break;
			
		case CLASS_REF_GP:
			//设置类型名称。
			rfs.defineName(targetInfoString);
			//定义引用类型。
			rfs.defineRefType(infoType);
			break;
			
		default:
			//不是类型引用的标签，
			//防止获取非法信息，
			//返回null。
			return null;
		}

		return rfs;
	}
	
	/**
	 * 从当前targetBlock及其子链上获取一个完整的泛参指定定义。
	 * @return
	 * 		一个泛参指定结构。
	 */
	protected GPAssignStruct getGPAssignStruct() {
		GPAssignStruct gpas = 
				new GPAssignStruct();
		switch(infoType){
		case GP_ASSIGN_CL:
			//泛参被指定为实类型。
			//targetInfoString 形如“T: basic.Integer”，
			//其中包含了被指定的泛参名，
			//以及用于填充泛参的另一个类型引用名，
			//一次性将这两个信息补充到RStruct中。
			gpas.defineAssign(targetInfoString);
			//标明泛参指定是指定为实类型。
			gpas.defineType(infoType);
			if (hasSubBlock){
				//如果有子链。
				//为参数类型指定泛参指定。
				gpas.defineGPAssign_forArgument_by_RSet(
						//从子链中获取泛参指定集合。
						getRSet_fromSub_use(
								new GPAssignSetSpider()));
			}
			break;
			
		case GP_ASSIGN_GP:
			//泛参被指定为另一个泛参。
			gpas.defineAssign(targetInfoString);
			//标明泛参指定是指定为实类型。
			gpas.defineType(infoType);
			break;
			
		default:
			//防止获取非法信息。
			return null;
		}
		
		return gpas;
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
	protected String extractFunName() {
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
	
	/**
	 * 获取当前targetBlock所定义的一个执行入口组件。
	 * @return
	 * 		执行入口定义集合。
	 */
	protected ExcuteeStruct getExcuteeStruct(){
		ExcuteeStruct es = new ExcuteeStruct();
		
		es.defineName(targetInfoString);
		
		return es;
	}

	/**
	 * 获取当前targetBlock定义的ExcuterStruct。
	 * @return
	 */
	protected ExcuterStruct getExcuterStruct() {
		ExcuterStruct es = new ExcuterStruct();
		
		es.defineName(targetInfoString);
		
		return es;
	}
	
	/**
	 * 获取当前Block及其子链所定义的一个SubFunStruct。
	 * @return
	 * 		当前Block及其子链所定义的一个SubFunStruct。
	 */
	protected SubFunStruct getSubFunStruct() {
		if (infoType != InformationType.SUBFUN) {
			return null;
		}
		
		//获取SubFunStruct。
		SubFunStruct sfs = 
				getRStruct_fromSub_use(
						new SubFunStructSpider());
		
		//定义SubFun的名字。
		sfs.defineName(targetInfoString);
		
		return sfs;
	}
	
	/**
	 * 从当前targetBlock及其子链中整理得到一个注释结构。
	 * @return
	 */
	protected CommentStruct getCommentStruct() {
		CommentStruct cs = new CommentStruct();
		
		//设置方形区域。
		cs.defineRect(targetInfoString);
		
		cs
		.defineCommentText_by_RStruct(
				getRStruct_fromSub_use(
						new TextStructSpider()));
		
		return cs;
	}
	
	/**
	 * 从当前infoStruct获取一个ArcPointStruct，
	 * 即弧线一个端点的定义。
	 * @return
	 */
	protected ArcPointStruct getArcPointStruct() {
		return new ArcPointStruct(targetInfoString);
	}
	
	/**
	 * 获取坐标结构。
	 * @return
	 */
	protected LocationStruct getLocationStruct() {
		//返回二维坐标的结构。
		return new LocationStruct(targetInfoString);
	}
	
	/**
	 * 从子链中获取相同类型RSet通用方法，
	 * spiderForSub是一个调用无惨构造方法生成的Spider，
	 * 本方法将会把这个SpiderForSub放到子链上，
	 * 执行功能，然后返回收集到的结果。
	 * @param spiderForSub
	 * 		用无惨构造方法生成的RSetSpider。
	 * @return
	 * 		spiderForSub收集到的信息的合集。
	 */
	protected 
	<RSTRUCT_RETURN extends IRStruct> 
	RSet<RSTRUCT_RETURN> 						//返回值类型。
	getRSet_fromSub_use 						//方法名。
	(UtilsRSetSpider_with_RSet<RSTRUCT_RETURN> 	//参数类型
	spiderForSub)								//参数名
	{
		
		//放置Spider。
		spiderForSub.placeSpider(subBlock);
		
		//收集信息。
		spiderForSub.workUntilEnd();
		
		//返回信息集合。
		return spiderForSub.getRSet();
	}

	/**
	 * 从子链的信息中获取一个RStruct的通用方法，
	 * spiderForSub是一个调用无惨构造方法生成的Spider，
	 * 本方法将会把这个SpiderForSub放到子链上，
	 * 执行功能，然后返回收集到的结果。
	 * @param spiderForSub
	 * 		将要被放置到子链上的Spider，
	 * 		这个Spider应该用无惨构造方法构造。
	 * @return
	 * 		spiderForSub的工作结果。
	 */
	protected
	<RSTRUCT_RETURN extends IRStruct> 
	RSTRUCT_RETURN 										//返回值类型。
	getRStruct_fromSub_use 								//方法名。
	(UtilsRStructSpider_with_RStruct<RSTRUCT_RETURN> 	//参数类型
	spiderForSub)										//参数名
	{
		//在子链上放置Spier。
		spiderForSub.placeSpider(subBlock);
		
		//Spider工作。
		spiderForSub.workUntilEnd();
		
		//返回Spider工作的结果。
		return spiderForSub.getRStruct();
	}
}
