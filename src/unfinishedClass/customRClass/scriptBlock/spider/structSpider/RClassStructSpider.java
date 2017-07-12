package unfinishedClass.customRClass.scriptBlock.spider.structSpider;

import basicTool.RLogger;
import unfinishedClass.customRClass.RStringChecker;
import unfinishedClass.customRClass.rStruct.FunStruct;
import unfinishedClass.customRClass.rStruct.RClassStruct;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.ScriptDeclaration;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.CountableSpider;
import unfinishedClass.customRClass.scriptBlock.spider.grammarSpider.FunGSpider;

/**
 * 此Spider读取一串包含有脚本信息的Block，
 * 将其中的信息提取出来生成一个RClassStruct。
 */
public class RClassStructSpider 
extends UtilsRStructSpider_with_RStruct<RClassStruct>{
	
	public RClassStructSpider(ScriptBlock targetBlock) {
		super(targetBlock);
		finalRStruct = new RClassStruct();
	}

	
	@Override
	public void countWork() {
		//用于存储子链中的信息的字符串，
		//有的子链中固定为一个简单的信息，
		//无需复杂的遍历方法，所以这里可以直接用一个
		String subSingleString;
		switch(infoType){
		//对RClass类型的提取。
		case DECLAR_TYPE:
			//定义RClass类型。
			finalRStruct
			.defineType(getFistInfoType_fromSub());		//获取infoType。
			break;
			
			//对类名的提取。
		case DECLAR_NAME:
			//定义类名。
			finalRStruct
			.defineName(getFistInfoString_fromSub());	//获取直接的类名定义。
			break;
			
		case DECLAR_GEN_PARAMS:
			//定义泛参。
			finalRStruct
			.defineGenParam_by_RSet(
					getRSet_fromSub_use(
							new GenParamSetSpider()));
			break;
			
		case DECLAR_EXTENDS:
			//定义非接口父类。
			finalRStruct
			.defineExtends(
					getRSet_fromSub_use(
							new RClassRefSetSpider())
					.getRStruct(0));
			break;
			
		case DECLAR_IMPLEMENTS:
			//定义接口父类。
			finalRStruct
			.defineImplements_by_RSet(
					getRSet_fromSub_use(
							new RClassRefSetSpider()));
			break;
			
		case DECLAR_MEMBERS:
			//定义成员变量。
			finalRStruct
			.defineMembers_by_RStruct(
					getRStruct_fromSub_use(
							new VarFieldStructSpider()));
			break;
			
		case DECLAR_FUN_CONFUN:
			//定义构造Function。
			finalRStruct
			.defineConFun(
					getFunStruct());
			break;
			
		case DECLAR_FUN_STATIC:
			//定义静态Function。
			finalRStruct
			.defineStaticFun(
					getFunStruct());
			break;
			
		case DECLAR_FUN:
			//定义普通Function。
			finalRStruct
			.defineFun(
					getFunStruct());
			break;
			
		case DECLAR_FUN_ABSTRACT:
			//定义抽象Function。
			finalRStruct
			.defineAbstractFun(
					getFunStruct());
			break;
			
		default:
			//什么也不做。
			break;
		}
	}

	@Override
	public RClassStruct getRStruct() {
		return finalRStruct;
	}
}
