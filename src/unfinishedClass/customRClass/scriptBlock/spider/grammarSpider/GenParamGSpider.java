package unfinishedClass.customRClass.scriptBlock.spider.grammarSpider;

import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;

/**
 * 检查泛参声明，
 * 这里的父类时DeclarGSpider，
 * 因为每一个泛参都必须声明一个类型引用作为泛参约束。
 */
public class GenParamGSpider extends DeclarGSpider {

	public GenParamGSpider(ScriptBlock targetBlock) {
		//两个记录槽。
		super(targetBlock, "泛参声明语法检查", 2);
	}

	@Override
	protected void declarGrammarWork() {
		if (infoType == InformationType.GEN_PARAM){
			//检查泛参约束，
			//泛参约束要求是一个类引用（实类型或者另一个泛参）,
			//检查数量限制为1。
			sendSpider(new RClassRefGSpider(subBlock, 1));
		} else {
			dealWith_Unexpected();
		}
	}
	
	/**
	 * 增加警告缺少泛参声明。
	 */
	@Override
	public boolean occurredError(){
		return super.occurredError()
				//缺少具体的类型声明或者过多的类型声明。
				|| 0 == getRecordOf(InformationType.GEN_PARAM);
	}
	
	/**
	 * 增加警告缺少泛参声明。
	 */
	@Override
	public String getRawReport(){
		String appendReport = "";
		
		if ( 0 ==  getRecordOf(InformationType.GEN_PARAM)){
			appendReport = "缺少泛参声明。";
		}
		
		return super.getRawReport()
				+ appendReport.toString();
	}
	
	/**
	 * 将GEN_PARAM映射到1，
	 * 其他的映射到0。
	 */
	protected int map_infoType_to_logIndex(InformationType infoType){
		switch(infoType){
		case GEN_PARAM:
			return 1;
			
		default:
			return 0;
		}
	}

}
